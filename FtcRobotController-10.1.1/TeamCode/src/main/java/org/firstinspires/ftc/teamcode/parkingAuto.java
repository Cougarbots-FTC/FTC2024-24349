package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "parkingAuto", group = "24349")
public class parkingAuto extends LinearOpMode {
    MightyMouseHM robot = new MightyMouseHM();
    ElapsedTime Time = new ElapsedTime();


    // Calculate the COUNTS_PER_INCH for your specific drive train.
    // Go to your motor vendor website to determine your motor's COUNTS_PER_MOTOR_REV
    // For external drive gearing, set DRIVE_GEAR_REDUCTION as needed.
    // For example, use a value of 2.0 for a 12-tooth spur gear driving a 24-tooth spur gear.
    // This is gearing DOWN for less speed and more torque.
    // For gearing UP, use a gear ratio less than 1.0. Note this will affect the direction of wheel rotation.
    ///For drive train motors
    static  double     COUNTS_PER_MOTOR_REV    = 336 ;    // gear ratio * tick per
    static  double     DRIVE_GEAR_REDUCTION    = 1;     //  External Gearing
    // load / motor ? big over small? or small over big?
    static  double     WHEEL_DIAMETER_INCHES   = 4 ;     // For figuring circumference
    static  double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static  double     DRIVE_SPEED             = 0.75;
    static  double     TURN_SPEED              = 0.5;

    //static final double LIFT_GEAR_RATIO = 13.7;
    //static final double LIFT_COUNTS_PER_INCH = (28 * LIFT_GEAR_RATIO) / (Math.PI * Math.pow(1.5,2)) ; //* 28;

    @Override
    public void runOpMode() {
        robot.Map(hardwareMap);
        telemetry.addData("Time", Time.time());
        telemetry.addData("Starting at",  "%7d :%7d",
                robot.driveFrontLeft.getCurrentPosition(),
                robot.driveFrontRight.getCurrentPosition());
        telemetry.update();
        sleep(1000);

        robot.driveFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.driveFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.driveFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.driveFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        // Step through each leg of the path.
        // Note: Reverse movement is obtained by setting a negative distance (not speed).
        // S1: Moves backward 30 inches with a 1 second timeout.
        // Ours moves half of what it is told (but hey! if it works, it works)
        encoderDrive(DRIVE_SPEED, 80, -80, 1);
        // S2: Turn right 12 inches with 4 second timeout.
        // S3: Reverse 24 inches with 4 second timeout.
        telemetry.addData("Path", "Complete");
        telemetry.update();
        // Pause to display final telemetry message.
        sleep(1000);
    }

    /**
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the OpMode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        double newLeftTarget;
        double newRightTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {
            //telemetry.addData("Starting Left Encoder", robot.driveFrontLeft.getCurrentPosition());
            //telemetry.addData("Starting Right Encoder", robot.driveFrontRight.getCurrentPosition());
            //telemetry.addData("COUNTS_PER_INCH", COUNTS_PER_INCH); // if robot moves wrong, gear reduction or wheel diameter = incorrect
            // adjust counts per inch by tweaking gear reduction
            //telemetry.update();
            //sleep(1000);

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.driveFrontLeft.getCurrentPosition() + Math.floor(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.driveFrontRight.getCurrentPosition() + Math.floor(rightInches * COUNTS_PER_INCH);
            robot.driveFrontLeft.setTargetPosition((int) newLeftTarget);
            robot.driveFrontRight.setTargetPosition((int) newRightTarget);

            telemetry.addData("Target Pos Left:",newLeftTarget);
            telemetry.addData("Target Pos Right:",newRightTarget);

            telemetry.update();
            sleep(1000);


            robot.driveFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.driveFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            Time.reset();
            robot.driveFrontLeft.setPower(Math.abs(speed));
            robot.driveFrontRight.setPower(Math.abs(speed));

            //telemetry.addData("Motors Busy?", robot.driveFrontLeft.isBusy() + " | " + robot.driveFrontRight.isBusy());
            // if both motors say false, then the target position is not correctly set
            //telemetry.update();
            //sleep(1000);

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (Time.seconds() < timeoutS) &&
                    (robot.driveFrontLeft.isBusy() && robot.driveFrontRight.isBusy())) {

                // Display it for the driver.
                //telemetry.addData("Running to", " %7d :%7d", newLeftTarget, newRightTarget);
                //telemetry.addData("Currently at", " at %7d :%7d",
                //robot.driveFrontLeft.getCurrentPosition(), robot.driveFrontRight.getCurrentPosition());
                //telemetry.update();
            }

            // Stop all motion;
            robot.driveFrontLeft.setPower(0);
            robot.driveFrontRight.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.driveFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.driveFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move.
        }
    }
}