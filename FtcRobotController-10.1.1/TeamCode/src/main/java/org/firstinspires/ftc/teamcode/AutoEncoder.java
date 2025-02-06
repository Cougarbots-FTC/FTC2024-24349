package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "AutoEncoder", group = "24349")
public class AutoEncoder extends LinearOpMode {
    MightyMouseHM robot = new MightyMouseHM();
    ElapsedTime Time = new ElapsedTime();


    // Calculate the COUNTS_PER_INCH for your specific drive train.
    // Go to your motor vendor website to determine your motor's COUNTS_PER_MOTOR_REV
    // For external drive gearing, set DRIVE_GEAR_REDUCTION as needed.
    // For example, use a value of 2.0 for a 12-tooth spur gear driving a 24-tooth spur gear.
    // This is gearing DOWN for less speed and more torque.
    // For gearing UP, use a gear ratio less than 1.0. Note this will affect the direction of wheel rotation.
    ///For drive train motors
    static  double     COUNTS_PER_MOTOR_REV_ARM= 288 ;    // gear ratio * tick per  //45 t * 125t
    static  double     DRIVE_GEAR_REDUCTION_ARM    = 125/45.0 * 72;     //  External Gearing
    static  double     COUNTS_PER_MOTOR_REV    = 336 ;    // gear ratio * tick per
    static  double     DRIVE_GEAR_REDUCTION    = 1;     //  External Gearing
    // load / motor ? big over small? or small over big?
    static  double     WHEEL_DIAMETER_INCHES   = 4 ;     // For figuring circumference
    static  double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static  double     COUNTS_PER_INCH_ARM      = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION);
    static  double     DRIVE_SPEED             = 0.64;
    static  double     TURN_SPEED              = 0.5;
    static  double     ARM_SPEED              = 0.7;

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

        telemetry.addData("Time", Time.time());
        telemetry.addData("Starting at",  "%7d :%7d",
                robot.armRotator.getCurrentPosition(),
                robot.armRotator2.getCurrentPosition());
        telemetry.update();
        sleep(1000);

        robot.driveFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.driveFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.driveFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.driveFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.armRotator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.armRotator2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.armRotator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.armRotator2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        // Step through each leg of the path.
        // Note: Reverse movement is obtained by setting a negative distance (not speed).
        // S1: Moves backward 30 inches with a 1 second timeout.
        // Ours moves half of what it is told (but hey! if it works, it works)
        robot.claw.setPosition(1);
        encoderDrive(DRIVE_SPEED, -75, 75, 1);
        encoderArm(ARM_SPEED,17.5,17.5,1.1);
        // S2: Turn right 12 inches with 4 second timeout.  `
        // S3: Reverse 24 inches with 4 second timeout.
        robot.wrist.setPower(-1);
        sleep(500);
        robot.wrist.setPower(-1);
        robot.armRotator.setPower(0.7);
        robot.armRotator2.setPower(0.7);
        sleep(500);


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
        double newLeftFTarget;
        double newRightFTarget;
        double newLeftBTarget;
        double newRightBTarget;

        if (opModeIsActive()) {
            newLeftFTarget = robot.driveFrontLeft.getCurrentPosition()
                    + Math.floor(leftInches * COUNTS_PER_INCH);
            newRightFTarget = robot.driveFrontRight.getCurrentPosition()
                    + Math.floor(rightInches * COUNTS_PER_INCH);
            newLeftBTarget = robot.driveBackLeft.getCurrentPosition()
                    + Math.floor(leftInches * COUNTS_PER_INCH);
            newRightBTarget = robot.driveBackRight.getCurrentPosition()
                    + Math.floor(rightInches * COUNTS_PER_INCH);
            robot.driveFrontLeft.setTargetPosition((int) newLeftFTarget);
            robot.driveFrontRight.setTargetPosition((int) newRightFTarget);
            robot.driveBackLeft.setTargetPosition((int) newLeftBTarget);
            robot.driveBackRight.setTargetPosition((int) newRightBTarget);

            telemetry.addData("Target Pos Left:",newLeftFTarget);
            telemetry.addData("Target Pos Right:",newRightFTarget);

            telemetry.update();
            sleep(1000);

            robot.driveFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.driveFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.driveBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.driveBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            Time.reset();
            robot.driveFrontLeft.setPower(Math.abs(speed));
            robot.driveFrontRight.setPower(Math.abs(speed));
            robot.driveBackLeft.setPower(Math.abs(speed));
            robot.driveBackRight.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (Time.seconds() < timeoutS) &&
                    (robot.driveFrontLeft.isBusy() &&
                            robot.driveFrontRight.isBusy() &&
                            robot.driveBackLeft.isBusy() &&
                            robot.driveBackRight.isBusy())) {
            }

            robot.driveFrontLeft.setPower(0);
            robot.driveFrontRight.setPower(0);
            robot.driveBackLeft.setPower(0);
            robot.driveBackRight.setPower(0);

            robot.driveFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.driveFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.driveBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.driveBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sleep(250);   // optional pause after each move.
        }
    }
    public void encoderArm(double speed,
                             double arm1Inches, double arm2Inches,
                             double timeoutS) {
        double newArm;
        double newArm2;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {
            newArm = robot.armRotator.getCurrentPosition()
                    + Math.floor(arm1Inches * COUNTS_PER_INCH_ARM);
            newArm2 = robot.armRotator2.getCurrentPosition()
                    + Math.floor(arm2Inches * COUNTS_PER_INCH_ARM);
            robot.armRotator.setTargetPosition((int) newArm);
            robot.armRotator2.setTargetPosition((int) newArm2);

            telemetry.addData("Target Pos arm1:",newArm);
            telemetry.addData("Target Pos arm2:",newArm2);

            telemetry.update();
            sleep(1000);


            robot.armRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.armRotator2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            Time.reset();
            robot.armRotator.setPower(Math.abs(speed));
            robot.armRotator2.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (Time.seconds() < timeoutS) &&
                    (robot.armRotator.isBusy() &&
                            robot.armRotator2.isBusy())) {
            }

            robot.armRotator.setPower(0);
            robot.armRotator2.setPower(0);

            robot.armRotator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.armRotator2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);
        }
    }
}