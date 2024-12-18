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

    HardwareMap hwMap = null;
    public void Map(HardwareMap hardwareMap) {

        hwMap = hardwareMap;
        driveFrontRight = hwMap.get(DcMotor.class, "driveFrontRight"); // 0
        driveFrontLeft = hwMap.get(DcMotor.class, "driveFrontLeft");   // 1
        armRotator = hwMap.get(DcMotor.class, "armRotator");   // 2
        armRotator2 = hwMap.get(DcMotor.class, "armRotator2");   // 3

        // default motor direction is forward
        // if you want your robot to slide, then you would put it on post mode
        driveFrontRight.setDirection(DcMotor.Direction.FORWARD);
        driveFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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
        claw = hwMap.get(Servo.class, "claw");
    }

}
