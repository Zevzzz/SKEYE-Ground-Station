
from cv2 import circle as drawCircle
from cv2 import putText, FONT_HERSHEY_SIMPLEX

from Vision import Vision
from NWT import NWT
from SerialComm import SerialComm
from Landmarker import Landmarker


vision = Vision(1)
nwt = NWT()
lmer = Landmarker()
# serial = SerialComm()

while True:
    img = vision.captureImg()
    # medX, medY, sv, colorMaskedImg = vision.getColorMaskedImg(img, 10, 0.00000, 0.01)
    #
    # tX = (colorMaskedImg.shape[1] / 2) - medX
    # tY = (colorMaskedImg.shape[0] / 2) - medY

    tX, tY = lmer.extractNoseCoords(img)
    tX = img.shape[1] / 2 - tX
    tY = img.shape[0] / 2 - tY

    if tX == 320 and tY == 240:
        tX = 0
        tY = 0

    nwt.putNumber('EOS-tX', tX)
    nwt.putNumber('EOS-tY', tY)
    nwt.putNumber('EOS-tSV', 1000)

    # print(f'tX: {tX}\ntY:{tY}\ntSV{sv}')

    # imgWithMed = drawCircle(img, (medX, medY), 15, (255, 150, 0), 2)
    # putText(img, f'SV: {round(sv, 2)}',
    #         (medX - 75, medY + 45), FONT_HERSHEY_SIMPLEX, 0.9, (255, 150, 0), 2)
    # vision.showImg(imgWithMed, 'Img Med')
    # vision.showImg(colorMaskedImg, 'Img Masked')
    vision.showImg(img, 'Blank Preview')




