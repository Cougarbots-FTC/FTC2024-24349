package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name="highSpecimenAuto", group="24349")
public class highSpecimen extends LinearOpMode{

    MightyMouseHM robot = new MightyMouseHM();
    ElapsedTime Time = new ElapsedTime();
    double multy = 0.30;

    @Override
    public void runOpMode() {
        robot.Map(hardwareMap);
        waitForStart();

        //moves backwards to the rod
        robot.driveFrontLeft.setPower(-0.53);
        robot.driveFrontRight.setPower(0.5);
        robot.claw.setPosition(1);
        sleep(1200);

        //moves backwards to the rod
        robot.driveFrontLeft.setPower(-0.3);
        robot.driveFrontRight.setPower(0.29);
        sleep(200);


        //moves the arm slightly upwards
        robot.driveFrontLeft.setPower(0);
        robot.driveFrontRight.setPower(0);
        robot.armRotator.setPower(0.6);
        robot.armRotator2.setPower(0.6);
        sleep(650);

        //moves the wrist upwards so that the rod and clip line up
        robot.wrist.setPower(1);
        robot.armRotator.setPower(0);
        robot.armRotator2.setPower(0);
        sleep(500);

        //moves the arm slightly upwards
        robot.driveFrontLeft.setPower(0);
        robot.driveFrontRight.setPower(0);
        robot.armRotator.setPower(0.4);
        robot.armRotator2.setPower(0.4);
        sleep(500);

        //moves both the wrist and arm down to clip the specimen
        robot.wrist.setPower(1);
        robot.armRotator.setPower(-0.75);
        robot.armRotator2.setPower(-0.75);
        sleep(475);

    }
}