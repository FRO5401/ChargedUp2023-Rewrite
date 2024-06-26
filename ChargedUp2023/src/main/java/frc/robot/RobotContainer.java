// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Commands.MoveArm;
import frc.robot.Commands.ToggleClaw;
import frc.robot.Commands.ToggleGear;
import frc.robot.Commands.ToggleCompressor;
import frc.robot.Subsystems.Arm;
import frc.robot.Subsystems.Claw;
import frc.robot.Subsystems.Compresor;
import frc.robot.Commands.XboxMove;
import frc.robot.Subsystems.Drivebase;

public class RobotContainer {
  /*  Variables */
  //    Controllers
  CommandXboxController driver = Controls.driver;
  CommandXboxController operator = Controls.operator;

  //    Subsystems
  Compresor compresor = new Compresor();
  Drivebase drivebase = new Drivebase();
  Claw claw = new Claw();
  Arm arm = new Arm();

  public RobotContainer() {
    /* Setting Default Commands */
    drivebase.setDefaultCommand(new XboxMove(drivebase));
    arm.setDefaultCommand(new MoveArm(arm));

    /* Configuring bindings */
    configureBindings();
  }

  private void configureBindings() {
    /*  Commands */
    //    Operator
    operator.x().onTrue(new ToggleCompressor(compresor));
    operator.y().onTrue(new ToggleClaw(claw));
    //    Driver
    driver.start().onTrue(new ToggleGear(drivebase));
    
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
