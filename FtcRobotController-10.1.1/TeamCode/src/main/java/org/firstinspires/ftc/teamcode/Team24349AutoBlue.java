package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name="Team24349Auto1ParkingBlue", group="24349")
public class Team24349AutoBlue extends LinearOpMode{

    MightyMouseHM robot = new MightyMouseHM();
    ElapsedTime Time = new ElapsedTime();
    double multy = 0.3;

    @Override
    public void runOpMode() {
        robot.Map(hardwareMap);
        waitForStart();

        robot.driveFrontRight.setPower(-1);
        robot.driveFrontLeft.setPower(0.9);


        sleep(1700);

        robot.driveFrontLeft.setPower(0);
        robot.driveFrontRight.setPower(0.5);

        sleep(1400);

        robot.driveFrontLeft.setPower(-0.9);
        robot.driveFrontRight.setPower(1);

        sleep(200);

        robot.armRotator.setPower(0.8);
        robot.armRotator2.setPower(0.8);

        sleep(600);

        // write code here


    }

}