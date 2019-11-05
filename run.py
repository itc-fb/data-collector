import sys
import os

chrome = "chrome"
edge = "edge"
safari = "safari"

print("Type the number of browser below.")
print("Chrome - 1")
#print("Edge - 2")
#print("SAFARI - 3")


def setData():
    browser = int(input("Type the browser number: "))
    if(browser > 0 and browser < 2):
        login = input("enter login: ")
        password = input("enter pass: ")
        if browser == 1:
            runMaven(chrome, login, password)
        elif browser == 2:
            runMaven(chrome, login, password)
        else:
            runMaven(chrome, login, password)
    else:
        print("Incorrect number.")
        setData()





def runMaven(browser, login, password):
    mvnCommand = "mvn package -Dbrowser={} -Dlogin={} -Dpassword={}".format(browser, login, password)
    os.system(mvnCommand)

setData()

