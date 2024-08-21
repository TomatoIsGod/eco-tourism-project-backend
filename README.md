# 生态旅游AI小程序后端

### 介绍
生态旅游AI小程序后端

* 以小程序为平台和流量入口
* 让用户输入旅游的基本诉求
* 系统为用户输出详细旅游路书报告和出行建议
* 未来引入`ChatGPT`等AI技术手段，为用户提供更好的出行建议

### 软件架构
软件架构说明


### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request

### 工作计划

#### 第一周

##### 后端：

- [x] 了解微信小程序code授权获取流程/openid获取流程/手机号获取流程/昵称获取流程

- [x] 开发微信小程序code授权获取接口/openid获取接口

- [x] 开发用户授权确认&获取接口

- [x] 获取用户的手机号/昵称等信息，并落库

#####  爬虫：

- [x] 分析 & 爬取苏州城市的景点的列表页面：https://you.ctrip.com/sight/suzhou11/s0-p1.html

- [x] 爬取必要元素：景点图片、名称、A级、tags、热点度、评分数、地址、门票（可能有多个，取最低金额）

- [x] 将抓取的元素先本地化，可以输出成txt等文本



#### 第二周

##### 后端：

- [x] 实现指定城市查询接口

- [x] 实现点击骰子随机分配城市接口

- [x] 实现城市列表接口功能

#####  爬虫：

- [ ] 景点基本数据的落库

- [ ] 分析小程序前端界面需求

- [ ] 实现询问页面城市选择接口

- [ ] 实现更多城市选择接口

- [ ] 实现景区列表页面显示界面



#### 第三周：

后端：

- [ ] 路书详情页面数据接口

- [ ] 路书收藏接口

- [ ] 路书上某一景区替换推荐接口
