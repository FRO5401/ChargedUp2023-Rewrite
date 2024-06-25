// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

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
  private RelativeEncoder shoulderEncoder1;
  private RelativeEncoder shoulderEncoder2;
  //      Telescoping Arm Encoder
  private RelativeEncoder telescopeEncoder;

  /** Creates a new Arm. */
  public Arm() {
    /*  Declaring Variables */
    /*    CANSparkMaxs */
    //      Shoulder Motors
    shoulderLeft = new CANSparkMax(ArmConstants.SHOULDER_Left_ID, MotorType.kBrushless);
    shoulderRight = new CANSparkMax(ArmConstants.SHOULDER_Right_ID, MotorType.kBrushless);
    //      Telescoping Arm Motor
    telescopeMotor = new CANSparkMax(ArmConstants.TELESCOPE_MOTOR_ID, MotorType.kBrushless);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
