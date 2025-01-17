package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MightyMouse", group = "24349")
public class MightyMouseTeleOp extends LinearOpMode {
    MightyMouseHM robot = new MightyMouseHM();

    @Override
    public void runOpMode() {
        // initializing the hardware map for the robot
        robot.Map(hardwareMap);
        // telemetry stores positions and other values that are assigned
        telemetry.addData("Say", "Mighty Mouse is here to save the day!");
        telemetry.update();

        // must have this!!
        waitForStart();

        // opMode is active when you hit the play button on the drivers hub, & vice versa
        while (opModeIsActive()) {

            // allows you to slow down your robot
            // if right_bumper is pressed then it is at 1/2 power; otherwise it is at full power
            boolean speedslow = gamepad1.right_bumper;
            double mag = speedslow ? 0.5 : 1.0;

            // this is the one we use to move front and back
            double yPower = gamepad1.left_stick_y;

            if (yPower != 0) {
                // makes it move forward if up and backward if down
                robot.driveFrontLeft.setPower(-yPower * mag);
                robot.driveFrontRight.setPower(yPower * mag);
            } else {
                // makes sure that the robot is not moving when the left joystick is not moved
                robot.driveFrontLeft.setPower(0);
                robot.driveFrontRight.setPower(0);
            }
            // this is for turning
            double xPower = gamepad1.right_stick_x;
            if (xPower < 0 ) {
                robot.driveFrontLeft.setPower(-xPower * mag);
                robot.driveFrontRight.setPower(xPower * mag);
            } else {
                // makes sure that the robot is not moving when the right joystick is not moved
                robot.driveFrontLeft.setPower(0);
                robot.driveFrontRight.setPower(0);
            }
            if (xPower > 0) {
                robot.driveFrontRight.setPower(-xPower * mag);
                robot.driveFrontLeft.setPower(xPower * mag);
            } else {
                // makes sure that the robot is not moving when the right joystick is not moved
                robot.driveFrontRight.setPower(0);
                robot.driveFrontLeft.setPower(0);
            }

            // this is for lifting the arm
            // by using gamepad 2, this is for when we press start B
            double armRotatorUp = gamepad2.left_trigger;
            if (armRotatorUp > 0.1) {
                robot.armRotator.setPower(armRotatorUp);
                robot.armRotator2.setPower(armRotatorUp);
            } else {
                robot.armRotator.setPower(0);
                robot.armRotator2.setPower(0);
            }
            // this is for lowering the arm
            double armRotatorDown = gamepad2.right_trigger;
            if (armRotatorDown > 0.1) {
                robot.armRotator.setPower(-1 * armRotatorDown);
                robot.armRotator2.setPower(-1 * armRotatorDown);
            } else {
                robot.armRotator.setPower(0);
                robot.armRotator2.setPower(0);
            }

            // this is to open and close the claw
            if (gamepad2.a) {
                robot.claw.setPosition(1);//open
            }
            if (gamepad2.b) {
                robot.claw.setPosition(0); //close
            }

            // moves the wrist up and down
            double wristPower = -gamepad2.left_stick_y;
            if (wristPower != 0) {
                robot.wrist.setPower(wristPower); //open
            } else {
                robot.wrist.setPower(0);
            }

        }
    }
}
