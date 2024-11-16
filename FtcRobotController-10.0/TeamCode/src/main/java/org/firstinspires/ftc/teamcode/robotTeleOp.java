package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class robotTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException  {
        // Declare our motors
        // Make sure your ID's match your configuration
        //control hub motors
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        //expansion hub motors
        DcMotor linearMotion = hardwareMap.dcMotor.get("linearMotion");
        DcMotor rotatingArm1 = hardwareMap.dcMotor.get("rotatingArm1");
        DcMotor rotatingArm2 = hardwareMap.dcMotor.get("rotatingArm2");
        DcMotor intake = hardwareMap.dcMotor.get("intake");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y1 = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x1 = gamepad1.left_stick_x;
            double rx1 = gamepad1.right_stick_x;

            double y2 = -gamepad2.left_stick_y; //
            double x2 = -gamepad2.left_stick_x; //

            // This button choice was made so that it is hard to hit on accident,
            // it can be freely changed based on preference.
            // The equivalent button is start on Xbox-style controllers.
            if (gamepad1.options) {
                imu.resetYaw();
            }

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX1 = x1 * Math.cos(-botHeading) - y1 * Math.sin(-botHeading);
            double rotY1 = x1 * Math.sin(-botHeading) + y1 * Math.cos(-botHeading);

            rotX1 = rotX1 * 1.1;  // Counteract imperfect strafing
            double speed = 0.1;
            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(rotY1) + Math.abs(rotX1) + Math.abs(rx1), 1);
            double frontLeftPower = 2*(rotY1 + rotX1 + rx1) / denominator;
            double backLeftPower = 2*(rotY1 - rotX1 + rx1) / denominator;
            double frontRightPower = 2*(rotY1 - rotX1 - rx1) / denominator;
            double backRightPower = 2*(rotY1 + rotX1 - rx1) / denominator;


            frontLeftMotor.setPower(backRightPower);
            backLeftMotor.setPower(frontRightPower);
            frontRightMotor.setPower(backLeftPower);
            backRightMotor.setPower(frontLeftPower);

            //arm
            if(y2>0){
                rotatingArm1.setPower(2*y2);
                rotatingArm2.setPower(-2*y2);
            }
            else if (x2>0){
                rotatingArm1.setPower(-2*y2);
                rotatingArm2.setPower(2*y2);
            }
            else{
                rotatingArm1.setPower(0);
                rotatingArm2.setPower(0);
            }

            //linear motion
            if (gamepad1.x) {
                linearMotion.setPower(1);
            }
            else if(gamepad1.y){
                linearMotion.setPower(-1);
            }
            else {
                linearMotion.setPower(0);
            }

            //intake system
            if (gamepad2.x) {
                intake.setPower(1);
            }
            else if(gamepad2.y){
                intake.setPower(-1);
            }
            else {
                intake.setPower(0);
            }
        }
    }
}