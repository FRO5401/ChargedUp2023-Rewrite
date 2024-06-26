// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Controls;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.MotorConstants;
import frc.robot.Subsystems.Arm;

public class MoveArm extends Command {
  Arm arm;

  /** Creates a new TraverseArm. */
  public MoveArm(Arm m_arm) {
    arm = m_arm;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*  Variables */
    // Controller
    XboxController operator = Controls.xbox_operator;
    // Controller Inputs
    double telescopePower = operator.getRightX();
    double rotationPower = operator.getLeftY();

    /* Using Arm Functions */
    arm.telescopeArm(telescopePower);
    arm.rotateArm(rotationPower);

    /* So then you dont extend past the telescopearm max or minimum position  
    if (arm.getTelescopePosition() > ArmConstants.TELESCOPE_MAX_EXTENSION){
      arm.telescopeArm(MotorConstants.NO_POWER_PERCENT);
    } 
    else if( arm.getTelescopePosition() < ArmConstants.TELESCOPE_MIN_EXTENSION){
      arm.telescopeArm(MotorConstants.NO_POWER_PERCENT);
    } 
    else {
      arm.telescopeArm(telescopePower);
    }*/
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Setting the motor speeds to 0 when interrupted
    arm.telescopeArm(MotorConstants.NO_POWER_PERCENT);
    arm.rotateArm(MotorConstants.NO_POWER_PERCENT);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
