
import RPi.GPIO as GPIO
from Main import getState

class Pitch:
    def __init__(self, pinNumber):
        self.pinNumber = pinNumber

        GPIO.setmode(GPIO.BCM)
        GPIO.setup(pinNumber, GPIO.OUT)







