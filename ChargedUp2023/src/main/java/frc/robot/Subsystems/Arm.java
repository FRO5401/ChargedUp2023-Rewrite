// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;
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

  public void rotateArm(double power){
    shoulderRight.set(power);
    shoulderLeft.set(power);
  }

  public void telescopeArm(double power){
    telescopeMotor.set(power);
  }

  public double getTelescopePosition(){
    return telescopeEncoder.getPosition();
  }

  public double getShoulderRightPosition(){
    return shoulderEncoderRight.getPosition();
  }

  public double getShoulderLeftPosition(){
    return shoulderEncoderLeft.getPosition();
  }
  
  public void setPosition(double position){
    shoulderLeftPID.setSetpoint(position, ControlType.kPosition);
    shoulderRightPID.setSetpoint(position, ControlType.kPosition);
    }

  public void rightAngle(){
    if(getShoulderLeftPosition() > 17.5){
      shoulderLeft.set(-0.05);
      shoulderRight.set(-0.05);
    } else {
      shoulderLeft.set(0.7);
      shoulderRight.set(0.7);
    }
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
}
