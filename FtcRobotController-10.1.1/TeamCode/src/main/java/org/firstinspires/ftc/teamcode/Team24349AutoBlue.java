package org.firstinspires.ftc.teamcode;

// standard imports
// All imports are classes
// first one is just for autonomous
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// this one is for everything (including autonomous)
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// used for sleep (the counting time).
// can also be used on an advanced teleOp
import com.qualcomm.robotcore.util.ElapsedTime;

// setting up the driver's hub option
@Autonomous (name="Team24349Auto1ParkingBlue", group="24349")
public class Team24349AutoBlue extends LinearOpMode{

    // initializing a robot hardware map
    MightyMouseHM robot = new MightyMouseHM();
    ElapsedTime Time = new ElapsedTime();
    double multy = 0.3;

    // LinearOp Mode has its own runOpMode
    // so, this allows you to remake your own version of it
    // but must call upon it
    @Override

    // will run once you hit start
    public void runOpMode() {
        // initializing hardware map
        robot.Map(hardwareMap);
        // starts the timer
        waitForStart();

        // depends on your hardware map is set to
        // since ours is set to move forward,
        // with a power of 1, the robot will move forward at full power
        // will move backward at full power with a power of -1
        // can go higher than 1, but not suggested to put the strain on motors

        // moves forward
        robot.driveFrontRight.setPower(-1);
        robot.driveFrontLeft.setPower(0.9);


        sleep(900);

        // turns toward the left
        robot.driveFrontLeft.setPower(0);
        robot.driveFrontRight.setPower(0.5);

        sleep(1200);

        // since both arm motors are in the same orientation, they will have the same power
        // armRotator(2) - moves up and down (not enough power with 1 motor)
        robot.armRotator.setPower(0.8);
        robot.armRotator2.setPower(0.8);
        robot.driveFrontLeft.setPower(0);
        robot.driveFrontRight.setPower(0);

        sleep(600);

        // have to reset values after each sleep, or will continue action from before sleep
        // OR it will do the next step at the same time
        // EX: without setting the arm to power 0, it would cont moving with a power of 0.8 after sleep
        robot.driveFrontLeft.setPower(-0.9);
        robot.driveFrontRight.setPower(1);
        robot.armRotator.setPower(0);
        robot.armRotator2.setPower(0);

        sleep(200);
        // for the arm, a negative power means going down (as this is how our HM is set)
        robot.armRotator.setPower(-0.8);
        robot.armRotator2.setPower(-0.8);
        robot.driveFrontLeft.setPower(0);
        robot.driveFrontRight.setPower(0);

        sleep(200);

        robot.wrist.setPower(0.5);
        robot.armRotator.setPower(0);
        robot.armRotator2.setPower(0);

        sleep(200);

        robot.claw.setPosition(1);

        sleep(1000);

        // write code here


    }

}