// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PneumaticsConstants;
import frc.robot.Constants.ClawConstants;

public class Claw extends SubsystemBase {
  /*  Creating Variables */
  /*    Solenoids */
  private Solenoid leftClaw;
  private Solenoid rightClaw;

  /** Creates a new Claw. */
  public Claw() {
    leftClaw = new Solenoid(PneumaticsConstants.CTREPCM_ID, 
        PneumaticsModuleType.CTREPCM, 
        ClawConstants.LEFT_CLAW_CHANNEL);
    rightClaw = new Solenoid(PneumaticsConstants.CTREPCM_ID, 
        PneumaticsModuleType.CTREPCM, 
        ClawConstants.RIGHT_CLAW_CHANNEL);
    
  }
  public void open(){
    leftClaw.set(true);
    rightClaw.set(true);
  }
  public void close(){
    leftClaw.set(false);
    rightClaw.set(false);
  }
  public boolean getState(){
    return leftClaw.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
