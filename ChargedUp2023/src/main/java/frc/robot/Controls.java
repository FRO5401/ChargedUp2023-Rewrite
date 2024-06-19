package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Controls {
  public static final String DriveConstants = null;
// The driver's controller
  public static XboxController xbox_driver = new XboxController(Constants.ControlConstants.XBOX_CONTROLLER_DRIVER);
  public static XboxController xbox_operator = new XboxController(Constants.ControlConstants.XBOX_CONTROLLER_OPERATOR);
  public static CommandXboxController driver = new CommandXboxController(Constants.ControlConstants.XBOX_CONTROLLER_DRIVER); // Creates a CommandXboxController on port 1.
  public static CommandXboxController operator = new CommandXboxController(Constants.ControlConstants.XBOX_CONTROLLER_OPERATOR); // Creates a CommandXboxController on port 1.

}
  
  