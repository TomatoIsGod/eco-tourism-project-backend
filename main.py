import os

# Main function to interact with the user
def main():
    city_name = input("请输入城市名称: ")
    url = input("请输入城市URL: ")

    # Generate file paths
    input_file = f"data/raw_data/{city_name}_sight_data.json"
    output_file = f"data/cleaned_data/{city_name}_sight_data_cleaned.json"

  # Run the crawler script with arguments
    os.system(f'python /Users/yuanyifu/Desktop/Capgemini/eco-tourism-project-backend/crawler/main_crawler.py "{city_name}" "{url}" "{input_file}" "{output_file}"')
    print(f"成功爬取{city_name}数据")

    # Run the data cleaning script
    os.system(f'python /Users/yuanyifu/Desktop/Capgemini/eco-tourism-project-backend/data_cleaning/clean_data.py "{input_file}" "{output_file}"')
    print(f"成功清洗{city_name}数据")

    # Run the database insert script
    os.system(f'python /Users/yuanyifu/Desktop/Capgemini/eco-tourism-project-backend/database/db_insert.py "{output_file}"')
    print(f"成功存储{city_name}数据")


if __name__ == "__main__":
    main()
