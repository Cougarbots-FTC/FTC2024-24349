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

            double yPower = gamepad1.left_stick_y;

            if (yPower != 0) {
                robot.driveFrontLeft.setPower(-yPower * mag);
                robot.driveFrontRight.setPower(yPower * mag);
            } else {
                robot.driveFrontLeft.setPower(0);
                robot.driveFrontRight.setPower(0);
            }
            double xPower = gamepad1.right_stick_x;
            if (xPower < 0 ) {
                robot.driveFrontLeft.setPower(xPower * mag);
            } else {
                robot.driveFrontLeft.setPower(0);
            }
            if (xPower > 0) {
                robot.driveFrontRight.setPower(xPower * mag);
            } else {
                robot.driveFrontRight.setPower(0);
            }

            double armRotatorUp = gamepad1.left_trigger;
            if (armRotatorUp > 0.1) {
                robot.armRotator.setPower(armRotatorUp);
                robot.armRotator2.setPower(armRotatorUp);
            } else {
                robot.armRotator.setPower(0);
                robot.armRotator2.setPower(0);
            }
            double armRotatorDown = gamepad1.right_trigger;
            if (armRotatorDown > 0.1) {
                robot.armRotator.setPower(-1 * armRotatorDown);
                robot.armRotator2.setPower(-1 * armRotatorDown);
            } else {
                robot.armRotator.setPower(0);
                robot.armRotator2.setPower(0);
            }


            if (gamepad1.a) {
                robot.claw.setPosition(1);//open
            }
            if (gamepad1.b) {
                robot.claw.setPosition(0); //close
            }

            if (gamepad1.x) {
                robot.wrist.setPosition(1); //open
            }
            if (gamepad1.y) {
                robot.wrist.setPosition(0); //close
            }

        }
    }
}
