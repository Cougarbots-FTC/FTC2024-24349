package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name="parkingAuto", group="24349")
public class parkingAuto extends LinearOpMode{

    MightyMouseHM robot = new MightyMouseHM();
    ElapsedTime Time = new ElapsedTime();
    double multy = 0.30;

    @Override
    public void runOpMode() {
        robot.Map(hardwareMap);
        waitForStart();

        //moves forward into the net zone
        robot.driveFrontLeft.setPower(0.85);
        robot.driveFrontRight.setPower(-0.9);
        robot.claw.setPosition(0);
        sleep(800);

    }
}
