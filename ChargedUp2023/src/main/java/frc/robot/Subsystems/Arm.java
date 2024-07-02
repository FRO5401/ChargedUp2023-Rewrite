// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

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

    //  Motor direction
    shoulderRight.setInverted(true);

    //  Set Idle Mode of Motors
    shoulderLeft.setIdleMode(IdleMode.kBrake);
    shoulderRight.setIdleMode(IdleMode.kBrake);
    telescopeMotor.setIdleMode(IdleMode.kBrake);
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
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putNumber("Telescope Encoder Value", getTelescopePosition());
    SmartDashboard.putNumber("Left Shoulder Encoder Value", getShoulderLeftPosition());
    SmartDashboard.putNumber("Right Shoulder Encoder Value", getShoulderRightPosition());

  }
}
