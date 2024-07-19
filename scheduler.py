import schedule
import time
import os

# Function to run the main script
def run_main_script():
    print("Running main.py script...")
    os.system('python /Users/yuanyifu/Desktop/Capgemini/eco-tourism-project-backend/main.py')
    print("main.py script finished.")

# Check for the stop signal file
def check_for_stop_signal():
    return os.path.exists('stop_scheduler.txt')

# Schedule the job
schedule.every(0.5).minutes.do(run_main_script)

# Initial run
run_main_script()
initial_run_done = True

# Keep the script running
while True:
    schedule.run_pending()
    if check_for_stop_signal():
        print("Stop signal detected. Exiting scheduler.")
        break
    if initial_run_done:
        initial_run_done = False  # Reset the flag after the first run
    time.sleep(1)
