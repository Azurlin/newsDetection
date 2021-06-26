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