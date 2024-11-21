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
        
        sleep(1600);
        // write code here


    }

}