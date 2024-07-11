// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
  public static class Vision {
    public static final double kP = 1.0;
    public static final double kI = 0.0;
    public static final double kD = 0.0;

  }

  public static class Azimuth {
    public static final int kTicksPerRotFull = 200;

    public static final int kMaxMotorOutputTPS = 8;

    public static final double kP = 1.0;
    public static final double kI = 0.0;
    public static final double kD = 0.0;
    
  }

  public static class Pitch {
    
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
