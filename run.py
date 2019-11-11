import os
import csv
import sys
import argparse

def setData():
    parser = argparse.ArgumentParser(epilog="Example of usage: 'python run.py --usersFilePath ../Desktop/example.txt' or by default 'python run.py'")
    parser.add_argument("--usersFilePath", help="Get file path.")
    args = parser.parse_args()
    if(args.usersFilePath is not None):
        path = args.usersFilePath
        isFile = os.path.isfile(path)
        if(isFile):
            openFile(path)
        else:
            print("Please see the usage by '--help' key.")
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

