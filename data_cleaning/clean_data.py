import json
import re
import yaml

def clean_time(time_str):
    match = re.search(r'\d{2}:\d{2}', time_str)
    return match.group() if match else ""

def is_invalid_entry(entry):
    invalid_values = ["N/A", "-", ""]
    invalid_count = sum(1 for value in entry.values() if value in invalid_values)
    return invalid_count > len(entry) / 2

def should_remove_entry(entry):
    if "演唱会" in entry.get("tags", ""):
        return True
    return is_invalid_entry(entry)

def clean_data(input_file, output_file):
    with open(input_file, 'r', encoding='utf-8') as f:
        data = json.load(f)

    cleaned_data = []
    removed_count = 0
    for entry in data:
        if not should_remove_entry(entry):
            entry['open_time'] = clean_time(entry['open_time'])
            entry['close_time'] = clean_time(entry['close_time'])
            cleaned_data.append(entry)
        else:
            removed_count += 1

    with open(output_file, 'w', encoding='utf-8') as f:
        json.dump(cleaned_data, f, ensure_ascii=False, indent=4)

    print(f"原始数据条数: {len(data)}")
    print(f"清洗后数据条数: {len(cleaned_data)}")
    print(f"删除的数据条数: {removed_count}")
    print(f"其中，包含'演唱会'标签的删除条数: {sum(1 for entry in data if '演唱会' in entry.get('tags', ''))}")

if __name__ == "__main__":
    with open('config.yaml', 'r') as file:
        config = yaml.safe_load(file)

    input_file = config['input_file']
    output_file = config['output_file']

    clean_data(input_file, output_file)

    # Print the number of items in the cleaned data file
    with open(output_file, 'r', encoding='utf-8') as f:
        cleaned_data = json.load(f)
        print(f"Number of items after cleaning: {len(cleaned_data)}")
