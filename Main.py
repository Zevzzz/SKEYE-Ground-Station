import cv2

from Vision import Vision
from cv2 import waitKey, destroyAllWindows

vision = Vision(0)

while True:
    img = vision.captureImg()
    medX, medY, maskedImg = vision.getColorMaskedImg(img, 7)

    imgWithMed = cv2.circle(img, (medX, medY), 5, (255, 100, 0), 5)
    vision.showImg(imgWithMed, 'Img Med')

    vision.showImg(maskedImg, 'Img Masked')




