
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Azimuth;
import frc.robot.subsystems.Pitch;
import frc.util.Util;

public class RunMotorsWithController extends Command {
  /** Creates a new RunMotorsWithController. */
  Supplier<Double> joystickSupplierX, joystickSupplierY;
  Azimuth azimuth;
  Pitch pitch;
  public RunMotorsWithController(Azimuth azimuth, Pitch pitch, Supplier<Double> joystickSupplierX, Supplier<Double> joystickSupplierY) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.joystickSupplierX = joystickSupplierX;
    this.joystickSupplierY = joystickSupplierY;
    this.azimuth = azimuth;
    this.pitch = pitch;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double joyX = joystickSupplierX.get();
    double joyY = joystickSupplierY.get();
    System.out.println(joyX);
    // System.out.println(joyY);

    joyX = Util.deadband(joyX, 0.1);
    joyY = Util.deadband(joyY, 0.1);

    azimuth.setTargetAngleDeg(joystickSupplierX.get() + azimuth.getTargetDeg());
    pitch.setTargetAngleDeg(joystickSupplierY.get() + pitch.getTargetAngleDeg());

    azimuth.runToTargetAngle();
    pitch.runServoToTargetAngle();
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
