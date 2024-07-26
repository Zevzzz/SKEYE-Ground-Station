// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.swing.text.StyleContext.SmallAttributeSet;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Azimuth;
import frc.robot.subsystems.Pitch;

public class Track extends Command {
  Azimuth azimuth;
  Pitch pitch;
  PIDController pidAzimuth = new PIDController(Constants.Vision.kPTrackAzimuth, Constants.Vision.kITrackAzimuth, Constants.Vision.kDTrackAzimuth);
  PIDController pidPitch = new PIDController(Constants.Vision.kPTrackPitch, Constants.Vision.kITrackPitch, Constants.Vision.kDTrackPitch);

  /** Creates a new RunTrack. */
  public Track(Azimuth azimuth, Pitch pitch) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.azimuth = azimuth;
    this.pitch = pitch;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double tX = SmartDashboard.getNumber("EOS-tX", 0);
    double tY = SmartDashboard.getNumber("EOS-tY", 0);
    double tSV = SmartDashboard.getNumber("EOS-tSV", 0);

    if (tSV >= Constants.Vision.kMinSVForTrack) {
      double newAzimuthTarget = azimuth.getTargetAngleDeg() - pidAzimuth.calculate(tX);
      azimuth.setTargetAngleDeg(newAzimuthTarget);

      double newPitchTarget = pitch.getTargetAngleDeg() + pidPitch.calculate(tY);
      pitch.setTargetAngleUnbounded(newPitchTarget);

      // System.out.println(newAzimuthTarget);
    }

    azimuth.runToTargetAngle();
    pitch.runToTargetAngle();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
