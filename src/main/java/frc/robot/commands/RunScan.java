// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Azimuth;
import frc.robot.subsystems.Pitch;

public class RunScan extends Command {
  int scanStepAZ = 0;
  int scanStepPI = 0;
  int totalScanSteps = Constants.ScanningSetpoints.setpoints.length;
  Double[][] setpoints = Constants.ScanningSetpoints.setpoints;

  Azimuth azimuth;
  Pitch pitch;

  Timer timer = new Timer();
  private double pitchTargetPre = 0;

  /** Creates a new RunScan. */
  public RunScan(Azimuth azimuth, Pitch pitch) {
    this.azimuth = azimuth;
    this.pitch = pitch;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    scanStepAZ = 0;
    scanStepPI = 0;
    
    timer.stop();
    timer.reset();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double azimuthTarget = setpoints[scanStepAZ][0];
    double pitchTarget = setpoints[scanStepPI][1];

    azimuth.setTargetAngleDeg(azimuthTarget);
    pitch.setTargetAngleDeg(pitchTarget);

    azimuth.runToTargetAngle();
    pitch.runToTargetAngle();

    if (azimuth.atGoal()) {
      timer.start();      

      // Pre-move pitch
      // ms before pitch move
      if (timer.get() > Constants.ScanningSetpoints.kWaitTimeBeforePitchMoveSec && scanStepPI == scanStepAZ) {
        scanStepPI++;
        if (scanStepPI >= setpoints.length) {
          scanStepPI = 0;
        }
      }

      // ms before azimuth move
      if (timer.get() > Constants.ScanningSetpoints.kWaitTimeBeforeAzimuthMoveSec) {
        scanStepAZ++;
        if (scanStepAZ >= setpoints.length) {
          scanStepAZ = 0;
        }

        timer.stop();
        timer.reset();
      }

    }
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
