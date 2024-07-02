package frc.robot;

public final class Constants {
    public static class ControlConstants{
        // Controller Specifications
        public static final int XBOX_CONTROLLER_DRIVER = 0;
        public static final int XBOX_CONTROLLER_OPERATOR = 1;
        public static final double CONTROLLER_SENSITIVITY = 0.05;
    }

    public static class DriveConstants{
        // Motor IDs
        public static final int LEFT_DRIVE_1_ID = 2;
        public static final int LEFT_DRIVE_2_ID = 4;
        public static final int LEFT_DRIVE_3_ID = 6;
        public static final int RIGHT_DRIVE_1_ID = 1;
        public static final int RIGHT_DRIVE_2_ID = 3;
        public static final int RIGHT_DRIVE_3_ID = 5;

        // Precision Percent
        public static final double PRECISION_PERCENT = 0.3;

        // Motor Current Limit
        public static final int MOTOR_CURRENT_LIMIT = 20;
    }
    
    public static class ArmConstants{
        //  Motor IDs
        public static final int SHOULDER_LEFT_ID = 8;
        public static final int SHOULDER_RIGHT_ID = 9;
        public static final int TELESCOPE_MOTOR_ID = 7;


        //  Extensions 
        public static final double TELESCOPE_MAX_EXTENSION = 0.0;
        public static final double TELESCOPE_MIN_EXTENSION = 0.0;

        //  Rotations
        public static final double SHOULDER_MAX_ROTATION = 0.0;
        public static final double SHOULDER_MIN_ROTATION = 0.0;

    }
    
    public static class ClawConstants{
        // Channel IDs
        public static final int LEFT_CLAW_CHANNEL = 1;
        public static final int RIGHT_CLAW_CHANNEL = 2;
    }
    /*  LED Constants */
    public static class LEDConstants{
        //  LED PWM port
        public static final int LED_PWM_PORT = 0;
        //  LED strip length
        public static final int LED_LENGTH = 304;

        /*  LED Colors Constants */
        public static class ColorConstants{
            //  HSV Saturation
            public static final int RAINBOW_SAT = 255;
            //  HSV Value
            public static final int RAINBOW_VAL = 128;
        }
    }

    public static class PneumaticsConstants{
        // IDs
        public static final int CTREPCM_ID = 0;
        public static final int GEARSHIFTER_CHANNEL = 0;
    }

    public static class MotorConstants{
        // Percentages
        public static final int FULL_POWER_PERCENT = 1;
        public static final int NO_POWER_PERCENT = 0;
        public static final int NEGATIVE_POWER = -1;
    }
}