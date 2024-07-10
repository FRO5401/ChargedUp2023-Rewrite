// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
/*  Imports */
//    REV
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
//    WPI
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
//    Robot
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.PneumaticsConstants;

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

  /*    Soleniods */
  private Solenoid gearShifter;
  boolean isHighGear;

  /** Creates a new Drivebase. */
  public Drivebase() {
    /*    Initializing Variables */
    /*      CANSparkMaxs */
    //        Left Drive Motors
    leftDrive1 = new CANSparkMax(DriveConstants.LEFT_DRIVE_1_ID, MotorType.kBrushless);
    leftDrive2 = new CANSparkMax(DriveConstants.LEFT_DRIVE_2_ID, MotorType.kBrushless);
    leftDrive3 = new CANSparkMax(DriveConstants.LEFT_DRIVE_3_ID, MotorType.kBrushless);
    //        Right Drive Motors
    rightDrive1 = new CANSparkMax(DriveConstants.RIGHT_DRIVE_1_ID, MotorType.kBrushless);
    rightDrive2 = new CANSparkMax(DriveConstants.RIGHT_DRIVE_2_ID, MotorType.kBrushless);
    rightDrive3 = new CANSparkMax(DriveConstants.RIGHT_DRIVE_3_ID, MotorType.kBrushless);

    /*      Encoders */
    //        Left Drive Encoders
    leftEncoder1 = leftDrive1.getEncoder();
    leftEncoder2 = leftDrive2.getEncoder();
    leftEncoder3 = leftDrive3.getEncoder();
    //        Right Drive Encoders
    rightEncoder1 = rightDrive1.getEncoder();
    rightEncoder2 = rightDrive2.getEncoder();
    rightEncoder3 = rightDrive3.getEncoder();

    /*      Solenoid */
    gearShifter = new Solenoid(PneumaticsConstants.CTREPCM_ID, 
        PneumaticsModuleType.CTREPCM, 
        PneumaticsConstants.GEARSHIFTER_CHANNEL);
    isHighGear = false;

    /*    Restore Factory Defaults */
    //      leftDrive Restore
    leftDrive1.restoreFactoryDefaults();
    leftDrive2.restoreFactoryDefaults();
    leftDrive3.restoreFactoryDefaults();
    //      rightDrive Restore
    rightDrive1.restoreFactoryDefaults();
    rightDrive2.restoreFactoryDefaults();
    rightDrive3.restoreFactoryDefaults();

    //    Inverts left motors direction 
    leftDrive1.setInverted(true);
    leftDrive2.setInverted(true);
    leftDrive3.setInverted(true);

    /*  Setting Idle Mode */
    //    Left Drive
    leftDrive1.setIdleMode(IdleMode.kBrake);
    leftDrive2.setIdleMode(IdleMode.kBrake);
    leftDrive3.setIdleMode(IdleMode.kBrake);
    //    Right Drive
    rightDrive1.setIdleMode(IdleMode.kBrake);
    rightDrive2.setIdleMode(IdleMode.kBrake);
    rightDrive3.setIdleMode(IdleMode.kBrake);

     /*  Setting Current Limits */
    //    Left Drive
    leftDrive1.setSmartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT);
    leftDrive2.setSmartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT);
    leftDrive3.setSmartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT);
    //    Right Drive
    rightDrive1.setSmartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT);
    rightDrive2.setSmartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT);
    rightDrive3.setSmartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT);

    /*    Motor Groupings */
    //      Sets leftDrive motors to follow leftDrive1
    leftDrive2.follow(leftDrive1);
    leftDrive3.follow(leftDrive1);
    //      Set rightDrive motors to follow rightDrive1
    rightDrive2.follow(rightDrive1);
    rightDrive3.follow(rightDrive1);

  }
  //    move Command
  public void move(double left, double right){
    //    Sets speeds of motors
    leftDrive1.set(left);
    rightDrive1.set(right);
  }

  public void invertGear(){
    isHighGear = !isHighGear;
    gearShifter.set(isHighGear);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
