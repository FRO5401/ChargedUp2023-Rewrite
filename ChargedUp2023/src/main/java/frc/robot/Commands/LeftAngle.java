// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Subsystems.Arm;

public class LeftAngle extends Command {
  //  Declaring Variables
  private Arm arm;
  private boolean endCommand;

  /** Creates a new RightAngle. */
  public LeftAngle(Arm m_arm) {
    //  Makes local variable equal to global variable
    arm = m_arm;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    endCommand = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //  Moves Arm to Position
    arm.setPosition(Constants.ArmConstants.LEFT_ANGLE);
    endCommand = false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return endCommand;
  }
}
