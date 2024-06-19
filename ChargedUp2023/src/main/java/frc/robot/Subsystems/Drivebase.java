// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivebase extends SubsystemBase {
  /*  Declaring Variables */
  /*    CANSparkMaxs */
  //      Left Drive
  private CANSparkMax leftDrive1;
  private CANSparkMax leftDrive2;
  private CANSparkMax leftDrive3;
  //      Right Drive
  private CANSparkMax rightDrive1;
  private CANSparkMax rightDrive2;
  private CANSparkMax rightDrive3;

  /*    Encoders */
  //      Left Encoder
  private RelativeEncoder leftEncoder1;
  private RelativeEncoder leftEncoder2;
  private RelativeEncoder leftEncoder3;
  //      Right Encoder
  private RelativeEncoder rightEncoder1;
  private RelativeEncoder rightEncoder2;
  private RelativeEncoder rightEncoder3;

  /** Creates a new Drivebase. */
  public Drivebase() {
    /*    Initializing Variables */
    /*      Initializing CANSparkMaxs */
    //        Left Drive Motors
    leftDrive1 = new CANSparkMax(0, MotorType.kBrushless);
    leftDrive2 = new CANSparkMax(0, MotorType.kBrushless);
    leftDrive3 = new CANSparkMax(0, MotorType.kBrushless);
    //        Right Drive Motors
    rightDrive1 = new CANSparkMax(0, MotorType.kBrushless);
    rightDrive2 = new CANSparkMax(0, MotorType.kBrushless);
    rightDrive3 = new CANSparkMax(0, MotorType.kBrushless);

    /*      Initializing Encoders */
    //        Left Drive Encoders
    leftEncoder1 = leftDrive1.getEncoder();
    leftEncoder2 = leftDrive2.getEncoder();
    leftEncoder3 = leftDrive3.getEncoder();
    //        Right Drive Encoders
    rightEncoder1 = rightDrive1.getEncoder();
    rightEncoder2 = rightDrive2.getEncoder();
    rightEncoder3 = rightDrive3.getEncoder();

    /*    Restore Factory Defaults */
    //      leftDrive Restore
    leftDrive1.restoreFactoryDefaults();
    leftDrive2.restoreFactoryDefaults();
    leftDrive3.restoreFactoryDefaults();
    //      rightDrive Restore
    rightDrive1.restoreFactoryDefaults();
    rightDrive2.restoreFactoryDefaults();
    rightDrive3.restoreFactoryDefaults();

    /*    Motor Groupings */
    //      Sets leftDrive motors to follow leftDrive1
    leftDrive2.follow(leftDrive1);
    leftDrive3.follow(leftDrive1);
    //      Set rightDrive motors to follow rightDrive1
    rightDrive2.follow(rightDrive1);
    rightDrive3.follow(rightDrive3);

    //    Inverts left motors direction 
    leftDrive1.setInverted(true);

    //    Sets Idle Mode of motors
    leftDrive1.setIdleMode(IdleMode.kBrake);
    rightDrive1.setIdleMode(IdleMode.kBrake);


  }

  public void move(double left, double right){

    leftDrive1.set(left);
    rightDrive1.set(right);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
