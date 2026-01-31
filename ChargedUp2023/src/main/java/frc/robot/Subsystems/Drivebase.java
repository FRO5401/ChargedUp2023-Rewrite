// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
/*  Imports */
//    REV
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.Command;
//    WPI
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.ControlConstants;
//    Robot
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.MotionConstants;
import frc.robot.Constants.PneumaticsConstants;

public class Drivebase extends SubsystemBase {
  /*  Declaring Variables */
  /*    Spark Maxes */
  //      Left Drive
  private SparkMax leftDrive1;
  private SparkMax leftDrive2;
  private SparkMax leftDrive3;
  //      Right Drive
  private SparkMax rightDrive1;
  private SparkMax rightDrive2;
  private SparkMax rightDrive3;

  /*  Spark Configs */
  //      Left Drive
  private SparkMaxConfig leftConfig1;
  private SparkMaxConfig leftConfig2;
  private SparkMaxConfig leftConfig3;
  //      Right Drive
  private SparkMaxConfig rightConfig1;
  private SparkMaxConfig rightConfig2;
  private SparkMaxConfig rightConfig3;


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
    /*      Spark Maxes */
    //        Left Drive Motors
    leftDrive1 = new SparkMax(DriveConstants.LEFT_DRIVE_1_ID, MotorType.kBrushless);
    leftDrive2 = new SparkMax(DriveConstants.LEFT_DRIVE_2_ID, MotorType.kBrushless);
    leftDrive3 = new SparkMax(DriveConstants.LEFT_DRIVE_3_ID, MotorType.kBrushless);
    //        Right Drive Motors
    rightDrive1 = new SparkMax(DriveConstants.RIGHT_DRIVE_1_ID, MotorType.kBrushless);
    rightDrive2 = new SparkMax(DriveConstants.RIGHT_DRIVE_2_ID, MotorType.kBrushless);
    rightDrive3 = new SparkMax(DriveConstants.RIGHT_DRIVE_3_ID, MotorType.kBrushless);

    /*      Spark Configs */
    //        Left Drive Configs
    leftConfig1 = new SparkMaxConfig();
    leftConfig2 = new SparkMaxConfig();
    leftConfig3 = new SparkMaxConfig();
    //        Right Drive Configs
    rightConfig1 = new SparkMaxConfig();
    rightConfig2 = new SparkMaxConfig();
    rightConfig3 = new SparkMaxConfig();

    /*      Configuring Configs */
    //        Left Drive Configs
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
    //        Right Drive Configs
    rightConfig1
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT);    
    rightConfig2
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT)
      .follow(rightDrive1);
    rightConfig3
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(DriveConstants.MOTOR_CURRENT_LIMIT)
      .follow(rightDrive1);

    //        Configuring motors
    leftDrive1.configure(leftConfig1, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    leftDrive2.configure(leftConfig2, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    leftDrive3.configure(leftConfig3, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightDrive1.configure(rightConfig1, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightDrive2.configure(rightConfig2, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightDrive3.configure(rightConfig3, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

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
        DriveConstants.GEARSHIFTER_CHANNEL);
    isHighGear = false;

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /*    In-Line Commands */

  //    Change Gear
  public Command invertGear(){
    return runOnce(()->{
      isHighGear = !isHighGear;
      gearShifter.set(isHighGear);
    });
  }

  //    Drive
  public Command XboxMove(DoubleSupplier throttleSupplier, DoubleSupplier reverseSupplier, DoubleSupplier turnSupplier, BooleanSupplier pirouettingSupplier, BooleanSupplier isPrecisionSupplier, BooleanSupplier isStoppedSupplier){
    return run(()->{
      // Get constant values
      int fullPower = MotionConstants.FULL_POWER_PERCENT;
      int noPower = MotionConstants.NO_POWER_PERCENT;
      int negPower = MotionConstants.NEGATIVE_POWER;
      double sensitivity = ControlConstants.CONTROLLER_SENSITIVITY;

      // Get inputs
      double throttle = throttleSupplier.getAsDouble();
      double reverse = negPower * reverseSupplier.getAsDouble();
      boolean pirouetting = pirouettingSupplier.getAsBoolean();
      boolean isPrecision = isPrecisionSupplier.getAsBoolean();
      boolean isStopped = isStoppedSupplier.getAsBoolean();

      // Get percent modifier
      double percent = fullPower;
      if (isStopped){ percent = noPower; }
      else if (isPrecision){ percent = DriveConstants.PRECISION_PERCENT; }

      // Calculate power + getting turn
      double power = (throttle + reverse) * percent;
      double turn = turnSupplier.getAsDouble() * percent;

      /*    Normal Driving  */
      // Moving forward
      if (throttle >= sensitivity && Math.abs(reverse) <= sensitivity){
        leftDrive1.set(power*(fullPower+turn));
        rightDrive1.set(power*(fullPower-turn));
      }
      // Moving backward
      else if (throttle <= sensitivity && Math.abs(reverse) >= sensitivity){
        leftDrive1.set(power*(fullPower+turn));
        rightDrive1.set(power*(fullPower-turn));
      }
      // No movement
      else {
        leftDrive1.set(noPower);
        rightDrive1.set(noPower);
      }

      /*  Pirouetting */
      double pirouetteTurn = Math.abs(turn);
      if (pirouetting){
        // Turning left
        if (turn <= (negPower * sensitivity)){
          leftDrive1.set(negPower * pirouetteTurn);
          rightDrive1.set(pirouetteTurn);
        }
        // Turning right
        else if (turn >= sensitivity){
          leftDrive1.set(pirouetteTurn);
          rightDrive1.set(negPower * pirouetteTurn);
        }
        // No movement
        else{
          leftDrive1.set(noPower);
          rightDrive1.set(noPower);
        }
      }
    });
  }
}
