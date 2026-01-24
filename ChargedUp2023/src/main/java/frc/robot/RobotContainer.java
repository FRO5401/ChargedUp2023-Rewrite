// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Subsystems.Arm;
import frc.robot.Subsystems.Claw;
import frc.robot.Subsystems.Compresor;
import frc.robot.Subsystems.Drivebase;
import frc.robot.Subsystems.LEDLights;

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
  LEDLights ledLights = new LEDLights();

  public RobotContainer() {
    /* Setting Default Commands */
    drivebase.setDefaultCommand(drivebase.XboxMove(
      ()->driver.getRightTriggerAxis(), // DoubleSupplier throttle
      ()->driver.getLeftTriggerAxis(), // DoubleSupplier reverse
      ()->driver.getLeftX(), // DoubleSupplier turn
      ()->driver.leftStick().getAsBoolean(), // BooleanSupplier pirouetting
      ()->driver.rightBumper().getAsBoolean(), // BooleanSupplier isPrecision
      ()->driver.leftBumper().getAsBoolean())); // BooleanSupplier isStopped

    arm.setDefaultCommand(arm.moveArm(()->operator.getRightX(), ()->operator.getLeftY()));

    /* Configuring bindings */
    configureBindings();
  }

  private void configureBindings() {
    /*  Commands */
    //    Operator
    operator.x().onTrue(compresor.toggleCompressor());
    operator.rightTrigger().onTrue(claw.toCone());
    operator.leftTrigger().onTrue(claw.toCube());
    operator.y().onTrue(claw.open());
    // operator.b().whileTrue(arm.rightAngle());
    // operator.x().whileTrue(arm.leftAngle());
    
    //    Driver
    driver.start().onTrue(drivebase.invertGear());
    driver.a().onTrue(ledLights.setRainbow());
    driver.b().onTrue(ledLights.setHexColor(Color.kRed));
    driver.y().onTrue(ledLights.setHexColor(Color.kOrange));
    driver.x().onTrue(ledLights.setHexColor(Color.kYellow));
    driver.povLeft().onTrue(ledLights.setHexColor(Color.kGreen));
    driver.povUp().onTrue(ledLights.setHexColor(Color.kBlue));
    driver.povRight().onTrue(ledLights.setHexColor(Color.kPurple));
    driver.povDown().onTrue(ledLights.setHexColor(Color.kWhite));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
