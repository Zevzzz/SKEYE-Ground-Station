
import RPi.GPIO as GPIO
from simple_pid import PID
from Main import getState

kP = 0.0
kI = 0.0
kD = 0.0
pid = PID(kP, kI, kD)

class Azimuth:
    def __init__(self, pinNumberEN, pinNumberDIR, pinNumberPUL):
        self.pinNumberEN = pinNumberEN
        self.pinNumberDIR = pinNumberDIR
        self.pinNumberPUL = pinNumberPUL

        self.motorPWM = GPIO.PWM(pinNumberPUL, 1000)
        self.motorPWM.start(0)

        GPIO.setmode(GPIO.BCM)
        GPIO.setup(pinNumberEN, GPIO.OUT)
        GPIO.setup(pinNumberDIR, GPIO.OUT)
        GPIO.setup(pinNumberPUL, GPIO.OUT)

    def getAngleDeg(self):
        return 0

    def setAngleDeg(self, targetAngleDeg):
        pid.setpoint = targetAngleDeg
        output = pid(getAngleDeg())






