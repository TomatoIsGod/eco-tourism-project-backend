import yaml

# Load configuration from config.yaml
with open('config.yaml', 'r') as file:
    config = yaml.safe_load(file)

base_url = config['base_url']

# Load city IDs from the city_ids.txt file
city_ids = {}
with open('city_ids.txt', 'r') as file:
    for line in file:
        line = line.strip()
        if not line:
            continue
        try:
            city_id, city_chinese, city_english = line.split(':')
            city_ids[city_chinese.strip().lower()] = (city_id.strip(), city_english.strip())
        except ValueError:
            print(f"Skipping invalid line: {line}")

# Dictionary to store city URL mapping
city_url_mapping = {}

for city_chinese, (city_id, city_english) in city_ids.items():
    url = f"{base_url}{city_english}{city_id}.html"
    city_url_mapping[city_chinese] = url
    print(f"Successfully created URL for {city_chinese}: {url}")

# Save the mapping to a YAML file
try:
    with open('city_url_mapping.yaml', 'w') as file:
        yaml.dump({'city_url_mapping': city_url_mapping}, file, allow_unicode=True)
    print("City URL mapping has been generated")
except Exception as e:
    print(f"Error writing city URL mapping to file: {e}")

# Verify content written to file
try:
    with open('city_url_mapping.yaml', 'r') as file:
        data = yaml.safe_load(file)
        print(f"Content of city_url_mapping.yaml: {data}")
except Exception as e:
    print(f"Error reading city URL mapping from file: {e}")
