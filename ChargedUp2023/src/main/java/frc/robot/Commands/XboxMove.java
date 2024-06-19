// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Controls;
import frc.robot.Constants.ControlConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Subsystems.Drivebase;

public class XboxMove extends Command {
  /** Creates a new XboxMove. */

  Drivebase drivebase;
  public XboxMove(Drivebase m_drivebase) {
    drivebase = m_drivebase;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //getting constant values
    int fullPower = DriveConstants.FULL_POWER_PERCENT;
    int noPower = DriveConstants.NO_POWER_PERCENT;
    int negPower = DriveConstants.NEGATIVE_POWER;
    double sensitivity = ControlConstants.CONTROLLER_SENSITIVITY;
    XboxController driver = Controls.xbox_driver;

    //getting inputs
    double throttle = driver.getRightTriggerAxis();
    double reverse = negPower * driver.getLeftTriggerAxis();
    boolean pirouette = driver.getLeftStickButton();
    boolean precision = driver.getRightBumper();
    boolean stop = driver.getLeftBumper();

    // if stopped
    double percent;
    if (stop){
      percent = noPower;
    }
    // if precision is pressed
    if (precision){
      percent = DriveConstants.PRECISION_PERCENT;
    }

    // calculating power + gettnig turn
    double power = (throttle + reverse) * percent;
    double turn = driver.getLeftX() * percent;

    // moving forward
    if (throttle >= sensitivity && Math.abs(reverse) <= sensitivity){
      drivebase.move(power*(fullPower+turn), power*(fullPower-turn));
    }
    // moving backward
    else if (throttle <= sensitivity && Math.abs(reverse) >= sensitivity){
      drivebase.move(power*(fullPower-turn), power*(fullPower+turn));
    }
    // no movement
    else {
      drivebase.move(noPower, noPower);
    }

    //pirouetting
    double pirouetteTurn = Math.abs(turn);
    if (pirouette){
      // turning left
      if (turn <= sensitivity){
        drivebase.move(negPower * pirouetteTurn, pirouetteTurn);
      }
      // turning right
      if (turn >= sensitivity){
        drivebase.move(pirouetteTurn, negPower * pirouetteTurn);
      }
      // no movement
      else{
        drivebase.move(noPower, noPower);
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
