import os
import yaml

# Step 1: 获取上次爬取的城市
def get_last_scraped_city():
    if os.path.exists('last_scraped_city.txt'):
        with open('last_scraped_city.txt', 'r') as file:
            last_city = file.read().strip()
            return last_city
    return None

# Step 2: 更新上次爬取的城市
def update_last_scraped_city(city_name):
    with open('last_scraped_city.txt', 'w') as file:
        file.write(city_name)

# Step 3: Run the main functionality
def main():
    # Load city URL mapping from city_url_mapping.yaml
    with open('city_url_mapping.yaml', 'r') as file:
        city_url_mapping = yaml.safe_load(file)['city_url_mapping']

    # Read city names from cities_to_scrape.txt
    with open('cities_to_scrape.txt', 'r', encoding='utf-8') as file:
        cities = [line.strip().lower() for line in file if line.strip()]

    last_city = get_last_scraped_city()
    if last_city:
        start_index = cities.index(last_city) + 1
    else:
        start_index = 0

    if start_index >= len(cities):
        print("所有城市的数据已全部抓取完成。")
        return

    city_name = cities[start_index]
    url = city_url_mapping.get(city_name)
    if not url:
        print(f"无法找到城市 {city_name} 的对应 URL。请检查城市名是否正确，并更新映射文件。")
        return

    # Generate file paths
    input_file = f"data/raw_data/{city_name}_sight_data.json"
    output_file = f"data/cleaned_data/{city_name}_sight_data_cleaned.json"

    # Run the crawler script with arguments
    os.system(f'python crawler/main_crawler.py "{city_name}" "{url}" "{input_file}" "{output_file}"')
    print(f"成功爬取{city_name}数据")

    # Run the data cleaning script
    os.system(f'python data_cleaning/clean_data.py "{input_file}" "{output_file}"')
    print(f"成功清洗{city_name}数据")

    # Run the database insert script
    os.system(f'python database/db_insert.py "{output_file}"')
    print(f"成功存储{city_name}数据")

    # Update the last scraped city
    update_last_scraped_city(city_name)

if __name__ == "__main__":
    main()
