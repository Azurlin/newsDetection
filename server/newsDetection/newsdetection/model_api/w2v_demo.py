import csv
import random
import string
import collections
import nltk
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
from sklearn.preprocessing import StandardScaler
from sklearn.utils import resample
from sklearn import preprocessing

train_test_ratio = 0.8
test_train_div = 0.2
# lemmatizing
lemmatizer = WordNetLemmatizer()

# collects stopwords
stop_words = set(stopwords.words('english'))

news_source = {'reuters', 'getty', 'images', 'http', 'https', "donald", "trump"}


# Preprocess data- remove puctuation from words, remove stopwords, lemmatize and lower case
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


import re


# Read input file and split into train and test dataframes
def read_input_file():
    news = []
    category = []
    all_words = []
    all_words_fake = []
    all_words_true = []

    # cleaning data to remove peculiar patterns
    # remove URL, twitter handler, News source and location
    le = "[\s+\.\!\/_,$%^*(+\"\')]+"
    url_regex = re.compile(r"(?:www|https|http?)[^\s]+")
    twitter_handle1 = re.compile(r"\s([@#][\w_-]+)")
    twitter_handle2 = re.compile(r"\(([@#][\w_-]+)[\)]+")
    remove_last = re.compile(r"(\.|\?)([^\.]+)(\.$|$)")
    source_match = re.compile(r"^([^-]+)-")

    with open("Fake.csv", "r", encoding='UTF-8') as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        next(csv_reader)
        for lines in csv_reader:
            tmp1 = re.sub(url_regex, '', lines[1])
            tmp1 = re.sub(twitter_handle1, '', tmp1)
            tmp1 = re.sub(twitter_handle2, '', tmp1)
            tmp1 = re.sub(remove_last, '', tmp1)
            tmp1 = re.sub(le, ' ', tmp1)

            category.append('Fake')
            clean_tokens = tokenize_and_preproces(tmp1)
            news.append(clean_tokens)
            all_words.extend(clean_tokens)
            all_words_fake.extend(clean_tokens)

    with open("True.csv", "r", encoding='UTF-8') as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        next(csv_reader)
        for lines in csv_reader:
            tmp1 = re.sub(url_regex, '', lines[1])
            tmp1 = re.sub(twitter_handle1, '', tmp1)
            tmp1 = re.sub(twitter_handle2, '', tmp1)
            tmp1 = re.sub(source_match, '', tmp1)

            category.append('True')
            clean_tokens = tokenize_and_preproces(tmp1)
            news.append(clean_tokens)
            all_words.extend(clean_tokens)
            all_words_true.extend(clean_tokens)

    df_news = pd.DataFrame({'label': category, 'news': news})
    df_news = shuffle(df_news)

    Y = df_news.loc[:, 'label'].values
    X = df_news.loc[:, 'news'].values

    train_x, test_x, train_y, test_y = train_test_split(X, Y, test_size=test_train_div, random_state=0, stratify=Y)

    train_df = pd.DataFrame({'label': train_y, 'news': train_x})
    test_df = pd.DataFrame({'label': test_y, 'news': test_x})

    words = {"all_words": all_words, "all_words_fake": all_words_fake, "all_words_true": all_words_true}

    return train_df, test_df, words


train_df, test_df, words = read_input_file()

print(train_df)


###################################################################
def calculateROC(testy, testX, model):
    # generate a no skill prediction (majority class)
    ns_probs = [0 for _ in range(len(testy))]

    # predict probabilities
    lr_probs = model.predict_proba(testX)
    # keep probabilities for the positive outcome only
    lr_probs = lr_probs[:, 1]
    # calculate scores
    ns_auc = roc_auc_score(testy, ns_probs)
    lr_auc = roc_auc_score(testy, lr_probs)
    # summarize scores
    print('\n No Skill: ROC AUC=%.3f' % (ns_auc))
    print('Logistic: ROC AUC=%.3f' % (lr_auc))
    # calculate roc curves
    ns_fpr, ns_tpr, _ = roc_curve(testy, ns_probs, pos_label='True')
    lr_fpr, lr_tpr, _ = roc_curve(testy, lr_probs, pos_label='True')
    # plot the roc curve for the model
    pyplot.plot(ns_fpr, ns_tpr, linestyle='--', label='No Skill')
    pyplot.plot(lr_fpr, lr_tpr, marker='.', label='Logistic')
    # axis labels
    pyplot.xlabel('False Positive Rate')
    pyplot.ylabel('True Positive Rate')
    # show the legend
    pyplot.legend()
    # show the plot
    pyplot.show()


# GloVe
from gensim.models import KeyedVectors
from gensim.scripts.glove2word2vec import glove2word2vec

filename = 'glove.6B.50d.word2vec'
glove_input_file = 'glove.6B.50d.txt'
word2vec_output_file = 'glove.6B.50d.word2vec'
# glove2word2vec(glove_input_file, word2vec_output_file)

model = KeyedVectors.load_word2vec_format(filename, binary=False)


# This function gives the vector representation, using GLoVe, for the given list of words
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


# getting vector form of all words using GloVe
train_df["unigrams_vec_all"] = train_df.news.apply(lambda x: get_vector(x))
test_df["unigrams_vec_all"] = test_df.news.apply(lambda x: get_vector(x))
Y_all = train_df.label.values
X_all = train_df.unigrams_vec_all.values.tolist()

ytest = test_df.label.values
xtest = test_df.unigrams_vec_all.values.tolist()

mclass = 'ovr'

lr = LogisticRegression(solver='lbfgs', max_iter=1000, C=0.01,
                        random_state=0, multi_class=mclass,
                        dual=False, penalty="l2", class_weight="balanced").fit(X_all, Y_all)
import joblib

joblib.dump(lr, 'detection2.pkl')
print(type(xtest))
print(xtest)

yhat = lr.predict(xtest)

print('****************************************************')
print(' Feature: GloVe vectorization of all words')
print(' Classifier: Logistic Regression')
print('****************************************************')

print(mclass, 'Accuracy: ', accuracy_score(ytest, yhat))
print(mclass, 'Classification Report: \n', classification_report(ytest, yhat))
print(mclass, 'Classification Report: \n', confusion_matrix(ytest, yhat))

calculateROC(ytest, xtest, lr)
print('****************************************************')

with open('xtest.txt', 'w') as file_object:
    for line in xtest:
        file_object.write(str(line))
