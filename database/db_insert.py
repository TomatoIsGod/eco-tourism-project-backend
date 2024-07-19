import json
import mysql.connector
from datetime import datetime

# Check command line arguments
import sys
if len(sys.argv) != 2:
    print("Usage: python db_insert.py <output_file>")
    sys.exit(1)

output_file = sys.argv[1]

# Load JSON data
with open(output_file, 'r', encoding='utf-8') as f:
    sights = json.load(f)

# Load configuration from config.yaml
import yaml
with open('config.yaml', 'r') as file:
    config = yaml.safe_load(file)

db_config = config['db_config']

# Connect to MySQL
cnx = mysql.connector.connect(**db_config)
cursor = cnx.cursor()

# Prepare the INSERT statement
insert_stmt = (
    "INSERT INTO sight_overview "
    "(name, level, rank_tag, tags_id, heat, score, rater_count, address, "
    "distance_from_city, price, cover_img_url, open_time, close_time, city) "
    "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
)

def parse_time(time_str):
    if time_str and time_str != '-':
        try:
            return datetime.strptime(time_str, '%H:%M').time()
        except ValueError:
            return None
    return None

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
    
    # Handle price (convert to float or set to None if invalid)
    price = item.get('price', '')
    try:
        price = float(price) if price != '-' else None
    except ValueError:
        price = None
    
    cover_img_url = item.get('cover_img_url', '')
    city = item.get('city', '')
    
    # Handle open_time and close_time
    open_time = parse_time(item.get('open_time', ''))
    close_time = parse_time(item.get('close_time', ''))
    
    # Execute the INSERT statement
    cursor.execute(insert_stmt, (name, level, rank_tag, tags_id, heat, score, rater_count,
                                 address, distance_from_city, price, cover_img_url,
                                 open_time, close_time, city))

# Commit changes and close connection
cnx.commit()
cursor.close()
cnx.close()

print("Data insertion completed successfully.")
