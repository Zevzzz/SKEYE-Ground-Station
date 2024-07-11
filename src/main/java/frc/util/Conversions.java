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
}
