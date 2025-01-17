package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MightyMouseHM {
    // declare our 4 drive motors
    public DcMotor driveFrontRight = null;
    public DcMotor driveFrontLeft = null;

    public DcMotor armRotator = null;
    public DcMotor armRotator2 = null;
    public Servo claw = null;
    public CRServo wrist = null;

    HardwareMap hwMap = null;
    public void Map(HardwareMap hardwareMap) {
        // initializing the hardware map
        hwMap = hardwareMap;
        // configuration to the ports on the driver hub (each motor)
        // the '.get(DcMotor.class' is how you always initialize it
        // the comments next to each line shows the port each wire is connected to
        driveFrontRight = hwMap.get(DcMotor.class, "driveFrontRight"); // 0
        driveFrontLeft = hwMap.get(DcMotor.class, "driveFrontLeft");   // 1
        armRotator = hwMap.get(DcMotor.class, "armRotator");   // 2
        armRotator2 = hwMap.get(DcMotor.class, "armRotator2");   // 3

        // configuration of the servos
        claw = hwMap.get(Servo.class, "claw"); // 4
        wrist = hwMap.get(CRServo.class, "wrist"); // 5

        // default motor direction is forward
        // if you want your robot to slide, then you would put it on post mode
        // setting the direction of each motor
        // think of it like multiplying different signs for movement
        // '.setDirection...Direction', 'setZeroPower...Behavior', & 'setMode...RunMode' are all standard
        driveFrontRight.setDirection(DcMotor.Direction.FORWARD);
        driveFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // we didn't connect the encoder wire, so decided to run without encoder
        // encoders tell us where the motors stop and the time it takes them
        driveFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        driveFrontLeft.setDirection(DcMotor.Direction.FORWARD);
        driveFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        driveFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        armRotator.setDirection(DcMotor.Direction.FORWARD);
        armRotator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRotator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        armRotator2.setDirection(DcMotor.Direction.FORWARD);
        armRotator2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRotator2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

}
