// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.RunAzimuthWithSlider;
import frc.robot.commands.RunMotorsWithController;
import frc.robot.commands.RunPitchWithSlider;
import frc.robot.commands.RunScan;
import frc.robot.commands.Track;
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

  CommandXboxController controller = new CommandXboxController(Constants.Controlllers.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    
  }

  public CommandXboxController getController() {
    return controller;
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new ParallelCommandGroup(
      // new RunAzimuthWithSlider(azimuth),
      // new RunPitchWithSlider(pitch)
      // new RunScan(azimuth, pitch)
      // new RunMotorsWithController(azimuth, pitch, () -> controller.getLeftX(), () -> controller.getRightY()),
      new Track(azimuth, pitch)
    );
  }
}
