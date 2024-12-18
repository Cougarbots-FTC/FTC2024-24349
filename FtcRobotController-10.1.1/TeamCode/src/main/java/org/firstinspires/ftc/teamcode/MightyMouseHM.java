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
    public CRServo claw = null;

    HardwareMap hwMap = null;
    public void Map(HardwareMap hardwareMap) {
        // telling the control hub by initializing the motors
        // it is case - sensitive
        hwMap = hardwareMap;
        driveFrontRight = hwMap.get(DcMotor.class, "driveFrontRight"); // 0
        driveFrontLeft = hwMap.get(DcMotor.class, "driveFrontLeft");   // 1
        armRotator = hwMap.get(DcMotor.class, "armRotator");   // 2
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

        claw = hwMap.get(CRServo.class, "claw");
    }

}
