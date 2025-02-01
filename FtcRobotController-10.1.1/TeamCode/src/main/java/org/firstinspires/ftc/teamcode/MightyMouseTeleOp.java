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
        //pidf = new PIDFArm(robot.armRotator,0,0,0,0,3);
        //pidf2 = new PIDFArm(robot.armRotator2,0,0,0,0,2);


        // telemetry stores positions and other values that are assigned
        telemetry.addData("Say", "Mighty Mouse is here to save the day!");
        telemetry.addData("Arm1 Position: ", robot.armRotator.getCurrentPosition());
        //telemetry.addData("Arm2 Position: ", robot.armRotator2.getCurrentPosition());
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

            //this for the lift system
            //this on gamepad1
            //this lifts the left arm up and down
//            double liftArmUp = gamepad1.left_trigger;
//            double liftArmDown = gamepad1.right_trigger;
//            if (liftArmUp >= 0.1) {
//                robot.liftArm.setPower(liftArmUp);
//            }
//            else if (liftArmDown >= 0.1){
//                robot.liftArm.setPower(-1*liftArmDown);
//            }else {
//                robot.liftArm.setPower(0);
//            }
//            if (liftArmUp>0.1){
//                robot.liftArm.setPower(liftArmUp);
//            } else if (liftArmDown>0.1) {
//                robot.liftArm.setPower(-1*liftArmDown);
//            }
//            else {
//                robot.liftArm.setPower(0);
//            }

            //this rotates the chain
//            if (gamepad1.left_bumper){
//                //clockwise
//                robot.liftSystem.setPower(0.5);
//            } else if (gamepad1.right_bumper) {
//                //counterclockwise
//                robot.liftSystem.setPower(-0.5);
//            }
//            else {
//                robot.liftSystem.setPower(0);
//            }

            // this is for lifting the arm
            // by using gamepad 2, this is for when we press start B
            double armRotatorUp = gamepad2.left_trigger;
            double armRotatorDown = gamepad2.right_trigger;
            if (armRotatorUp > 0.1) {
                robot.armRotator.setPower(armRotatorUp); // Set encoder position to 1000
                robot.armRotator2.setPower(armRotatorUp); // Set encoder position to 1000
            }
            else if (armRotatorDown > 0.1) {
                robot.armRotator.setPower(-1 * armRotatorDown); // Set encoder position to 1000
                robot.armRotator2.setPower(-1 * armRotatorDown); // Set encoder position to 1000
            }
            else {
                robot.armRotator.setPower(0); // Set encoder position to 1000
                robot.armRotator2.setPower(0); // Set encoder position to 1000
            }


            //pidf code to set arm to a certain position
            // when x is pressed the arm moves to score high specimen
            // when y is pressed the arm moves to grab a specimen from the wall
//            if (gamepad2.x) {
//                pidf.setSetpoint(290); // Set encoder position to 1000
//                //pidf2.setSetpoint(289); // Set encoder position to 2000
//
//            } else if (gamepad2.y) {
//                pidf.setSetpoint(140); // Set encoder position to 2000
//                //pidf2.setSetpoint(139); // Set encoder position to 2000
//
//            }
            //pidf.loop();
            //pidf2.loop();

            // Telemetry for debugging
            //telemetry.addData("Target1 Position", pidf.getSetpoint());
            //telemetry.addData("Target2 Position", pidf2.getSetpoint());
            telemetry.addData("Current1 Position", robot.armRotator.getCurrentPosition());
            //telemetry.addData("Current2 Position", robot.armRotator2.getCurrentPosition());
            telemetry.update();

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
