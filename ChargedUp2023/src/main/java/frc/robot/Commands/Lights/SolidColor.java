// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands.Lights;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.LEDLights;

public class SolidColor extends Command {
  private LEDLights ledLights;
  private String color;
  private boolean endCommand;
  
  /** Creates a new SolidColor. */
  public SolidColor(LEDLights m_ledLights, String m_color) {
    ledLights = m_ledLights;
    color = m_color;
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
    if (color.equals("RED")){
      ledLights.setRGB(255,0,0);
    }
    else if (color.equals("ORANGE")){
      ledLights.setRGB(255, 165, 0);
    }
    else if (color.equals("YELLOW")){
      ledLights.setRGB(255,255,0);
    }
    else if (color.equals("GREEN")){
      ledLights.setRGB(0,255,0);
    }
    else if (color.equals("BLUE")){
      ledLights.setRGB(0,0,255);
    }
    else if (color.equals("PURPLE")){
      ledLights.setRGB(128,0,128);
    }
    ledLights.setData();
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
