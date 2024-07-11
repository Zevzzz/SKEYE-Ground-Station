// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.util.ShuffleboardIO;
import frc.util.Util;

public class Pitch extends SubsystemBase {
  /** Creates a new Pitch. */
  Servo servo = new Servo(Constants.RobotMap.PITCH_SERVO_MOTOR_PIN);
  private double targetAngleDeg = 0.0;

  public Pitch() {
    ShuffleboardIO.addSlider("Pitch Target Angle Deg [PI]", -90, 90, 0);
  }

  /**
   * @param angleDeg Target Angle for the Servo from -90 to 90 deg
   */
  public void setTargetAngleDeg(double angleDeg) {
    double boundedDeg = Util.bindMinMax(angleDeg, 0.0, 65.0);
    boundedDeg += 90;

    targetAngleDeg = boundedDeg;
  }

  public double getTargetAngleDeg() {
      return targetAngleDeg;
  }



  public void runServoToTargetAngle() {
    servo.setAngle(targetAngleDeg);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Pitch Deg Target [AZ]", targetAngleDeg);
  }
}
