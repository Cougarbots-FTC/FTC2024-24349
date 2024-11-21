package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Team24349HM {
    // declare our 4 drive motors
    public DcMotor DriveRightBack = null;
    public DcMotor DriveLeftBack = null;
    public DcMotor DriveLeftFront = null;
    public DcMotor DriveRightFront = null;
    // declaring any other motors (leftArm, RightArm, intake, linearMotion)
    public DcMotor RightSlide = null;
    public DcMotor LeftSlide = null;
    public DcMotor RightArm = null;
    public DcMotor LeftArm = null;

    // can select a certain area, and ctrl + / will comment it out
    //public Servo Servo = null;
//I fixed it



    //public NormalizedColorSensor ColorSensor = null;



    //public ElapsedTime period  = new ElapsedTime();

    // public Team4008HMNew() {}

    HardwareMap hwMap = null;
    //FIX AND USE IT
    public void Map(HardwareMap hardwareMap)
    {
        // telling the control hub by initializing the motors
        // it is case - sensitive
        hwMap = hardwareMap;
        DriveLeftFront = hwMap.get(DcMotor.class,"DriveLeftFront"); //0
        DriveRightFront = hwMap.get(DcMotor.class,"DriveRightFront"); //1
        DriveLeftBack = hwMap.get(DcMotor.class,"DriveLeftBack"); //2
        DriveRightBack = hwMap.get(DcMotor.class,"DriveRightBack"); //3
        //   Lights = hwMap.get(RevBlinkinLedDriver.class,"Lights");

        // default motor direction is forward
        // if you want your robot to slide, then you would put it on post mode
        DriveLeftFront.setDirection(DcMotor.Direction.FORWARD);
        DriveLeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DriveLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DriveRightFront.setDirection(DcMotor.Direction.REVERSE);
        DriveRightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DriveRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DriveLeftBack.setDirection(DcMotor.Direction.FORWARD);
        DriveLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DriveLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DriveRightBack.setDirection(DcMotor.Direction.REVERSE);
        DriveRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DriveRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


//        Turret = hwMap.get(DcMotor.class,"Turret");
//        Turret.setDirection(DcMotorSimple.Direction.REVERSE);
//        Turret.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        //IntakeLift.setDirection(DcMotor.Direction.REVERSE);

        RightSlide = hwMap.get(DcMotor.class, "RightSlide");
        RightSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        RightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        LeftSlide = hwMap.get(DcMotor.class, "LeftSlide");
        LeftSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        RightArm = hwMap.get(DcMotor.class, "RightArm");
        RightArm.setDirection(DcMotorSimple.Direction.REVERSE);
        RightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        LeftArm = hwMap.get(DcMotor.class, "LeftArm");
        LeftArm.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // a class for a possible servo
        //Servo = hwMap.get(Servo.class, "Servo");

        //Arm = hwMap.get(DcMotor.class, "Arm");

//        ColorSensor = hwMap.get(NormalizedColorSensor.class, "sensor");

    }
}
