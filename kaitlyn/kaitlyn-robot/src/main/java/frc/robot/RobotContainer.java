// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.MoveForTime;
import frc.robot.commands.RunIntake;
import frc.robot.commands.shooting.Launch;
import frc.robot.commands.shooting.Reload;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.intake.Hopper;
import frc.robot.subsystems.intake.IntakeArm;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */ 
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static final class Config {
   public static final int kJoystickID = 0;
   public static final int kIntakeArmInID = 1;
   public static final int kIntakeArmOutID = 2;
   public static final int kIntakeInID = 10;
   public static final int kIntakeOutID = 9;
   public static final int kLaunchButtonID = 8;
   public static final int kReloadButtonID = 7;
   public static final int kClimbInID = 4;
   public static final int kClimbOutID = 3;
   public static final double kSpeed = 0.4;
   public static final double kTimeInSeconds = 3;
  }
  
  //For Drivetrain subsystem
  private Joystick m_joystick = new Joystick(Config.kJoystickID);
  private Drivetrain m_drivetrain = new Drivetrain();
  private ArcadeDrive m_arcadeDrive = new ArcadeDrive(m_drivetrain, m_joystick);

  //For IntakeArm subsystem
  private IntakeArm m_intakeArm = new IntakeArm();
  private Hopper m_hopper = new Hopper();
  private JoystickButton m_intakeArmIn = new JoystickButton(m_joystick, Config.kIntakeArmInID);
  private JoystickButton m_intakeArmOut = new JoystickButton(m_joystick, Config.kIntakeArmOutID);
  private JoystickButton m_intakeIn = new JoystickButton(m_joystick, Config.kIntakeInID);
  private JoystickButton m_intakeOut = new JoystickButton (m_joystick, Config.kIntakeOutID);
  private RunIntake m_runIntake = new RunIntake(m_hopper, m_intakeArm, m_intakeIn, m_intakeOut);

  //For Shooter subsystem
  private Shooter m_shooter = new Shooter();
  private Launch m_launch = new Launch(m_shooter);
  private Reload m_reload = new Reload(m_shooter);
  private JoystickButton m_launchButton = new JoystickButton(m_joystick, Config.kLaunchButtonID);
  private JoystickButton m_reloadButton = new JoystickButton(m_joystick, Config.kReloadButtonID);

  //For Climb subsystem
  // private Climb m_climbArm = new Climb();
  private JoystickButton m_climbIn = new JoystickButton(m_joystick, Config.kClimbInID);
  private JoystickButton m_climbOut = new JoystickButton(m_joystick, Config.kClimbOutID);

  //For Autonomous Dead Reckoning
  private MoveForTime m_moveForTime = new MoveForTime(m_drivetrain, Config.kSpeed, Config.kTimeInSeconds);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_intakeArmIn.whenPressed(m_intakeArm.SolenoidArmIn());
    m_intakeArmOut.whenPressed(m_intakeArm.SolenoidArmOut());

    m_launchButton.whenPressed(m_launch);
    m_reloadButton.whenPressed(m_reload);

    // m_climbIn.whenPressed(m_climbArm.SetArmIn());
    // m_climbOut.whenPressed(m_climbArm.SetArmOut());

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_moveForTime;
  }

  public Command getTeleopCommand(){
    m_drivetrain.setDefaultCommand(m_arcadeDrive);
    m_runIntake.schedule();
    return null;
  }
}
