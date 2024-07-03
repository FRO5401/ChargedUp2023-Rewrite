// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Commands.ToggleCone;
import frc.robot.Commands.ToggleCube;
import frc.robot.Commands.OpenClaw;
import frc.robot.Commands.ToggleCompressor;
import frc.robot.Subsystems.Claw;
import frc.robot.Subsystems.Compresor;
import frc.robot.Commands.XboxMove;
import frc.robot.Subsystems.Drivebase;

public class RobotContainer {
  /* Variables */
  // Controllers
  CommandXboxController driver = Controls.driver;
  CommandXboxController operator = Controls.operator;
  // Subsystems
  Compresor compresor = new Compresor();
  Drivebase drivebase = new Drivebase();
  Claw claw = new Claw();

  public RobotContainer() {
    drivebase.setDefaultCommand(new XboxMove(drivebase));
    configureBindings();
  }

  private void configureBindings() {
    operator.x().onTrue(new ToggleCompressor(compresor));
    operator.y().onTrue(new ToggleCone(claw));
    operator.b().onTrue(new ToggleCube(claw));
    operator.a().onTrue(new OpenClaw(claw));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
