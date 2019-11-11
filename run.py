import os
import csv
import sys

def setData():
    if(len(sys.argv) == 2):
        filePath = sys.argv[1]
        isFile = os.path.isfile(filePath)
        if(isFile):
            openFile(sys.argv[1])
        else:
            print("Please type correct file path.")
    else:
        openFile("userLoginAndPass.txt")

def openFile(path):
    with open(path) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        for row in csv_reader:
            login = row[0]
            password = row[1]
            runMaven(login, password)

def runMaven(login, password):
    browser = "chrome"
    mvnCommand = "mvn package -Dlogin={} -Dpassword={} -Dbrowser={}".format(login, password, browser)
    os.system(mvnCommand)

setData()

