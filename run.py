import os
import csv
import sys

chrome = "chrome"

def setData():
    with open('userLoginAndPass.txt') as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        for row in csv_reader:
            login = row[0]
            password = row[1]
            if(len(sys.argv) > 1 and sys.argv[1] == chrome):
                runMaven(login, password, chrome)
            else:
                runMaven(login, password)

def runMaven(login, password, browser = chrome):
    mvnCommand = "mvn package -Dlogin={} -Dpassword={} -Dbrowser={}".format(login, password, browser)
    os.system(mvnCommand)

setData()

