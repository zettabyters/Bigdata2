#! /usr/bin/python
import sys

amlrules = ['AML','Anti-Money','Laundering']
kycrules = ['KYC']
dataprivacyrules = ['antitrust','PII']
penaltyrules = ['lawsuit','violate','appeals']
competitorrules = ['mastercard','visa','discover','amex']
otherrules = ['Frauds', 'penalties', 'violations','PCI','payments"]','merchants']

#index = 0
heading = ''
for line in sys.stdin:

#    index = index + 1
    line = line.strip()
    words = line.split()
#    print 'Line: %s' % (line)
#    link = ''
    amlscore = 0
    kycscore = 0
    dataprivacyscore = 0
    penaltyscore = 0
    competitorscore = 0
    otherscore = 0
    totalscore = 0

    newfeed = False
    for word in words:
        if word == "link:":
           heading = "link"
           continue
        elif word == "description:":
           heading = "description"
#           newfeed = True
           continue 
        elif word == "title:":
           heading = "title"
           continue 

        if heading == "link":
#	   print '%s \t %s' % (heading, word)
           link = word
           amlscore = 0
           kycscore = 0
           dataprivacyscore = 0
           penaltyscore = 0
           competitorscore = 0
           otherscore = 0
           totalscore = 0
           continue
        elif word in amlrules:
             amlscore = amlscore + 1
	     totalscore = totalscore + 1
        elif word in kycrules:
             kycscore = kycscore + 1
	     totalscore = totalscore + 1
        elif word in dataprivacyrules:
             dataprivacyscore = dataprivacyscore + 1
	     totalscore = totalscore + 1
        elif word in competitorrules:
             competitorscore = competitorscore + 1
	     totalscore = totalscore + 1
        elif word in otherrules:
             otherscore = otherscore + 1
	     totalscore = totalscore + 1

#        totalscore = amlscore + kycscore + dataprivacyscore + competitorscore + otherscore
#    if newfeed:
    print '%s \t %s' % (link, totalscore)
