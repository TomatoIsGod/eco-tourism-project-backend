import subprocess
import yaml

def run_script(script_path):
    result = subprocess.run(['python', script_path], capture_output=True, text=True)
    if result.returncode != 0:
        print(f"Error running {script_path}: {result.stderr}")
    else:
        print(f"Successfully ran {script_path}")

if __name__ == "__main__":
    # 加载配置文件
    with open('config.yaml', 'r') as file:
        config = yaml.safe_load(file)

    # 运行爬虫脚本
    print("Running crawler script...")
    run_script('crawler/main_crawler.py')

    # 运行数据清洗脚本
    print("Running data cleaning script...")
    run_script('data_cleaning/clean_data.py')

    # 运行数据库插入脚本
    print("Running database insert script...")
    run_script('database/db_insert.py')

    print("All tasks completed successfully.")
