import os
import yaml

def main():
    # Load city URL mapping from city_url_mapping.yaml
    with open('city_url_mapping.yaml', 'r') as file:
        city_url_mapping = yaml.safe_load(file)['city_url_mapping']

    # Get city name from user input
    city_name = input("请输入城市名: ").strip().lower()

    # Get URL for the city from the mapping
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

if __name__ == "__main__":
    main()
