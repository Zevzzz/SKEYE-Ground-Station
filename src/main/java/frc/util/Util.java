// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.util;

import frc.robot.Constants;

/** Add your docs here. */
public class Util {
    public static int bindMinMax(int value, int min, int max) {
        if (value > max) {
            return max;
        } else if (value < min) {
            return min;
        } else {
            return value;
        }
    }

    public static int bindMinMax(int value, int minmaxABS) {
        return bindMinMax(value, -minmaxABS, minmaxABS);
    }


    public static double bindMinMax(double value, double min, double max) {
        if (value > max) {
            return max;
        } else if (value < min) {
            return min;
        } else {
            return value;
        }
    }

    public static double bindMinMax(double value, double minmaxABS) {
        return bindMinMax(value, -minmaxABS, minmaxABS);
    }


    public static double deadband(double value, double deadband) {
        if (Math.abs(value) <= deadband) {
            return 0;
        } else {
            return value;
        }
    }

    


}
