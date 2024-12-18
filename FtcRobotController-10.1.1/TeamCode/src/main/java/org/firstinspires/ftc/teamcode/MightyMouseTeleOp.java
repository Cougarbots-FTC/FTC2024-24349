package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MightyMouse", group = "24349")
public class MightyMouseTeleOp extends LinearOpMode {
    MightyMouseHM robot = new MightyMouseHM();

    @Override
    public void runOpMode() {
        robot.Map(hardwareMap);
        telemetry.addData("Say", "Mighty Mouse is here to save the day!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            boolean speedslow = gamepad1.right_bumper;
            double mag = speedslow ? 0.5 : 1.0;

            /*
            double y = gamepad1.left_stick_y; // Remember, this is reversed!
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.5);
            double frontLeftPower = (-y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            robot.driveFrontLeft.setPower(frontLeftPower * mag);
            robot.driveFrontRight.setPower(frontRightPower * mag);

            */

            double yPower = gamepad1.left_stick_y;

            if (yPower != 0) {
                robot.driveFrontLeft.setPower(-yPower * mag);
                robot.driveFrontRight.setPower(yPower * mag);
            } else {
                robot.driveFrontLeft.setPower(0);
                robot.driveFrontRight.setPower(0);
            }
            double xPower = gamepad1.right_stick_x;
            if (xPower > 0 ) {
                robot.driveFrontLeft.setPower(xPower * mag);
            } else {
                robot.driveFrontLeft.setPower(0);
            }
            if (xPower < 0) {
                robot.driveFrontRight.setPower(xPower * mag);
            } else {
                robot.driveFrontRight.setPower(0);
            }

            double armRotatorUp = gamepad1.left_trigger;
            if (armRotatorUp > 0) {
                robot.armRotator.setPower(armRotatorUp);
            }
            double armRotatorDown = gamepad1.right_trigger;
            if (armRotatorDown > 0) {
                robot.armRotator.setPower(-1 * armRotatorDown);
            }


            if (gamepad1.y) {
                robot.claw.setPower(1);//open
            }
            if (gamepad1.x) {
                robot.claw.setPower(-1); //close
            }
            //telemetry.addData("Claw", robot.claw.getPosition());

            //telemetry.update();

        }
    }
}
