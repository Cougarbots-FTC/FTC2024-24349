package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name="Team24349Auto1ParkingBlue", group="24349")
public class Team24349AutoBlue extends LinearOpMode{

    Team24349HM robot = new Team24349HM();
    ElapsedTime Time = new ElapsedTime();
    double multy = 0.3;

    @Override
    public void runOpMode() {
        robot.Map(hardwareMap);
        waitForStart();
        // when straffing, both wheels spin inwards
        robot.DriveRightFront.setPower(-1);
        robot.DriveRightBack.setPower(1);
        robot.DriveLeftBack.setPower(1);
        robot.DriveLeftFront.setPower(-1);

        sleep(1600);

        robot.DriveRightFront.setPower(0);
        robot.DriveRightBack.setPower(0);
        robot.DriveLeftBack.setPower(0);
        robot.DriveLeftFront.setPower(0);



        // write code here


    }

}