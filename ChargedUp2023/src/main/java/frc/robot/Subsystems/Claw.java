// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

/*  Imports */
//    WPI
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//    Robot
import frc.robot.Constants.PneumaticsConstants;
import frc.robot.Constants.ClawConstants;

public class Claw extends SubsystemBase {
  /*  Declaring Variables */
  /*    Solenoids */
  private Solenoid leftClaw;
  private Solenoid rightClaw;
  /*    Claw Position */
  private boolean open;
  private boolean close;

  /** Creates a new Claw. */
  public Claw() {
    /*    Initializing Variables */
    /*      Soleniods */
    //        Left side
    leftClaw = new Solenoid(PneumaticsConstants.CTREPCM_ID, 
        PneumaticsModuleType.CTREPCM, ClawConstants.LEFT_CLAW_CHANNEL);
    //        Right side
    rightClaw = new Solenoid(PneumaticsConstants.CTREPCM_ID, 
        PneumaticsModuleType.CTREPCM, ClawConstants.RIGHT_CLAW_CHANNEL);
        
    /*      Positions */
    //        Soleniod open position
    open = false; //flipped tubed backward
    //        Soleniod close position
    close = true; //flipped tubed backward
    
  }
  //  Extends both soleniods to open the claw
  public void open(){
    leftClaw.set(open);
    rightClaw.set(open);
  }
  //  Retracts both soleniods to open the claw
  public void ConeGrab(){
    leftClaw.set(close);
    rightClaw.set(close);
  }

  public void CubeGrab(){
    leftClaw.set(close);
    rightClaw.set(open);
  }

  //  Return if the left Claw is Extended or Retracted
  public boolean getLeftState(){
    return leftClaw.get();
  }
  //  Return if the right Claw is Extended or Retracted
  public boolean getRightState(){
    return rightClaw.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
