from random import random

from flask import Flask, request
from gensim.models import KeyedVectors
from flask_sqlalchemy import SQLAlchemy
from flask_cors import CORS
from model_api import newsdetector
import joblib
import config
from database import db_session
from dbmodels import News
from dbmodels import IndexNews
from api import newsCrawler

app = Flask(__name__)
CORS(app)

# 加载模型
word2vec_model_path = 'model_api/glove.6B.50d.word2vec'
log_model_path = 'model_api/detection2.pkl'
word2vec_model = KeyedVectors.load_word2vec_format(word2vec_model_path, binary=False)
log_model = joblib.load(log_model_path)


# 跨域支持
def after_request(response):
    # JS前端跨域支持
    response.headers['Cache-Control'] = 'no-cache'
    response.headers['Access-Control-Allow-Origin'] = '*'
    return response


@app.teardown_appcontext
def shutdown_session(exception=None):
    db_session.remove()


@app.route('/')
def hello_world():
    return 'index'


@app.route('/getNews', methods=['POST'])
def get_news():
    print(request.json)
    count = request.json.get('count')
    print(request.json.get('count'))
    r = newsCrawler.getUrl(count)
    print(count)
    for temp in r:
        l = random.randint(1, 5)
        newssign = 1
        d1, d2 = newsdetector.detectionService(temp["text"][:10000], word2vec_model, log_model)
        if d1[0] == 'Fake':
            newssign = 0

        t = IndexNews(title=temp["title"],
                      content=temp["text"],
                      sign=newssign,
                      author=temp["author"],
                      label=l,
                      create_date=temp["date"],
                      )
        try:
            db_session.add(t)
            db_session.commit()
        except:
            return 0
    # res = db_session.query(IndexNews)
    return 1


@app.route('/newsDetection', methods=['POST'])
def news_detection():
    newsTitle = request.json.get('newsTitle')
    newsText = request.json.get('newsText')
    res1, res2 = newsdetector.detectionService(newsText, word2vec_model, log_model)
    return dict(msg='成功', data=res1[0])


@app.route('/newsListDetection', methods=['POST'])
def newsList_detection():
    res2 = News.query.filter(News.dataset_id == request.json.get('datasetId')).all()
    for i in res2:
        newssign = 1
        d1, d2 = newsdetector.detectionService(i.text, word2vec_model, log_model)
        if d1[0] == 'Fake':
            newssign = 0
        upres = db_session.query(News).filter(News.id == i.id).update({"sign": newssign})
    db_session.commit()
    db_session.close()
    return dict(msg='成功', data='检测完成')


if __name__ == '__main__':
    app.run()
