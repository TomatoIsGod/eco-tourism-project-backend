# 生态旅游AI小程序后端爬虫

### 介绍
生态旅游AI小程序后端爬虫模块

* 爬取指定网站的旅游景点数据
* 数据包括景点图片、名称、评级、标签、热点度、评分、地址和门票等
* 将爬取的数据保存到本地文件或数据库中
* 提供 API 接口以供前端查询

### 安装教程

1. 克隆仓库：
    ```bash
    git clone https://gitee.com/hellochenhao/eco-tourism-backend.git
    ```
2. 进入项目目录：
    ```bash
    cd eco-tourism-backend
    ```
3. 创建并激活虚拟环境（可选但推荐）：
    ```bash
    python -m venv venv
    source venv/bin/activate  # 在 Windows 上使用 `venv\Scripts\activate`
    ```
4. 安装所需依赖：
    ```bash
    pip install -r requirements.txt
    ```

### 使用说明

1. 运行爬虫并获取旅游数据：
    ```bash
    python main.py
    ```

2. 启动 Flask API 服务：
    ```bash
    flask run
    ```

### API 端点

#### 获取所有景点


此端点返回所有景点的信息。

#### 根据城市获取景点



此端点返回指定城市的景点信息。

### 工作计划

#### 第一周

- [x] 分析 & 爬取苏州城市的景点的列表页面：https://you.ctrip.com/sight/suzhou11/s0-p1.html
- [x] 爬取必要元素：景点图片、名称、A级、tags、热点度、评分数、地址、门票（可能有多个，取最低金额）
- [x] 将抓取的元素先本地化，可以输出成txt等文本

#### 第二周

- [ ] 景点基本数据的落库
- [ ] 实现指定城市查询接口
- [ ] 实现景区列表页面显示界面

#### 第三周

- [ ] 抓取景区详情页面信息 & 提取必要详情信息 & 落库 & 保存到本地
- [ ] 抓取景区轮播图片并且保存到本地

### 项目结构

```plaintext
eco-tourism-backend/
├── README.md
├── LICENSE
├── requirements.txt
├── config/
│   ├── __init__.py
│   ├── config.py
├── data/
│   ├── __init__.py
│   ├── database.py
│   ├── models.py
├── logs/
│   ├── tour_spider.log
├── spiders/
│   ├── __init__.py
│   ├── tour_spider.py
├── tests/
│   ├── __init__.py
│   ├── test_spider.py
├── main.py

### 配置

编辑 config/config.py 以配置数据库连接和其他设置。

### 许可证

根据 MIT 许可证分发。有关详细信息，请参阅 LICENSE 文件。
