// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.MotionConstants;

public class Arm extends SubsystemBase {
  /*  Declaring Variables */
  /*    CANSparkMaxs */
  //      Shoulder Motors
  private CANSparkMax shoulderLeft;
  private CANSparkMax shoulderRight;
  //      Telescoping Arm Motor
  private CANSparkMax telescopeMotor;

  /*    Encoders */
  //      Shoulder Encoders
  private RelativeEncoder shoulderEncoderLeft;
  private RelativeEncoder shoulderEncoderRight;
  //      Telescoping Arm Encoder
  private RelativeEncoder telescopeEncoder;

  /*    PID */
  //      Shoulder PID Controller
  private SparkPIDController shoulderRightPID;
  private SparkPIDController shoulderLeftPID;

  double kP;
  double kI;
  double kD;
  double kF;


  /** Creates a new Arm. */
  public Arm() {
    /*  Initalizing Variables */
    /*    CANSparkMaxs */
    //      Shoulder Motors
    shoulderLeft = new CANSparkMax(ArmConstants.SHOULDER_LEFT_ID, MotorType.kBrushless);
    shoulderRight = new CANSparkMax(ArmConstants.SHOULDER_RIGHT_ID, MotorType.kBrushless);
    //      Telescoping Arm Motor
    telescopeMotor = new CANSparkMax(ArmConstants.TELESCOPE_MOTOR_ID, MotorType.kBrushless);

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
    //      Shoulder PID
    shoulderLeftPID = shoulderLeft.getPIDController();
    shoulderRightPID = shoulderRight.getPIDController();

    //  Motor direction
    shoulderRight.setInverted(true);

    //  Set Idle Mode of Motors
    shoulderLeft.setIdleMode(IdleMode.kBrake);
    shoulderRight.setIdleMode(IdleMode.kBrake);
    telescopeMotor.setIdleMode(IdleMode.kBrake);

    
    kP = 0.22;
    kI = 0.0;
    kD = 0.0;
    kF = 0.0;

    shoulderLeftPID.setP(kP);
    shoulderLeftPID.setI(kI);
    shoulderLeftPID.setD(kD);
    shoulderLeftPID.setFF(kF);
    
    shoulderRightPID.setP(kP);
    shoulderRightPID.setI(kI);
    shoulderRightPID.setD(kD);
    shoulderRightPID.setFF(kF);

    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("FF Gain", kF);
    
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
    shoulderLeftPID.setReference(position, ControlType.kPosition);
    shoulderRightPID.setReference(position, ControlType.kPosition);
    
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

    SmartDashboard.putNumber("Telescope Encoder Value", getTelescopePosition());
    SmartDashboard.putNumber("Left Shoulder Encoder Value", getShoulderLeftPosition());
    SmartDashboard.putNumber("Right Shoulder Encoder Value", getShoulderRightPosition());
    
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double f = SmartDashboard.getNumber("FF Gain", 0);

    if((p != kP)) { shoulderRightPID.setP(p); shoulderLeftPID.setP(p); kP = p; }
    if((i != kI)) { shoulderRightPID.setI(i); shoulderLeftPID.setI(i); kI = i; }
    if((d != kD)) { shoulderRightPID.setD(d); shoulderLeftPID.setD(d); kD = d; }
    if((f != kF)) { shoulderRightPID.setFF(f); shoulderLeftPID.setFF(f); kF = f; }

    
  }
}
