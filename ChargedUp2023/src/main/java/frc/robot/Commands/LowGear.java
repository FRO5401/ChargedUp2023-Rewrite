// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Drivebase;

public class LowGear extends Command {
  private Drivebase gearShifter;
  boolean endCommand;

  /** Creates a new HighGear. */
  public LowGear(Drivebase m_gearShifter) {
    gearShifter = m_gearShifter;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(gearShifter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    endCommand = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    gearShifter.lowGear();

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
