// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
  public static class Vision {
    public static final double kPTrackAzimuth = 0.003;
    public static final double kITrackAzimuth = 0.0;
    public static final double kDTrackAzimuth = 0.0;

    public static final double kPTrackPitch = 0.01;
    public static final double kITrackPitch = 0.0;
    public static final double kDTrackPitch = 0.0005;

    public static final double kMinSVForTrackSwitch = 170.0;
    public static final double kMinSVForTrack = 130.0;

  }

  public static class Azimuth {
    public static final int kTicksPerRotFull = 200;

    public static final int kMaxMotorOutputTPS = 2; // 8

    public static final double kP = 1.0;
    public static final double kI = 0.0;
    public static final double kD = 0.0;

    public static final double kAtGoalToleranceDeg = 1.0;
    
  }

  public static class Pitch {
    // public static final double kAtGoalToleranceDeg = 0.5;
    // bruh
    public static final double kFull90DegAngle = 67.0;

    public static final double kBoundedMinDeg = 18.0;
    public static final double kBoundedMaxDeg = 67.0;

    public static final double kBoundedMinDegTrack = 108.0;
    public static final double kBoundedMaxDegTrack = 160.0;
  }


  public static class ScanningSetpoints {
    // Azimuth, Pitch
    public static final Double[][] setpoints = {
      {0.0, 15.0},
      {300.0, 20.0},
      {0.0, 25.0},
      {0.0, 30.0},
      {300.0, 35.0},
      {0.0, 40.0},
      {300.0, 45.0},
      {0.0, 50.0},
      {300.0, 55.0},
      {0.0, 60.0},
      {300.0, 65.0},
      {0.0, 67.0},
    };

    public static final double kWaitTimeBeforePitchMoveSec = 0.15;
    public static final double kWaitTimeBeforeAzimuthMoveSec = 0.5;
  }



  public static class Controlllers {
    public static final int kDriverControllerPort = 0;
  }


  public static class RobotMap {
    public static final int AZIMUTH_STEPPER_MOTOR_PIN_PL = 0;  // DIO 
    public static final int AZIMUTH_STEPPER_MOTOR_PIN_DIR = 1; // DIO
    
    public static final int PITCH_SERVO_MOTOR_PIN = 1;
  }

}
