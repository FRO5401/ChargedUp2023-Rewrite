package frc.robot;

public final class Constants {
    public static class ControlConstants{
        // controller specs
        public static final int XBOX_CONTROLLER_DRIVER = 0;
        public static final int XBOX_CONTROLLER_OPERATOR = 1;
        public static final double CONTROLLER_SENSITIVITY = 0.05;

    }

    public static class DriveConstants{
        //motor ids
        public static final int LEFT_MOTOR_1 = 2;
        public static final int LEFT_MOTOR_2 = 4;
        public static final int LEFT_MOTOR_3 = 6;
        public static final int RIGHT_MOTOR_1 = 1;
        public static final int RIGHT_MOTOR_2 = 3;
        public static final int RIGHT_MOTOR_3 = 5;

        //power percentages
        public static final int FULL_POWER_PERCENT = 1;
        public static final int NO_POWER_PERCENT = 0;
        public static final int NEGATIVE_POWER = -1;
        public static final double PRECISION_PERCENT = 0.3;

    }
    
    public static class ArmConstants{
        
    }
    
    public static class ClawConstants{
        public static final int LEFT_CLAW_CHANNEL = 1;
        public static final int RIGHT_CLAW_CHANNEL = 2;
    }
    
    public static class LEDConstants{
        
    }

    public static class PneumaticsConstants{

        public static final int CTREPCM_ID = 0;
        public static final int GEARSHIFTER_CHANNEL = 0;
    }
}