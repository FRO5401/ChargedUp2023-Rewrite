// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDConstants;
import frc.robot.Constants.LEDConstants.ColorConstants;

public class LEDLights extends SubsystemBase {
  /*  Declaring Variables */
  //    Addressable LEDs
  private AddressableLED lightLEDs;
  //    LED Buffer
  private AddressableLEDBuffer LEDBuffer;
  //    Rainbow Lights first pixel
  private int firstPixel;

  /** Creates a new LEDLights. */
  public LEDLights() {
    /*  Initializing Variables */
    //    AddressableLED
    lightLEDs = new AddressableLED(LEDConstants.LED_PWM_PORT);
    //    AddressableLEDBuffer
    LEDBuffer = new AddressableLEDBuffer(LEDConstants.LED_LENGTH);

    //  Sets Length of LED lights
    lightLEDs.setLength(LEDBuffer.getLength());
    //  Activates LED lights
    lightLEDs.start();

    firstPixel = 1;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /*    In-Line Commands   */
  //  Sets LED color by RGB
  public Command setRGBColor(int r, int g, int b){
    return runOnce(()->{
      for (int i = 0; i < LEDBuffer.getLength(); i++){
        LEDBuffer.setRGB(i, r, g, b);
      }
    });
  }
  //  Sets LED color by HEX code
  public Command setHexColor(Color color){
    return runOnce(()->{
      for (int i = 0; i < LEDBuffer.getLength(); i++){
        LEDBuffer.setLED(i, color);
      }
    });
  }
  //  Creates a rainbow on the LEDs
  public Command setRainbow(){
    return runOnce(()->{
      for (int i = 0; i < LEDBuffer.getLength(); i++){
        var hue = (firstPixel + ((i * 180 / LEDBuffer.getLength())) % 180);
        LEDBuffer.setHSV(i, hue, ColorConstants.RAINBOW_SAT, ColorConstants.RAINBOW_VAL);
      }

      firstPixel += 3;
      firstPixel %= 180;

      lightLEDs.setData(LEDBuffer);
    });
  }
}
