// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.RunAzimuthWithSlider;
import frc.robot.commands.RunPitchToTarget;
import frc.robot.subsystems.Azimuth;
import frc.robot.subsystems.Pitch;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  Azimuth azimuth = new Azimuth();
  Pitch pitch = new Pitch();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new ParallelCommandGroup(
      new RunAzimuthWithSlider(azimuth),
      new RunPitchToTarget(pitch)
    );
  }
}
