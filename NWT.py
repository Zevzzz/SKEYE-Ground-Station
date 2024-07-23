
from networktables import NetworkTables
import logging

logging.basicConfig(level=logging.DEBUG)

class NWT:
    def __init__(self):
        NetworkTables.initialize()
        self.sd = NetworkTables.getTable('SmartDashboard')

    def putNumber(self, name, data):
        self.sd.putNumber(name, data)




