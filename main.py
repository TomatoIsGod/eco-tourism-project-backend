import subprocess
import yaml
import logging

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

def run_script(script_path):
    result = subprocess.run(['python', script_path], capture_output=True, text=True)
    if result.returncode != 0:
        logging.error(f"Error running {script_path}: {result.stderr}")
    else:
        logging.info(f"Successfully ran {script_path}")

if __name__ == "__main__":
    # Load configuration
    with open('config.yaml', 'r') as file:
        config = yaml.safe_load(file)

    # Run crawler script
    logging.info("Running crawler script...")
    try:
        run_script('crawler/main_crawler.py')
    except Exception as e:
        logging.error(f"Failed to run crawler script: {e}")

    # Run data cleaning script
    logging.info("Running data cleaning script...")
    try:
        run_script('data_cleaning/clean_data.py')
    except Exception as e:
        logging.error(f"Failed to run data cleaning script: {e}")

    # Run database insert script
    logging.info("Running database insert script...")
    try:
        run_script('database/db_insert.py')
    except Exception as e:
        logging.error(f"Failed to run database insert script: {e}")

    logging.info("All tasks completed successfully.")
