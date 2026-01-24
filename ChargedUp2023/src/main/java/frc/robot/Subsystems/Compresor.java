// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

/*  Imports */
//    WPI
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//    Robot
import frc.robot.Constants.PneumaticsConstants;

public class Compresor extends SubsystemBase {
  /*  Declaring Variables */
  //    Compressor 
  private Compressor compressor;

  /** Creates a new Compresor. */
  public Compresor() {
    /*    Initializing Variables */
    //      Compressor
    compressor = new Compressor(PneumaticsConstants.CTREPCM_ID, PneumaticsModuleType.CTREPCM);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  /*    In-Line Commands */

  //  Toggles the compressor
  public Command toggleCompressor(){
    return runOnce(()->{   
      if (!compressor.isEnabled()){
        compressor.enableDigital();
      }
      else{
        compressor.disable();
      }
    });
  }
}
