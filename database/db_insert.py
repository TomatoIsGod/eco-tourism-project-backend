import sys
import json
import mysql.connector
from datetime import datetime
import yaml

# Check command line arguments
if len(sys.argv) != 2:
    print("Usage: python db_insert.py <output_file>")
    sys.exit(1)

# Get output file from command line arguments
output_file = sys.argv[1]

# Load JSON data
with open(output_file, 'r', encoding='utf-8') as f:
    sights = json.load(f)

# Load database configuration from config.yaml
with open('/Users/yuanyifu/Desktop/Capgemini/eco-tourism-project-backend/config.yaml', 'r') as file:
    config = yaml.safe_load(file)

db_config = config['db_config']

# Connect to MySQL
cnx = mysql.connector.connect(
    host=db_config['host'],
    user=db_config['user'],
    password=db_config['password'],
    database=db_config['database']
)
cursor = cnx.cursor()

# Prepare the INSERT statement
insert_stmt = (
    "INSERT INTO sight_overview "
    "(name, level, rank_tag, tags_id, heat, score, rater_count, address, "
    "distance_from_city, price, cover_img_url, open_time, close_time, city) "
    "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
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
    
    # Handle price (convert to float or set to None if invalid)
    price = item.get('price', '')
    try:
        price = float(price) if price != '-' else None
    except ValueError:
        price = None
    
    cover_img_url = item.get('cover_img_url', '')
    
    # Handle open_time and close_time
    def parse_time(time_str):
        if time_str and time_str != '-':
            try:
                return datetime.combine(datetime.today(), datetime.strptime(time_str, '%H:%M').time())
            except ValueError:
                return None
        return None
    
    open_time = parse_time(item.get('open_time', ''))
    close_time = parse_time(item.get('close_time', ''))
    
    city = item.get('city', '')

    # Execute the INSERT statement
    cursor.execute(insert_stmt, (name, level, rank_tag, tags_id, heat, score, rater_count,
                                 address, distance_from_city, price, cover_img_url,
                                 open_time, close_time, city))

# Commit changes and close connection
cnx.commit()
cursor.close()
cnx.close()

print("Data insertion completed successfully.")
