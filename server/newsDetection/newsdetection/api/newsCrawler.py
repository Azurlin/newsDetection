# -*- coding: utf-8 -*-
"""
Created on Mon Apr 12 14:55:06 2021

@author: azurlin
"""
import re
from bs4 import BeautifulSoup
import requests
from lxml import html
from html.parser import HTMLParser
from lxml import etree

# url = url1
'''
newsdict = {
        'title':'null',
        'text':'null',
        'author':'null',
        'date':'null'
        }

#author
author = soup.findAll(name="span", attrs={"class" :"news-byline-full__name xs-block link-initial--text-black"})
#print("+++++++++++++++++++++")
#print(author[0].string)
newsdict['author'] = author[0].string

#title
#print(soup.h1.string) 
newsdict['title'] = soup.h1.string

text=''
count=0
newscontent = soup.find_all('p')
for i in newscontent:
    count = count +1
    if not i.string is None:
        #print(i.string)
        text = text+i.string
    if count == 4:
        #print("===============")
        #print(i.string)
        newsdict['date'] = i.string

newsdict['text'] = text        
print(newsdict)    
'''


def getText(url1):
    # url1 = "https://www.buzzfeednews.com/article/davidmack/tiger-woods-crash-speeding"

    header = {
        "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36"
    }
    page = requests.Session().get(url1, headers=header)

    # tree = html.fromstring(page.text)
    htmls = page.text
    soup = BeautifulSoup(htmls)
    # print(htmls)
    # soup = BeautifulSoup(html)

    newsdict = {
        'title': 'null',
        'text': 'null',
        'author': 'null',
        'date': 'null'
    }

    # author
    author = soup.findAll(name="span", attrs={"class": "news-byline-full__name xs-block link-initial--text-black"})
    # print("+++++++++++++++++++++")
    # print(author[0].string)
    newsdict['author'] = author[0].string

    # title
    # print(soup.h1.string)
    newsdict['title'] = soup.h1.string

    text = ''
    count = 0
    newscontent = soup.find_all('p')
    for i in newscontent:
        count = count + 1
        if count == 4:
            # print("===============")
            # print(i.string)
            l = i.string
            # Posted on April 4, 2021, at14: 13p.m.ET
            datedict = {"January": 1, "February": 2, "March": 3, "April": 4,
                        "May": 5, "June": 6, "July": 7, "August": 8,
                        "September": 9, "October": 10, "November": 11, "December": 12, }
            for k in datedict.keys():
                m = re.search(k, str(l))
                if not m is None:
                    break
                m = 1
            try:
                num = re.findall('\d+', l)
                dstr = str(num[1]) + '-' + str(datedict[m.group()]) + '-' + str(num[0])
            except:
                dstr = '2021-1-1'
            newsdict['date'] = dstr
            continue
        if not i.string is None:
            # print(i.string)
            text = text + i.string

    newsdict['text'] = text
    # print(newsdict)


    return newsdict


def getUrl(count):
    url = "https://www.buzzfeednews.com/"
    url1 = "https://www.buzzfeednews.com/article/davidmack/tiger-woods-crash-speeding"

    url2 = "https://www.buzzfeednews.com/us/feed/home?page="+str(count)+"&flexpro_enabled=1"

    header = {
        "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36"
    }
    page = requests.Session().get(url2, headers=header)

    # tree = html.fromstring(page.text)
    htmls = page.text
    # print(htmls)
    # soup = BeautifulSoup(html)
    soup = BeautifulSoup(htmls)
    # url ===== url2
    newslist = []
    newsurltag = soup.find_all('article')
    for i in newsurltag:
        nurl = i.a["href"]
        newslist.append(getText(nurl))
        # print(getText(nurl))

    return newslist
    print(newslist)


# f=open("newsText.txt","w",encoding='utf-8')
# f.write(str(newslist))
# f.close()
# newslist.append(getText(nurl))
# print(getText('https://www.buzzfeednews.com/article/tanyachen/black-tiktok-creators-addison-rae-tonight-show-rachel-hollis'))


# print(soup.find_all('a'))

# article
# 取新闻url
# print(soup.article.a["href"])

# 总新闻
'''
article = soup.find_all('article')
#print(article)
nlist = []
for tag in article:
    nlist.append(tag.a["href"])
'''

'''
for i in nlist:
    page = requests.Session().get(i, headers=header)
    htmls1=page.text
    soup1 = BeautifulSoup(htmls1)

def getNewsInfo():
'''
