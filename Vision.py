
import cv2
import numpy as np

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

    def getColorMaskedImg(self, img, redThresholdHue):
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

        # Apply morphological operations to remove noise
        kernel = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (5, 5))
        mask = cv2.morphologyEx(mask, cv2.MORPH_CLOSE, kernel)
        mask = cv2.morphologyEx(mask, cv2.MORPH_OPEN, kernel)

        white_pixel_coordinates = np.column_stack(np.where(mask == 255))
        # print(white_pixel_coordinates)

        # Find the contours of the red areas
        # contours, _ = cv2.findContours(mask, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

        # Calculate and print the area of each contour
        # contour_areas = []
        # for contour in contours:
        #     area = cv2.contourArea(contour)
        #     contour_areas.append(area)

        #
        # # Extract all the points in the contours
        # points = []
        # for contour in contours:
        #     for point in contour:
        #         points.append(point[0])

        # Calculate the median position of the red pixels
        xPoints = []
        yPoints = []
        if len(white_pixel_coordinates) > 0:
            for point in white_pixel_coordinates:
                xPoints.append(point[1])
                yPoints.append(point[0])

            median_x = np.median(xPoints)
            median_y = np.median(yPoints)
            return int(median_x), int(median_y), mask
        else:
            return 0, 0, mask


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













