
import sys
import time
from networktables import NetworkTables

# To see messages from networktables, you must setup logging
import logging

logging.basicConfig(level=logging.DEBUG)




class NWT:
    def __init__(self):
        # ip = ''
        #
        # if len(sys.argv) != 2:
        #     print("Error: specify an IP to connect to!")
        #     exit(0)

        # ip = sys.argv[1]
        ip = '169.254.26.227'

        NetworkTables.initialize(server=ip)

        self.sd = NetworkTables.getTable("SmartDashboard")

    def putNumber(self, name, data):
        self.sd.putNumber(name, data)




