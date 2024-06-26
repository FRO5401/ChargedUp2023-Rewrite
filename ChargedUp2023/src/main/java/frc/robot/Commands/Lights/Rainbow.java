// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands.Lights;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.LEDLights;

public class Rainbow extends Command {
  private LEDLights ledLights;
  private boolean endCommand;
  /** Creates a new Rainbow. */
  public Rainbow(LEDLights m_ledLights) {
    ledLights = m_ledLights;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(ledLights);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    endCommand = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ledLights.rainbow();
    ledLights.setData();
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
