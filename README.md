## 文件目录

|  目录   | 说明  | 
|  ----  | ----  |
| /database  | 数据库 |
| /front-end  | 前端 |
| /server  | 服务端 |


## 1.服务端

##### 目录

- server
	- dataManager
		- newsdisplay (基于SpringBoot的数据管理)
	- newsDetection
		- newsdetection (基于Flask的虚假新闻检测)

|  开发环境   | 版本  | 
|  ----  | ----  |
| Python  | 3.7 |
| JDK  | 1.8 |
| Maven  | 3.6 |


## 2.前端

##### 目录

- front-end
	- vue-admin

|  依赖   | 版本  | 
|  ----  | ----  |
| Vue  | 2.2.2 |
| Element-ui  | 1.2.8 |

> 浏览器支持：最新浏览器以及 IE10+

#### 安装依赖
npm install

#### 启动服务 
npm run dev
启动地址: localhost:8081

#### 构建项目
npm run build


## 3.数据库

> 包含建表sql语句与测试数据：\database\newsdb.sql

|  数据库   | 版本  | 
|  ----  | ----  |
| MySQL  | 8.0 |

## 4.词向量模型

测试系统使用了glove.6B词向量
>下载地址:https://www.kaggle.com/rohitanil/glove6b50dtxt

转化为word2Vec形式：

	python -m gensim.scripts.glove2word2vec -i glove.6B.50d.txt -o glove.6B.50d.word2vec


## 5.系统部分页面展示


**首页**

![首页](https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/show/index.png)

**提交新闻检测页面**

![假新闻](https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/show/detection-fake.png)

![真新闻](https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/show/detection-true.png)

**数据集管理**

![数据集管理](https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/show/dataset.png)

**后台管理**

![dataset-admin](https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/show/dataset-admin.png)

![admin](https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/show/admin.png)

**新闻浏览**

![](https://azurlin.oss-cn-beijing.aliyuncs.com/ldu_project/show/sign.png)

