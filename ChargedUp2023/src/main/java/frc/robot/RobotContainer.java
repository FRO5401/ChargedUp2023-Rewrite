// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Commands.ToggleCompressor;
import frc.robot.Subsystems.Compresor;

public class RobotContainer {
  XboxController operator = Controls.xbox_operator;
  Compresor compresor = new Compresor();
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    operator.x().whileTrue(new ToggleCompressor(compresor));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
