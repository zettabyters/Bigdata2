#! /usr/bin/python
import sys

word2count = {}

for line in sys.stdin:
    line = line.strip()
    link,totalscore = line.split('\t',1)
    
    try:
        totalscore = int(totalscore)
    except ValueError:
        continue
    
    try:
        word2count[link] = word2count[link] + totalscore
    except:
        word2count[link] = totalscore

for word in word2count.keys():
    print '%s \t %s' % (word,word2count[word])
