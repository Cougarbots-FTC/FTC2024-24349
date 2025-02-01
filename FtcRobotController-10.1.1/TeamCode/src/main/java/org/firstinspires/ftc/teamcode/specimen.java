package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name="specimenAuto", group="24349")
public class specimen extends LinearOpMode{

    MightyMouseHM robot = new MightyMouseHM();
    ElapsedTime Time = new ElapsedTime();
    double multy = 0.30;

    @Override
    public void runOpMode() {
        robot.Map(hardwareMap);
        waitForStart();

        //moves forward to the rod
        robot.driveFrontLeft.setPower(0.85);
        robot.driveFrontRight.setPower(-0.9);
        robot.claw.setPosition(0);
        sleep(750);

        //moves the arm slightly upwards
        robot.driveFrontLeft.setPower(0);
        robot.driveFrontRight.setPower(0);
        robot.armRotator.setPower(0.4);
        //robot.armRotator2.setPower(0.4);
        sleep(300);

        //moves the wrist upwards so that the rod and clip line up
        robot.wrist.setPower(1);
        robot.armRotator.setPower(0);
        //robot.armRotator2.setPower(0);
        sleep(500);

        //moves both the wrist and arm down to clip the specimen
        robot.wrist.setPower(1);
        robot.armRotator.setPower(-0.75);
        //robot.armRotator2.setPower(-0.75);
        sleep(475);

        //opens the claw after hooking the specimen
        robot.claw.setPosition(1);
        robot.wrist.setPower(0);
        robot.armRotator.setPower(0);
        //robot.armRotator2.setPower(0);
        sleep(300);
    }
}