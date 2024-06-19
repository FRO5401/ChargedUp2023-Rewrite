// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Commands.ToggleCompressor;
import frc.robot.Subsystems.Compresor;
import frc.robot.Commands.XboxMove;
import frc.robot.Subsystems.Drivebase;

public class RobotContainer {
  CommandXboxController operator = Controls.operator;
  Compresor compresor = new Compresor();
  Drivebase drivebase = new Drivebase();

  public RobotContainer() {
    drivebase.setDefaultCommand(new XboxMove(drivebase));
    configureBindings();
  }

  private void configureBindings() {
    operator.x().onTrue(new ToggleCompressor(compresor));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
