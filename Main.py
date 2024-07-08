
from enum import Enum

class EOSStates(Enum):
    STANDBY = 0
    SCANNING = 1
    TRACKING = 2
eosState = EOSStates.STANDBY


# Functs
def setState(newEOSState):
    eosState = newEOSState
def getState():
    return eosState



if __name__ == '__main__':
    while True:




