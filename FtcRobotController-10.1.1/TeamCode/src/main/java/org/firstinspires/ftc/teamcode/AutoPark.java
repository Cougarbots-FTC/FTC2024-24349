package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name="AutoPark", group="24349")
public class AutoPark extends LinearOpMode{

    MightyMouseHM robot = new MightyMouseHM();
    ElapsedTime Time = new ElapsedTime();
    double multy = 0.3;

    @Override
    public void runOpMode() {
        robot.Map(hardwareMap);
        waitForStart();

        robot.driveFrontRight.setPower(-0.85);
        robot.driveFrontLeft.setPower(1);

        sleep(1000);

        // write code here
    }
}