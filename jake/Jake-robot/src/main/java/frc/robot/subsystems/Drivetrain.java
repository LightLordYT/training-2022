// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  private static final class Config {
    public static final int kRightPrimaryMotorID = 0;
    public static final int kLeftPrimaryMotorID = 1;
    public static final int kRightSecondaryMotorID = 2;
    public static final int kLeftSecondaryMotorID = 3;
  }
  /** Creates a new Drivetrain.  */
  private WPI_TalonFX m_rightPrimaryMotor = new WPI_TalonFX(Config.kRightPrimaryMotorID);
  // secondary motor
  private WPI_TalonFX m_rightSecondaryMotor = new WPI_TalonFX(Config.kRightSecondaryMotorID);

  // i creat motr 2 (left)

  private WPI_TalonFX m_leftPrimaryMotor = new WPI_TalonFX(Config.kLeftPrimaryMotorID);
  // secondary motor
  private WPI_TalonFX m_leftSecondaryMotor = new WPI_TalonFX(Config.kLeftSecondaryMotorID);

  public Drivetrain() {
    m_leftSecondaryMotor.follow(m_leftPrimaryMotor);
    m_rightSecondaryMotor.follow(m_rightPrimaryMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
