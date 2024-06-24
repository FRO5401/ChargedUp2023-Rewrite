// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

/*  Imports */
//    WPI
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
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
  //  Enables the compressor
  public void enable(){
    compressor.enableDigital();
  }
  //  Disables the compressor
  public void disable(){
    compressor.disable();
  }
  //  Returns if the compressor is enabled ot disabled
  public boolean getCompressor(){
    return compressor.isEnabled();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
