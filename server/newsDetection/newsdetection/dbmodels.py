from sqlalchemy import Column, Integer, String
from database import Base


class News(Base):
    __tablename__ = 'newsdisply'
    id = Column(Integer, primary_key=True)
    dataset_id = Column(Integer, unique=False)
    title = Column(String(50), unique=False)
    text = Column(String(500), unique=False)
    sign = Column(Integer, unique=False)

    def __init__(self, dataset_id=None, title=None, text=None, sign=None):
        self.dataset_id = dataset_id
        self.title = title
        self.text = text
        self.sign = sign

    def __repr__(self):
        return '<News %r>' % self.id


class IndexNews(Base):
    __tablename__ = 'news'
    id = Column(Integer, primary_key=True)
    title = Column(String(200), unique=False)
    content = Column(String, unique=False)
    sign = Column(Integer, unique=False)
    author = Column(String(50), unique=False)
    label = Column(Integer, unique=False)
    create_date = Column(String, unique=False)

    def __init__(self,title=None, content=None, sign=None,author = None,label = None,create_date = None):
        self.title = title
        self.content = content
        self.sign = sign
        self.author = author
        self.label = label
        self.create_date = create_date

    def __repr__(self):
        return '<IndexNews %r>' % self.id
