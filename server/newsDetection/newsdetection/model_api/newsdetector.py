from nltk.tokenize import word_tokenize
from nltk import (precision, recall)
from nltk.corpus import stopwords
from sklearn.utils import shuffle
import pandas as pd
import numpy as np
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import confusion_matrix
from sklearn.metrics import accuracy_score
from sklearn.metrics import classification_report
from nltk import pos_tag
from nltk.corpus import opinion_lexicon
from sklearn.model_selection import train_test_split
from nltk.stem import WordNetLemmatizer
from sklearn.preprocessing import StandardScaler
from sklearn.utils import resample
from sklearn import preprocessing
import pickle
from sklearn.metrics import roc_curve
from sklearn.metrics import roc_auc_score
from matplotlib import pyplot
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import confusion_matrix
from sklearn.metrics import accuracy_score
from sklearn.metrics import classification_report
from nltk import pos_tag
from nltk.corpus import opinion_lexicon
from sklearn.model_selection import train_test_split
from nltk.stem import WordNetLemmatizer
import re


def detectionService(newsText, wm, dm):
    stop_words = set(stopwords.words('english'))
    news_source = {'reuters', 'getty', 'images', 'http', 'https', "donald", "trump"}
    # lemmatizing
    lemmatizer = WordNetLemmatizer()

    filename = 'model_api/glove.6B.50d.word2vec'
    # glove2word2vec(glove_input_file, word2vec_output_file)
    from gensim.models import KeyedVectors
    from gensim.scripts.glove2word2vec import glove2word2vec

    # 加载模型
    print('============加载模型============')
    print('============word2vec============')
    # model = KeyedVectors.load_word2vec_format(filename, binary=False)
    model = wm
    print('============加载完成============')

    data_new = newsText;

    # data_new = 'Donald Trump just couldn t wish all Americans a Happy New Year and leave it at that Instead, he had to give a shout out to his enemies haters and  the very dishonest fake news media.  The former reality show star had just one job to do and he couldn t do it. As our Country rapidly grows stronger and smarter, I want to wish all of my friends, supporters, enemies, haters, and even the very dishonest Fake News Media, a Happy and Healthy New Year,  President Angry Pants tweeted.  2018 will be a great year for America! As our Country rapidly grows stronger and smarter, I want to wish all of my friends, supporters, enemies, haters, and even the very dishonest Fake News Media, a Happy and Healthy New Year'
    # data_new = 'House Intelligence Committee Chairman Devin Nunes is going to have a bad day. He s been under the assumption, like many of us, that the Christopher Steele-dossier was what prompted the Russia investigation so he s been lashing out at the Department of Justice and the FBI in order to protect Trump. As it happens, the dossier is not what started the investigation, according to documents obtained by the New York Times.Former Trump campaign adviser George Papadopoulos was drunk in a wine bar when he revealed knowledge of Russian opposition research on Hillary Clinton.On top of that, Papadopoulos wasn t just a covfefe boy for Trump, as his administration has alleged. He had a much larger role, but none so damning as being a drunken fool in a wine bar. Coffee boys  don t help to arrange a New York meeting between Trump and President Abdel Fattah el-Sisi of Egypt two months before the election. It was known before that the former aide set up meetings with world leaders for Trump, but team Trump ran with him being merely a coffee boy.In May 2016, Papadopoulos revealed to Australian diplomat Alexander Downer that Russian officials were shopping around possible dirt on then-Democratic presidential nominee Hillary Clinton. '
    # data_new = 'Trump campaign adviser George Papadopoulos told an Australian diplomat in May 2016 that Russia had political dirt on Democratic presidential candidate Hillary Clinton, the New York Times reported on Saturday. The conversation between Papadopoulos and the diplomat, Alexander Downer, in London was a driving factor behind the FBI'

    # data_new ='A federal judge in New York on Thursday threw out a lawsuit that had accused President Donald Trump of violating the U.S. Constitution by accepting foreign payments through his hotels and other businesses, handing him a major victory on an issue that has dogged him since even before he took office in January. Though other lawsuits remain pending that make similar claims, the ruling by U.S. District Judge George Daniels is the first to weigh the merits of the U.S. '
    # data_new ='compromise Republican tax bill released late Friday does not eliminate a $7,500 electric vehicle tax credit as Republicans in the House of Representatives had previously proposed. The measure follows the lead of the Senate version approved last month that did not eliminate the credit. Killing the credit could have hurt automakers like General Motors Co (GM.N), Volkswagen AG (VOWG_p.DE), Tesla Inc (TSLA.O) and Nissan Motor Co (7201.T).'
    # 数据预处理
    def tokenize_and_preproces(x):
        tokens = word_tokenize(x)
        cleaned_tokens = []
        for each_token in tokens:
            tmp = each_token.lower()
            if tmp in news_source:
                continue

            # drop stopwords
            if (tmp.isalpha() and tmp not in stop_words):
                # stemming
                tmp = lemmatizer.lemmatize(tmp)
                cleaned_tokens.append(tmp)

        return cleaned_tokens

    le = "[\s+\.\!\/_,$%^*(+\"\')]+"
    url_regex = re.compile(r"(?:www|https|http?)[^\s]+")
    twitter_handle1 = re.compile(r"\s([@#][\w_-]+)")
    twitter_handle2 = re.compile(r"\(([@#][\w_-]+)[\)]+")
    remove_last = re.compile(r"(\.|\?)([^\.]+)(\.$|$)")
    source_match = re.compile(r"^([^-]+)-")
    data = re.sub(url_regex, '', str(data_new))
    data = re.sub(twitter_handle1, '', data)
    data = re.sub(twitter_handle2, '', data)
    data = re.sub(remove_last, '', data)
    data = re.sub(le, ' ', data)
    clean_tokens = tokenize_and_preproces(data)
    print(data)
    d = data.split(' ')
    print(d)
    print(type(d))

    def get_vector(x, word_features_cur=None):
        tmp = np.zeros(50)
        # tmp1=list(set(x).intersection(word_features_cur))
        for word in x:
            if word_features_cur is not None and word not in word_features_cur:
                continue
            try:
                tmp = np.add(tmp, model[word])
            except:
                continue
        return tmp

    d_w2v = get_vector(clean_tokens)
    print(d_w2v)

    # 加载模型
    print('============加载模型============')
    print('============逻辑回归模型detection.pkl============')
    # detection_model = joblib.load('model_api/detection.pkl')
    detection_model = dm

    print('============加载完成============')
    res = detection_model.predict([d_w2v])
    res_pr = detection_model.predict_proba([d_w2v])
    print('============结果============')
    print(res)
    print(res_pr)
    return res, res_pr
