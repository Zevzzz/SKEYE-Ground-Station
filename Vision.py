
import cv2
import numpy as np

def getContourBrightness(contour, hsvImg):
    v_channel = hsvImg[:, :, 2]
    s_channel = hsvImg[:, :, 1]
    mask = np.zeros(v_channel.shape, dtype=np.uint8)

    cv2.drawContours(mask, [contour], -1, (255, 255, 255), -1)

    mean_v = cv2.mean(v_channel, mask=mask)[0]
    mean_s = cv2.mean(s_channel, mask=mask)[0]
    combined_brightness = (mean_v + mean_s) / 2

    return combined_brightness



class Vision:
    cap = None

    def __init__(self, cameraID = 0):
        self.cap = cv2.VideoCapture(cameraID, cv2.CAP_DSHOW)

        if not self.cap.isOpened():
            raise ValueError(f'Could not open camera with ID {cameraID}')

    def captureImg(self):
        if self.cap:
            ret, img = self.cap.read()
            return img
        else:
            raise ValueError('No capture found')

    def release(self):
        if self.cap:
            self.cap.release()
        else:
            raise ValueError('No capture found')

    def showImg(self, img, windowName):
        cv2.imshow(windowName, img)

        if cv2.waitKey(33) & 0xFF == 27:
            cv2.destroyAllWindows()

    def previewBrightnesses(self, img, areaFilteredContours, hsv_image):
        previewImg = img.copy()
        for contour in areaFilteredContours:
            brightness = getContourBrightness(contour, hsv_image)
            x, y, w, h = cv2.boundingRect(contour)
            cv2.rectangle(previewImg, (x, y), (x + w, y + h), (255, 100, 0), 2)
            cv2.putText(previewImg, f'B: {round(brightness, 2)}', (x, y - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.5,
                        (0, 255, 0))
        self.showImg(previewImg, 'preview IMg')

    def getColorMaskedImg(self, img, redThresholdHue, lowerAreaThresholdDeci, upperAreaThresholdDeci):
        # Convert the image to the HSV color space
        hsv_image = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)

        # Define the range for the red color in HSV
        lower_red1 = np.array([0, 100, 100])
        upper_red1 = np.array([redThresholdHue, 255, 255])
        lower_red2 = np.array([180 - redThresholdHue, 100, 100])
        upper_red2 = np.array([180, 255, 255])

        # Threshold the image to get only red colors
        mask1 = cv2.inRange(hsv_image, lower_red1, upper_red1)
        mask2 = cv2.inRange(hsv_image, lower_red2, upper_red2)
        mask = cv2.bitwise_or(mask1, mask2)

        # self.showImg(mask, 'Masked no filter')

        # Apply morphological operations to remove noise
        # kernel = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (5, 5))
        # mask = cv2.morphologyEx(mask, cv2.MORPH_CLOSE, kernel)
        # mask = cv2.morphologyEx(mask, cv2.MORPH_OPEN, kernel)


        # Binary
        _, binaryImg = cv2.threshold(mask, 127, 255, cv2.THRESH_BINARY)

        # Contours
        contours, _ = cv2.findContours(binaryImg, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

        # Remove large detections
        imgArea = binaryImg.shape[0] * binaryImg.shape[1]
        imgAreaThreshLower = lowerAreaThresholdDeci * imgArea
        imgAreaThreshUpper = upperAreaThresholdDeci * imgArea

        areaFilteredContours = []

        areaMask = np.ones(binaryImg.shape, dtype="uint8") * 255
        for contour in contours:
            area = cv2.contourArea(contour)
            if imgAreaThreshLower <= area <= imgAreaThreshUpper:
                cv2.drawContours(areaMask, [contour], -1, (0, 0, 0), -1)
                areaFilteredContours.append(contour)

        areaMask = cv2.bitwise_not(areaMask)
        areaMaskedImg = cv2.bitwise_and(binaryImg, areaMask)


        # Brightest Cluster
        brightestContour = None
        brightestClusterVal = -1
        brightestContourImg = np.zeros(areaMaskedImg.shape, dtype=np.uint8)

        for contour in areaFilteredContours:
            brightness = getContourBrightness(contour, hsv_image)
            if brightness > brightestClusterVal:
                brightestClusterVal = brightness
                brightestContour = contour
        if brightestContour is not None:
            cv2.drawContours(brightestContourImg, [brightestContour], -1, (255, 255, 255), -1)

        # self.previewBrightnesses(img, areaFilteredContours, hsv_image)

        resultImg = cv2.bitwise_and(areaMaskedImg, brightestContourImg)

        # Find white pixel coords
        white_pixel_coordinates = np.column_stack(np.where(resultImg == 255))

        # Calculate the median position of the red pixels
        xPoints = []
        yPoints = []
        if len(white_pixel_coordinates) > 0:
            for point in white_pixel_coordinates:
                xPoints.append(point[1])
                yPoints.append(point[0])

            median_x = np.median(xPoints)
            median_y = np.median(yPoints)
            return int(median_x), int(median_y), brightestClusterVal, resultImg
        else:
            return 0, 0, 0, resultImg


        # # Define thresholds
        # lowerThresholdsRGB = lowerThresholdsL
        # upperThresholdsRGB = lowerThresholdsH
        #
        # # Convert to RGB
        # imgRGB = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        #
        # # Mask red
        # mask = cv2.inRange(imgRGB, lowerThresholdsRGB, upperThresholdsRGB)
        #
        # # Morphological Operations Noise removal
        # kernel = np.ones((5, 5), np.uint8)
        # mask = cv2.morphologyEx(mask, cv2.MORPH_CLOSE, kernel)
        # mask = cv2.morphologyEx(mask, cv2.MORPH_OPEN, kernel)
        #
        # # Apply mask to preview
        # imgResult = cv2.bitwise_and(img, img, mask=mask)
        # # imgResult = cv2.cvtColor(imgResult, cv2.COLOR_BGR2RGB)
        #
        # return imgResult














