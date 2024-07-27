// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.util;

import frc.robot.Constants;

/** Add your docs here. */
public class Conversions {
    public static double StepperHalfTicks2Degrees(int ticks) {
        return (double) ticks / (Constants.Azimuth.kTicksPerRotFull * 2) * 360.0;
    }

    public static int StepperDegrees2HalfTicks(double deg) {
        double percRot = deg / 360.0;
        int ticks = (int) percRot * Constants.Azimuth.kTicksPerRotFull * 2;
        return ticks;
    }
}
