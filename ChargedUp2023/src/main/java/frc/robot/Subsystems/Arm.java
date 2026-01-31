// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import java.util.function.DoubleSupplier;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.MotionConstants;

// Smart Dashboard Tuning
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends SubsystemBase {
  /*  Declaring Variables */
  /*    Spark Maxes */
  //      Shoulder Motors
  private SparkMax shoulderLeft;
  private SparkMax shoulderRight;
  //      Telescoping Arm Motor
  private SparkMax telescopeMotor;

  /*    Spark Configs */
  //      Shoulder Configs
  private SparkMaxConfig shoulderConfigLeft;
  private SparkMaxConfig shoulderConfigRight;
  //      Telescoping Arm Configs
  private SparkMaxConfig telescopeConfig;

  /*    Encoders */
  //      Shoulder Encoders
  private RelativeEncoder shoulderEncoderLeft;
  private RelativeEncoder shoulderEncoderRight;
  //      Telescoping Arm Encoder
  private RelativeEncoder telescopeEncoder;

  /*    PID */
  //      Shoulder PID Controller
  private SparkClosedLoopController shoulderRightPID;
  private SparkClosedLoopController shoulderLeftPID;

  /* SmartDashBoard Tuning
  double kP;
  double kI;
  double kD;
  double kF;
  */

  /** Creates a new Arm. */
  public Arm() {
    /*  Initalizing Variables */
    /*    Spark Maxes */
    //      Shoulder Motors
    shoulderLeft = new SparkMax(ArmConstants.SHOULDER_LEFT_ID, MotorType.kBrushless);
    shoulderRight = new SparkMax(ArmConstants.SHOULDER_RIGHT_ID, MotorType.kBrushless);
    //      Telescoping Arm Motor
    telescopeMotor = new SparkMax(ArmConstants.TELESCOPE_MOTOR_ID, MotorType.kBrushless);

    /*    Spark Configs */
    //      Shoulder Configs
    shoulderConfigLeft = new SparkMaxConfig();
    shoulderConfigRight = new SparkMaxConfig();
    //      Telescoping Arm Configs
    telescopeConfig = new SparkMaxConfig();

    /*      Configuring Configs */
    //      Shoulder Configs
    shoulderConfigLeft
      .inverted(false)
      .idleMode(IdleMode.kBrake);
    shoulderConfigRight
      .inverted(true)
      .idleMode(IdleMode.kBrake);
    //      Telescoping Arm Configs
    telescopeConfig
      .inverted(false)
      .idleMode(IdleMode.kBrake);

    /*    Encoders  */
    //      Shoulder Motors
    shoulderEncoderLeft = shoulderLeft.getEncoder();
    shoulderEncoderRight = shoulderRight.getEncoder();
    //      Telescoping Arm Motor
    telescopeEncoder = telescopeMotor.getEncoder();

    /*    Default Set Position For Encoders  */
    //      Shoulder Motors
    shoulderEncoderLeft.setPosition(MotionConstants.NO_POWER_PERCENT);
    shoulderEncoderRight.setPosition(MotionConstants.NO_POWER_PERCENT);
    //      Telescoping Arm Motor
    telescopeEncoder.setPosition(MotionConstants.NO_POWER_PERCENT);

    /*    PID */
    //      PID Values
    shoulderConfigLeft.closedLoop
      .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
      .pid(ArmConstants.kP, ArmConstants.kI, ArmConstants.kI);
    shoulderConfigLeft.closedLoop
      .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
      .pid(ArmConstants.kP, ArmConstants.kI, ArmConstants.kI);

    /*      Configuring motors */
    shoulderLeft.configure(shoulderConfigLeft, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    shoulderRight.configure(shoulderConfigRight, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    telescopeMotor.configure(telescopeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
    /*  SmartDashBoard Tuning */
    /* 
    //    Sets PID Variables Value
    kP = ArmConstants.kP;
    kI = ArmConstants.kI;
    kD = ArmConstants.kD;
    kF = ArmConstants.kF;    

    //    Set PID Controller
    shoulderLeftPID.setP(kP);
    shoulderLeftPID.setI(kI);
    shoulderLeftPID.setD(kD);
    shoulderLeftPID.setFF(kF);
    
    shoulderRightPID.setP(kP);
    shoulderRightPID.setI(kI);
    shoulderRightPID.setD(kD);
    shoulderRightPID.setFF(kF);

    //    Displays PID to SmartDashBoard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("FF Gain", kF);
    */
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // SmartDashboard.putNumber("Telescope Encoder Value", getTelescopePosition());
    // SmartDashboard.putNumber("Left Shoulder Encoder Value", getShoulderLeftPosition());
    // SmartDashboard.putNumber("Right Shoulder Encoder Value", getShoulderRightPosition());
    
    /*  PID Tuning */
    /*
    //    Gets Values from SmartDashBoard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double f = SmartDashboard.getNumber("FF Gain", 0);

    //  If the Value Changes Update the PID Values
    if((p != kP)) { shoulderRightPID.setP(p); shoulderLeftPID.setP(p); kP = p; }
    if((i != kI)) { shoulderRightPID.setI(i); shoulderLeftPID.setI(i); kI = i; }
    if((d != kD)) { shoulderRightPID.setD(d); shoulderLeftPID.setD(d); kD = d; }
    if((f != kF)) { shoulderRightPID.setFF(f); shoulderLeftPID.setFF(f); kF = f; }
    */
  }

  /*    In-Line Commands */

  //  Default command to move the arm
  public Command moveArm(DoubleSupplier telescopePowerSupplier, DoubleSupplier rotationPowerSupplier){
    return runOnce(()->{
      /*  Variables */
      //    Get Inputs
      double telescopePower = telescopePowerSupplier.getAsDouble();
      double rotationPower = DriveConstants.PRECISION_PERCENT * -1 * rotationPowerSupplier.getAsDouble();

      /*  Moving Arm */
      // Telescoping arm
      telescopeMotor.set(telescopePower);

      //Rotating arm
      shoulderRight.set(rotationPower);
      shoulderLeft.set(rotationPower);

      // /*  Limits on telescoping arm */
      // //  If Telescope Arm Passes Max Extension; Stop
      // if (telescopeEncoder.getPosition() > ArmConstants.TELESCOPE_MAX_EXTENSION && telescopePower > 0){
      //   telescopeMotor.set(MotionConstants.NO_POWER_PERCENT);
      // } 
      // //  If Telescope Arm Before Min Extension; Stop
      // else if (telescopeEncoder.getPosition() < ArmConstants.TELESCOPE_MIN_EXTENSION && telescopePower < 0) {
      //   telescopeMotor.set(MotionConstants.NO_POWER_PERCENT);
      // } 
      // //  Move Telescope Arm
      // else {
      //   telescopeMotor.set(telescopePower);
      // }
    
      // /*    Limits on shoulder rotation  */
      // //  If Shoulder Rotation Passes Max Rotation; Stop
      // if (shoulderEncoderRight.getPosition() > ArmConstants.SHOULDER_MAX_ROTATION && rotationPower > 0){
      //   shoulderRight.set(MotionConstants.NO_POWER_PERCENT);
      //   shoulderLeft.set(MotionConstants.NO_POWER_PERCENT);
      // } 
      // //  If Shoulder Rotation Before Min Rotation; Stop
      // else if (shoulderEncoderRight.getPosition() < ArmConstants.SHOULDER_MIN_ROTATION && rotationPower < 0) {
      //   shoulderRight.set(MotionConstants.NO_POWER_PERCENT);
      //   shoulderLeft.set(MotionConstants.NO_POWER_PERCENT);
      // } 
      // //  Move Shoulder
      // else {
      // //Rotating arm
      //   shoulderRight.set(rotationPower);
      //   shoulderLeft.set(rotationPower);    
      // }
    });
  }
  
  // Moves the arm to a right angle on the left
  public Command leftAngle(){
    return runOnce(()->{
      shoulderLeftPID.setSetpoint(Constants.ArmConstants.LEFT_ANGLE, ControlType.kPosition);
      shoulderRightPID.setSetpoint(Constants.ArmConstants.LEFT_ANGLE, ControlType.kPosition);
    });
  }

    // Moves the arm to a right angle on the right
  public Command rightAngle(){
    return runOnce(()->{
      shoulderLeftPID.setSetpoint(Constants.ArmConstants.RIGHT_ANGLE, ControlType.kPosition);
      shoulderRightPID.setSetpoint(Constants.ArmConstants.RIGHT_ANGLE, ControlType.kPosition);
    });
  }
}
