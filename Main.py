
from cv2 import circle as drawCircle
from cv2 import putText, FONT_HERSHEY_SIMPLEX

from Vision import Vision
from NWT import NWT
from SerialComm import SerialComm


vision = Vision(1)
nwt = NWT()
# serial = SerialComm()

while True:
    img = vision.captureImg()
    medX, medY, sv, colorMaskedImg = vision.getColorMaskedImg(img, 10, 0.00000, 0.01)

    # serial.putData('TEST DATA')
    tX = (colorMaskedImg.shape[1] / 2) - medX
    tY = (colorMaskedImg.shape[0] / 2) - medY

    if tX == 320 and tY == 240:
        tX = 0
        tY = 0

    nwt.putNumber('EOS-tX', tX)
    nwt.putNumber('EOS-tY', tY)
    nwt.putNumber('EOS-tSV', sv)

    print(f'tX: {tX}\ntY:{tY}\ntSV{sv}')

    imgWithMed = drawCircle(img, (medX, medY), 15, (255, 150, 0), 2)
    putText(img, f'SV: {round(sv, 2)}',
            (medX - 75, medY + 45), FONT_HERSHEY_SIMPLEX, 0.9, (255, 150, 0), 2)
    vision.showImg(imgWithMed, 'Img Med')
    vision.showImg(colorMaskedImg, 'Img Masked')




