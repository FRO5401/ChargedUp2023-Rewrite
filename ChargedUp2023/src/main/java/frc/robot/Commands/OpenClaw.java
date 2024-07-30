// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Claw;

public class OpenClaw extends Command {
  //  Declaring Variables
  private Claw claw;
  private boolean endCommand;

  /** Creates a new OpenClaw. */
  public OpenClaw(Claw m_claw) {
    //  Makes local variable equal to global variable
    claw = m_claw;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(claw);  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    endCommand = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    claw.open();
    endCommand = true;
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
