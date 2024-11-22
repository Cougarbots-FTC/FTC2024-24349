package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//people who able to access this: 1
@TeleOp(name = "Team24349TeleOp", group = "24349")
public class Team24349TeleOp extends LinearOpMode {
    Team24349HM robot = new Team24349HM();


    @Override
    public void runOpMode() {
        robot.Map(hardwareMap);
        telemetry.addData("Say", "TeleOp Starting");
        telemetry.update();
        robot.DriveRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.DriveLeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.DriveRightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.Servo.setPosition(0.4);

        int count = 0;
        //robot.Lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_PARTY_PALETTE);
        waitForStart();

        while (opModeIsActive()) {
            // a ternary operator
            // a slow button for the driver
            boolean speedslow = gamepad1.right_bumper;
            // when the right bumper is held down, it will go down to 50 percent of its speed
            double mag = speedslow ? 0.5 : 1.0;

            double y = gamepad1.left_stick_y; // Remember, this is reversed!
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;


          /*  if (robot.Touched.isPressed()) {
                telemetry.addData("Touch", robot.Touched.isPressed());
                telemetry.update();
            } */

            // hello
            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.5);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;



            robot.DriveLeftFront.setPower(frontLeftPower * mag);
            robot.DriveLeftBack.setPower(backLeftPower * mag);
            robot.DriveRightFront.setPower(frontRightPower * mag);
            robot.DriveRightBack.setPower(backRightPower * mag);

            double slidePower = gamepad2.left_stick_y;
            robot.LeftSlide.setPower(slidePower);
            robot.RightSlide.setPower(slidePower * -1);

            double armPower = gamepad2.right_stick_y * 0.75;
            robot.LeftArm.setPower(armPower);
            robot.RightArm.setPower(armPower * -1);

            //robot.Servo.setPosition(0.25);


            if(gamepad2.y) {
                robot.Servo.setPosition(0.4);//open
            }

            if(gamepad2.a) {
                robot.Servo.setPosition(0);//close
            }
            telemetry.addData("RightFront", robot.DriveRightFront.getCurrentPosition());
            telemetry.addData("RightBack", robot.DriveRightBack.getCurrentPosition());
            telemetry.addData("LeftFront", robot.DriveLeftFront.getCurrentPosition());
            telemetry.addData("LeftBack", robot.DriveLeftBack.getCurrentPosition());
            telemetry.addData("Servo Position", robot.Servo.getPosition());//getCurrentPosition?
            telemetry.update();




//            double Arm1 = gamepad2.right_stick_y;
//            double Arm2 = gamepad2.left_stick_y;
//            robot.Arm1.setPower(Arm1);
//            robot.Arm2.setPower(Arm2);
            // left bumper is open, right bumper is close
//            if (gamepad2.right_bumper) {
//                robot.Intake.setPosition(1.0);
//            }
//            if (gamepad2.left_bumper){
//                robot.Intake.setPosition(0.3);
//            }

//            if(gamepad2.y){
//                robot.Arm1.setPower(.9);
//            }
//            else if (gamepad2.x){
//                robot.Arm1.setPower(-1);
//            }
//            else {
//                robot.Arm1.setPower(0);
//            }
        }
    }
}