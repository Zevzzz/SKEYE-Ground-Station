// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.util.Conversions;
import frc.util.ShuffleboardIO;
import frc.util.StepperMotor;
import frc.util.Util;

public class Azimuth extends SubsystemBase {
  public enum AzimuthStates {
    STANDBY,
    TRACKING,
    SCANNING
  }

  private double targetDeg = 0.0;

  private StepperMotor stepperMotor = new StepperMotor(Constants.RobotMap.AZIMUTH_STEPPER_MOTOR_PIN_PL, Constants.RobotMap.AZIMUTH_STEPPER_MOTOR_PIN_DIR);
  private PIDController pidController = new PIDController(Constants.Azimuth.kP, Constants.Azimuth.kI, Constants.Azimuth.kD);

  public Azimuth() {
    ShuffleboardIO.addSlider("StepsPerRun [AZ]", 0, 30, 0);
    ShuffleboardIO.addSlider("Target Angle Azimuth [AZ]", 0, 360, 0);
  }

  public int getTicks() {
    return stepperMotor.getSteps();
  }

  public double getAngleDegHalfStep() {
    double deg = Conversions.StepperHalfTicks2Degrees(getTicks());
    deg %= 360.0;
    deg += (deg < 0) ? 360 : 0;
    return deg;
  }

  public void setTargetAngleDeg(double targetDeg) {
    double targetDegFiltered = targetDeg % 360;
    if (targetDegFiltered < 0) {
      targetDegFiltered += 360.0;
    }
    this.targetDeg = targetDegFiltered;
  }
  public double getTargetAngleDeg() {
      return targetDeg;
  }

  public boolean atGoal() {
    return Math.abs(getTargetAngleDeg() - getAngleDegHalfStep()) <= Constants.Azimuth.kAtGoalToleranceDeg;
  }



  public void runAzimuthWithSlider() {
    // This method will be called once per scheduler run
    stepperMotor.stepMulti((int) ShuffleboardIO.getDouble("StepsPerRun [AZ]"));
  }

  public void runToTargetAngle() {
    int motorOutput = (int) pidController.calculate(getAngleDegHalfStep(), targetDeg);
    int motorOutputBounded = Util.bindMinMax(motorOutput, Constants.Azimuth.kMaxMotorOutputTPS);

    stepperMotor.stepMulti(motorOutputBounded);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Stepper Motor Ticks [AZ]", getTicks());
    SmartDashboard.putNumber("Stepper Motor Deg [AZ]", getAngleDegHalfStep());

  }
}
