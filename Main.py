
from cv2 import circle as drawCircle
from cv2 import putText, FONT_HERSHEY_SIMPLEX

from Vision import Vision
from NWT import NWT


vision = Vision(0)
nwt = NWT()

while True:
    img = vision.captureImg()
    medX, medY, sv, colorMaskedImg = vision.getColorMaskedImg(img, 10, 0.00000, 0.01)

    nwt.putNumber('EOS-tX', medX)
    nwt.putNumber('EOS-tY', medY)
    nwt.putNumber('EOS-tSV', sv)

    # imgWithMed = drawCircle(img, (medX, medY), 15, (255, 150, 0), 2)
    # putText(img, f'SV: {round(sv, 2)}',
    #         (medX - 75, medY + 45), FONT_HERSHEY_SIMPLEX, 0.9, (255, 150, 0), 2)
    # vision.showImg(imgWithMed, 'Img Med')
    # vision.showImg(colorMaskedImg, 'Img Masked')




