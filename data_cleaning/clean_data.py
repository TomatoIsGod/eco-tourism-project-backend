import sys
import json
import re

# Check command line arguments
if len(sys.argv) != 3:
    print("Usage: python clean_data.py <input_file> <output_file>")
    sys.exit(1)

# Get input and output files from command line arguments
input_file = sys.argv[1]
output_file = sys.argv[2]

def clean_time(time_str):
    match = re.search(r'\d{2}:\d{2}', time_str)
    return match.group() if match else ""

def is_invalid_entry(entry):
    invalid_values = ["N/A", "-", ""]
    return all(value in invalid_values for key, value in entry.items() if key != 'city')

def should_remove_entry(entry):
    # Check if the entry should be removed based on tags
    tags_to_remove = ["演唱会", "剧场剧院", "看演出", "音乐节", "livehouse", "特色演出"]
    for tag in tags_to_remove:
        if tag in entry.get("tags", ""):
            return True
    return is_invalid_entry(entry)

def clean_data(input_file, output_file):
    with open(input_file, 'r', encoding='utf-8') as f:
        data = json.load(f)

    cleaned_data = []
    removed_count = 0
    for entry in data:
        if not should_remove_entry(entry):
            # Clean open_time and close_time fields
            entry['open_time'] = clean_time(entry['open_time'])
            entry['close_time'] = clean_time(entry['close_time'])
            cleaned_data.append(entry)
        else:
            removed_count += 1

    with open(output_file, 'w', encoding='utf-8') as f:
        json.dump(cleaned_data, f, ensure_ascii=False, indent=4)

    tags_to_remove = ["演唱会", "剧场剧院", "看演出", "音乐节", "livehouse", "特色演出"]
    print(f"原始数据条数: {len(data)}")
    print(f"清洗后数据条数: {len(cleaned_data)}")
    print(f"删除的数据条数: {removed_count}")
    print(f"其中，包含'演唱会'、'音乐节'等标签的删除条数: {sum(1 for entry in data if any(tag in entry.get('tags', '') for tag in tags_to_remove))}")

# 使用示例
clean_data(input_file, output_file)
print("Data cleaning completed successfully.")
