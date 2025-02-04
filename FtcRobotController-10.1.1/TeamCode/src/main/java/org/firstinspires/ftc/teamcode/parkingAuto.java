package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name="parkingAuto", group="24349")
public class parkingAuto extends AutoEncoder{

    MightyMouseHM robot = new MightyMouseHM();
    ElapsedTime Time = new ElapsedTime();
    double multy = 0.30;

    @Override
    public void runOpMode() {
        // moves forward
        //encoderDrive(DRIVE_SPEED, 80, -80, 1);

    }
}

