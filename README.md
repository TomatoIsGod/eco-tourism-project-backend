# 生态旅游AI小程序后端爬虫功能（Python）

## 项目简介
本项目主要功能包括从携程网爬取景点信息，对数据进行清洗处理，并将清洗后的数据存储到MySQL数据库中。同时，项目还具备定时任务功能，可以自动化爬取多个城市的景点信息。

## 配置说明
在开始之前，请确保在'config.yaml‘中正确配置以下内容：
db_config:
  host: "47.113.192.168"
  user: "ecotour"
  password: "ecotour!123"
  database: "ecotour"
webdriver_path: "/path/to/your/ChromeDriver"  # 指定ChromeDriver的路径
local_image_path: "/path/to/your/local/Directory"  # 指定保存图片的目录
base_url: "https://you.ctrip.com/sight/"   #请根据携程网站上城市景点列表页面的URL调整

## 使用说明
1. 首先在'scheduler.py'中设定定时爬虫的时间间隔。scheduler 的定时任务功能，用于自动化爬取多个城市的景点信息。通过 cities_to_scrape.txt 文件列出需要爬取的城市，并利用 last_scraped_city.txt 记录上次爬取的城市，以确保下次爬取从上次停止的地方继续。调度脚本 scheduler.py 每隔设定的时间间隔自动运行 main.py 脚本，依次爬取每个城市的数据，进行数据清洗，并将结果插入到 MySQL 数据库中。
2. 'scheduler.py'有检查是否存在停止信号文件（例如一个特定的文件是否存在），用来决定是否停止调度器的功能。如果检测到停止信号，脚本会打印 "Stop signal detected. Exiting scheduler." 并退出循环，从而终止脚本的运行。
当想停止定时任务进程时，可以在根目录下创建“stop_scheduler.txt'，即可停止定时任务的进程。

## 文件结构

```plaintext
├── config.yaml                # 配置文件，包含数据库连接信息和其他配置
├── cities_to_scrape.txt       # 需要爬取的城市列表
├── last_scraped_city.txt      # 记录上次爬取的城市
├── city_ids.txt               # 城市ID与名称的对应关系
├── city_url_mapping.yaml      # 城市与URL的映射关系
├── crawler/
│   ├── main_crawler.py        # 主要的爬虫脚本
├── data_cleaning/
│   └── clean_data.py          # 数据清洗脚本
├── database/
│   └── db_insert.py           # 数据插入脚本
├── scheduler.py               # 调度脚本，用于定时运行爬虫
├── generate_city_url_mapping.py  # 生成城市URL映射的脚本
└── README.md                  # 项目说明文件
