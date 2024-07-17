# Ecotourism Project Backend

This project is designed to scrape, clean, and store data for an ecotourism AI mini-program. The backend system involves a Python-based web scraper, data cleaning scripts, and a database insertion script.

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)


## Requirements

- Python 3.x
- Google Chrome
- ChromeDriver

## Installation

1. **Clone the repository:**

    ```bash
    git clone <https://github.com/TomatoIsGod/eco-tourism-project-backend.git>
    cd eco-tourism-project-backend
    ```

2. **Create and activate a virtual environment:**

    ```bash
    python3 -m venv env
    source env/bin/activate
    ```

3. **Install the dependencies:**

    ```bash
    pip install -r requirements.txt
    ```

4. **Download ChromeDriver:**

    Make sure to download the ChromeDriver version that matches your Google Chrome version from [ChromeDriver](https://sites.google.com/chromium.org/driver/). Place the `chromedriver` executable in a known location.

## Configuration

1. **Edit the `config.yaml` file:**

    Update the `config.yaml` file with your specific paths and database configuration.

    ```yaml
    input_file: "data/Suzhou_sight_data.json"
    output_file: "data/Suzhou_sight_data_cleaned_output.json"
    db_config:
      host: "47.113.192.168"
      user: "ecotour"
      password: "ecotour!123"
      database: "ecotour"
    webdriver_path: "/path/to/your/chromedriver"
    url: "https://you.ctrip.com/sight/suzhou11/s0-p1.html#sightname"  # Default URL for Suzhou
    ```

    To scrape data for a different city, change the `url` field in the `config.yaml` file to the desired city's URL.

## Usage

1. **Run the main script to scrape, clean, and insert data:**

    ```bash
    python main.py
    ```

    The main script will:
    - Scrape the data from the specified URL.
    - Clean the scraped data.
    - Insert the cleaned data into the MySQL database.

## Project Structure

eco-tourism-project-backend/
│
├── crawler/
│ ├── main_crawler.py
│ ├── utils.py
│
├── data_cleaning/
│ ├── clean_data.py
│
├── database/
│ ├── db_insert.py
│
├── data/
│ ├── Suzhou_sight_data.json
│ ├── Suzhou_sight_data_cleaned_output.json
│
├── config.yaml
├── requirements.txt
├── main.py
├── README.md
└── .gitignore


## Contributing

1. **Fork the repository.**
2. **Create a new branch.**
3. **Make your changes.**
4. **Submit a pull request.**


