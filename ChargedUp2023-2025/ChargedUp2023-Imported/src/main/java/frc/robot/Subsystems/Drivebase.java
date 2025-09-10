// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
/*  Imports */
//    REV
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

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
  private SparkMax leftDrive1;
  private SparkMax leftDrive2;
  private SparkMax leftDrive3;
  //      Right Drive
  private SparkMax rightDrive1;
  private SparkMax rightDrive2;
  private SparkMax rightDrive3;

  private SparkMaxConfig leftConfig1;
  private SparkMaxConfig leftConfig2;
  private SparkMaxConfig leftConfig3;
  private SparkMaxConfig rightConfig1;
  private SparkMaxConfig rightConfig2;
  private SparkMaxConfig rightConfig3;


  // /*    Encoders */
  // //      Left Encoder
  // private RelativeEncoder leftEncoder1;
  // private RelativeEncoder leftEncoder2;
  // private RelativeEncoder leftEncoder3;
  // //      Right Encoder
  // private RelativeEncoder rightEncoder1;
  // private RelativeEncoder rightEncoder2;
  // private RelativeEncoder rightEncoder3;

  /*    Soleniods */
  private Solenoid gearShifter;
  boolean isHighGear;

  /** Creates a new Drivebase. */
  public Drivebase() {
    /*    Initializing Variables */
    /*      CANSparkMaxs */
    //        Left Drive Motors
    leftDrive1 = new SparkMax(DriveConstants.LEFT_DRIVE_1_ID, MotorType.kBrushless);
    leftDrive2 = new SparkMax(DriveConstants.LEFT_DRIVE_2_ID, MotorType.kBrushless);
    leftDrive3 = new SparkMax(DriveConstants.LEFT_DRIVE_3_ID, MotorType.kBrushless);
    //        Right Drive Motors
    rightDrive1 = new SparkMax(DriveConstants.RIGHT_DRIVE_1_ID, MotorType.kBrushless);
    rightDrive2 = new SparkMax(DriveConstants.RIGHT_DRIVE_2_ID, MotorType.kBrushless);
    rightDrive3 = new SparkMax(DriveConstants.RIGHT_DRIVE_3_ID, MotorType.kBrushless);

    leftConfig1 = new SparkMaxConfig();
    leftConfig2 = new SparkMaxConfig();
    leftConfig3 = new SparkMaxConfig();
    rightConfig1 = new SparkMaxConfig();
    rightConfig2 = new SparkMaxConfig();
    rightConfig3 = new SparkMaxConfig();

    // Configs
    leftConfig1
      .inverted(true)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT);
    leftConfig2
      .inverted(true)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT)
      .follow(leftDrive1);
    leftConfig3
      .inverted(true)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT)
      .follow(leftDrive1);

      
    rightConfig1
    .inverted(false)
    .idleMode(IdleMode.kBrake)
    .smartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT);
    rightConfig2
    .inverted(false)
    .idleMode(IdleMode.kBrake)
    .smartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT)
    .follow(rightDrive1);
    rightConfig1
    .inverted(false)
    .idleMode(IdleMode.kBrake)
    .smartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT)
    .follow(rightDrive1);

    leftDrive1.configure(leftConfig1, null, null);
    leftDrive2.configure(leftConfig2, null, null);
    leftDrive3.configure(leftConfig3, null, null);
    rightDrive1.configure(rightConfig1, null, null);
    rightDrive2.configure(rightConfig2, null, null);
    rightDrive3.configure(rightConfig3, null, null);


    // /*      Encoders */
    // //        Left Drive Encoders
    // leftEncoder1 = leftDrive1.getEncoder();
    // leftEncoder2 = leftDrive2.getEncoder();
    // leftEncoder3 = leftDrive3.getEncoder();
    // //        Right Drive Encoders
    // rightEncoder1 = rightDrive1.getEncoder();
    // rightEncoder2 = rightDrive2.getEncoder();
    // rightEncoder3 = rightDrive3.getEncoder();

    /*      Solenoid */
    gearShifter = new Solenoid(PneumaticsConstants.CTREPCM_ID, 
        PneumaticsModuleType.CTREPCM, 
        DriveConstants.GEARSHIFTER_CHANNEL);
    isHighGear = false;

  }

  //    Drive Command
  public void move(double left, double right){
    //    Sets speeds of motors
    leftDrive1.set(left);
    rightDrive1.set(right);
  }
  //    Change Gear
  public void invertGear(){
    isHighGear = !isHighGear;
    gearShifter.set(isHighGear);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
