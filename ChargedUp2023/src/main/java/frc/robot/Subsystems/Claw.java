// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

/*  Imports */
//    WPI
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /*    In-Line Commands */
  // Opens the claw
  public Command open(){
    return runOnce(()->{
      leftClaw.set(open);
      rightClaw.set(open);
    });
  }
  // Opens the claw to the cube position
  public Command toCube(){
    return runOnce(()->{  
      leftClaw.set(close);
      rightClaw.set(open);
    });
  }
  // Opens the claw to the cone position
  public Command toCone(){
    return runOnce(()->{
      leftClaw.set(close);
      rightClaw.set(close);
    });
  }
}
