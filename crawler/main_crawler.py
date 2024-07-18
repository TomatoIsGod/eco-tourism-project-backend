import sys
import json
import re
import time
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import yaml

# Check command line arguments
if len(sys.argv) != 5:
    print("Usage: python main_crawler.py <city_name> <url> <input_file> <output_file>")
    sys.exit(1)

# Get city name, URL, input file, and output file from command line arguments
city_name = sys.argv[1]
url = sys.argv[2]
input_file = sys.argv[3]
output_file = sys.argv[4]

# Load configuration from config.yaml
with open('/Users/yuanyifu/Desktop/Capgemini/eco-tourism-project-backend/config.yaml', 'r') as file:
    config = yaml.safe_load(file)

webdriver_path = config['webdriver_path']

# Set up Chrome options
chrome_options = Options()
# chrome_options.add_argument("--headless")  # comment this line to run in headless mode
chrome_options.add_argument("--disable-blink-features=AutomationControlled")
chrome_options.add_argument("--disable-gpu")
chrome_options.add_argument("--no-sandbox")
chrome_options.add_argument("--disable-dev-shm-usage")
chrome_options.add_argument("start-maximized")
chrome_options.add_argument("disable-infobars")
chrome_options.add_argument("--disable-extensions")
chrome_options.add_experimental_option("excludeSwitches", ["enable-automation"])
chrome_options.add_experimental_option('useAutomationExtension', False)

# Set up the Chrome WebDriver
service = Service(webdriver_path)
driver = webdriver.Chrome(service=service, options=chrome_options)

driver.execute_cdp_cmd("Page.addScriptToEvaluateOnNewDocument", {
    "source": """
    Object.defineProperty(navigator, 'webdriver', {
      get: () => undefined
    })
    """
})

# Navigate to the specified URL
driver.get(url)

# Increase the wait time to 30 seconds
wait = WebDriverWait(driver, 30)
try:
    wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, 'div.sightListBox_box__XD4Au')))
    print("First page loaded successfully.")
except Exception as e:
    print("Error: Timeout while waiting for the first page to load.")
    driver.quit()
    exit()

# Function to parse time
def parse_time(time_str):
    open_time, close_time = None, None
    try:
        if '开园' in time_str:
            open_time = re.search(r'\d{2}:\d{2}', time_str).group()
        elif '闭园' in time_str:
            close_time = re.search(r'\d{2}:\d{2}', time_str).group()
    except Exception as e:
        print(f"Error parsing time: {e}")
    return open_time, close_time

# Function to parse detailed open_time and close_time
def parse_details_time(time_str):
    try:
        if '开放' in time_str:
            time_range = time_str.split('开放')[0].strip()
            if '-' in time_range:
                start_time, end_time = time_range.split('-')
                if '次日' in end_time:
                    end_time = end_time.replace('次日', '').strip()
                return start_time.strip(), end_time.strip()
    except Exception as e:
        print(f"Error parsing details time: {e}")
    return "-", "-"

# Function to clean up time strings
def clean_time(time_str):
    time_match = re.search(r'\d{2}:\d{2}', time_str)
    if time_match:
        return time_match.group()
    return ""

# JSON file setup
sights_data = []

# Function to extract sight items from a single page
def extract_sight_items():
    sight_items = driver.find_elements(By.CSS_SELECTOR, 'div.cardListBox_box__lMuWz > div')

    for item in sight_items:
        sight = {}
        # Initialize default values
        sight['name'] = 'N/A'
        sight['level'] = 'N/A'
        sight['rank_tag'] = 'N/A'
        sight['tags'] = 'N/A'
        sight['heat'] = 'N/A'
        sight['score'] = 'N/A'
        sight['rater_count'] = 'N/A'
        sight['address'] = 'N/A'
        sight['open_time'] = "-"
        sight['close_time'] = "-"
        sight['distance_from_city'] = 'N/A'
        sight['price'] = 0.0
        sight['cover_img_url'] = 'N/A'
        sight['detail_url'] = 'N/A'
        sight['detailed_address'] = 'N/A'
        sight['details'] = 'N/A'
        sight['city'] = city_name  # Use city_name from input

        # Extract image URL
        try:
            img = item.find_element(By.CSS_SELECTOR, 'div.coverModule_box__RH1cu > img')
            sight['cover_img_url'] = img.get_attribute('src')
        except Exception as e:
            print(f"Error extracting cover_img_url: {e}")

        # Extract name and detail page URL
        try:
            name_element = item.find_element(By.CSS_SELECTOR, 'div.titleModule_box__VMMFM > div > span:nth-child(1) > a')
            sight['name'] = name_element.text
            sight['detail_url'] = name_element.get_attribute('href')
        except Exception as e:
            print(f"Error extracting name or detail_url: {e}")

        # Extract level
        try:
            sight['level'] = item.find_element(By.CSS_SELECTOR, 'span.titleModule_level-text-view__40Dbg.titleModule_level-text__lxAaP').text
        except Exception as e:
            print(f"Error extracting level: {e}")

        # Extract rank tag
        try:
            sight['rank_tag'] = item.find_element(By.CSS_SELECTOR, 'div.rankInfoModule_rank_view__NUa8B > span').text
        except Exception as e:
            print(f"Error extracting rank_tag: {e}")

        # Extract tags
        try:
            tags_elements = item.find_elements(By.CSS_SELECTOR, 'div.rankInfoModule_tag_list_view__4_nZC > div > span')
            sight['tags'] = ", ".join([tag.text for tag in tags_elements])
        except Exception as e:
            print(f"Error extracting tags: {e}")

        # Extract heat
        try:
            sight['heat'] = item.find_element(By.CSS_SELECTOR, 'div.commentInfoModule_heat-score-view__yL8zo > span.commentInfoModule_heat-score_value__J8p3b').text
        except Exception as e:
            print(f"Error extracting heat: {e}")

        # Extract score
        try:
            sight['score'] = item.find_element(By.CSS_SELECTOR, 'div.commentInfoModule_comment-view__LBx9p > span.commentInfoModule_comment-text__UBk1F.commentInfoModule_comment-score_value__iUsa8').text
        except Exception as e:
            print(f"Error extracting score: {e}")

        # Extract review count
        try:
            sight['rater_count'] = item.find_element(By.CSS_SELECTOR, 'div.commentInfoModule_comment-view__LBx9p > span:nth-child(3)').text
        except Exception as e:
            print(f"Error extracting rater_count: {e}")

        # Extract distance from city center
        try:
            sight['distance_from_city'] = item.find_element(By.CSS_SELECTOR, 'div.bottomModule_left-view__6arPk > div.distanceView_box__zWu29 > span:nth-child(3)').text
        except Exception as e:
            print(f"Error extracting distance_from_city: {e}")

        # Extract price
        try:
            price_element = item.find_element(By.CSS_SELECTOR, 'div.bottomModule_right-view__wBeQE > div > div > span.priceView_real-price-text__xmmuA')
            price_text = price_element.text
            if price_text != '免费':
                sight['price'] = float(price_text.replace('¥', '').strip())
            else:
                sight['price'] = 0.0
        except Exception:
            try:
                # Check if the price is "免费"
                free_price_element = item.find_element(By.CSS_SELECTOR, 'div.bottomModule_right-view__wBeQE > div > div > span')
                if free_price_element.text == '免费':
                    sight['price'] = 0.0
                else:
                    sight['price'] = '-'
            except Exception as e:
                print(f"Error extracting price: {e}")
                sight['price'] = '-'

        # Extract rough address
        try:
            address_element = item.find_element(By.CSS_SELECTOR, '#__next > div.sight_list_page_wrap > div.sight_list_page > div.sightListBox_box__XD4Au > div.cardListBox_box__lMuWz > div:nth-child(3) > div.baseInfoModule_box__r0bkr > div.bottomModule_box__dHx0U > div.bottomModule_left-view__6arPk > div.distanceView_box__zWu29 > span:nth-child(1)')
            sight['address'] = address_element.text
        except Exception as e:
            print(f"Error extracting address: {e}")

        # Extract open time or close time
        try:
            time_text = item.find_element(By.CSS_SELECTOR, 'div.bottomModule_right-view__wBeQE > div > span').text
            sight['open_time'], sight['close_time'] = parse_time(time_text)
        except Exception as e:
            print(f"Error extracting time: {e}")

        # Clean up the "open_time" and "close_time" fields
        sight['open_time'] = clean_time(sight['open_time'])
        sight['close_time'] = clean_time(sight['close_time'])

        # Extract detail page information
        if sight['detail_url'] != 'N/A':
            driver.execute_script("window.open('');")
            driver.switch_to.window(driver.window_handles[1])
            driver.get(sight['detail_url'])
            try:
                wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, 'div.poiDetailPageWrap')))
                try:
                    sight['detailed_address'] = driver.find_element(By.CSS_SELECTOR, 'div.baseInfoContent > div:nth-child(1) > p.baseInfoText').text
                except Exception as e:
                    print(f"Error extracting detailed_address from detail page for {sight['name']}: {e}")
                    sight['detailed_address'] = 'N/A'
                try:
                    detail_time_text = driver.find_element(By.CSS_SELECTOR, 'div.baseInfoContent > div:nth-child(2) > p.baseInfoText.cursor.openTimeText').text
                    sight['details'] = detail_time_text
                    detail_open_time, detail_close_time = parse_details_time(detail_time_text)
                    if detail_open_time and detail_close_time:
                        sight['open_time'] = clean_time(detail_open_time)
                        sight['close_time'] = clean_time(detail_close_time)
                except Exception as e:
                    print(f"Error extracting detail time from detail page for {sight['name']}: {e}")
            except Exception as e:
                print(f"Error loading detail page for {sight['name']}: {e}")
            driver.close()
            driver.switch_to.window(driver.window_handles[0])

        # Update address if it is N/A
        if sight['address'] == 'N/A' and sight['detailed_address'] != 'N/A':
            sight['address'] = sight['detailed_address']

        # Skip if all fields are 'N/A' or None
        if all(value in ['N/A', None, 0.0, "-"] for value in sight.values()):
            print(f"Skipping {sight['name']} due to empty data.")
            continue

        # Add to JSON data
        sights_data.append(sight)
        print(f"Successfully extracted data for {sight['name']}")

# Loop through all pages
total_pages = 3
for page in range(1, total_pages + 1):
    print(f"Processing page {page}/{total_pages}...")
    
    # Scroll to load all items
    last_height = driver.execute_script("return document.body.scrollHeight")
    while True:
        driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
        time.sleep(2)  # Wait for the page to load new content
        new_height = driver.execute_script("return document.body.scrollHeight")
        if new_height == last_height:
            break
        last_height = new_height

    # Extract sight items from the current page
    extract_sight_items()
    
    # Click the "Next" button to go to the next page
    try:
        next_button = driver.find_element(By.CSS_SELECTOR, 'li.ant-pagination-next > a.ant-pagination-item-link')
        next_button.click()
        # Wait for the new page to load
        wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, 'div.sightListBox_box__XD4Au')))
    except Exception as e:
        print(f"Error: Could not navigate to page {page + 1}. {e}")
        break

# Write JSON data to file
with open(input_file, 'w', encoding='utf-8') as json_file:
    json.dump(sights_data, json_file, ensure_ascii=False, indent=4)

# Print the number of items scraped
print(f"Number of items scraped: {len(sights_data)}")

# Close the browser
driver.quit()
