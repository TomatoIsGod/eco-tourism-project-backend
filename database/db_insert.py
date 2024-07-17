import json
import mysql.connector
from datetime import datetime
import yaml

def load_config(config_path='config.yaml'):
    with open(config_path, 'r') as file:
        return yaml.safe_load(file)

def parse_time(time_str):
    if time_str and time_str != '-':
        try:
            return datetime.combine(datetime.today(), datetime.strptime(time_str, '%H:%M').time())
        except ValueError:
            return None
    return None

def insert_data_to_db(input_file, db_config):
    # Load JSON data
    with open(input_file, 'r', encoding='utf-8') as f:
        sights = json.load(f)

    # Print the number of items to be inserted
    print(f"Number of items to be inserted: {len(sights)}")

    # Connect to MySQL
    cnx = mysql.connector.connect(**db_config)
    cursor = cnx.cursor()

    # Prepare the INSERT statement
    insert_stmt = (
        "INSERT INTO sight_overview "
        "(name, level, rank_tag, tags_id, heat, score, rater_count, address, "
        "distance_from_city, price, cover_img_url, open_time, close_time) "
        "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
    )

    for item in sights:
        # Handle missing or invalid values
        name = item.get('name', '')
        level = item.get('level', '')
        rank_tag = item.get('rank_tag', '')
        tags_id = None  # Assuming tags_id is not provided in JSON
        heat = item.get('heat', '')
        score = item.get('score', '')
        rater_count = item.get('rater_count', '')
        address = item.get('address', '')
        distance_from_city = item.get('distance_from_city', '')

        price = item.get('price', '')
        try:
            price = float(price) if price != '-' else None
        except ValueError:
            price = None

        cover_img_url = item.get('cover_img_url', '')

        open_time = parse_time(item.get('open_time', ''))
        close_time = parse_time(item.get('close_time', ''))

        cursor.execute(insert_stmt, (name, level, rank_tag, tags_id, heat, score, rater_count,
                                     address, distance_from_city, price, cover_img_url,
                                     open_time, close_time))

    cnx.commit()
    cursor.close()
    cnx.close()

if __name__ == "__main__":
    config = load_config()
    input_file = config['output_file']  # 使用清洗后的数据文件作为输入
    db_config = config['db_config']
    insert_data_to_db(input_file, db_config)
