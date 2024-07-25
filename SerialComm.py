
from serial import Serial

class SerialComm:
    def __init__(self):
        self.serial = Serial()
        self.serial.baudrate = 9600
        self.serial.port = 'COM7'
        self.serial.open()

    def putData(self, data):
        self.serial.write(data)




