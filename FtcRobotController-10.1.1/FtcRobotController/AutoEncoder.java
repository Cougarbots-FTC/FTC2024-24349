package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "AutoEncoder", group = "24349")
public class AutoEncoder extends LinearOpMode {
    MightyMouse robot = new MightyMouseHM();
    ElapsedTime Time = new ElapsedTime();


    // Calculate the COUNTS_PER_INCH for your specific drive train.
    // Go to your motor vendor website to determine your motor's COUNTS_PER_MOTOR_REV
    // For external drive gearing, set DRIVE_GEAR_REDUCTION as needed.
    // For example, use a value of 2.0 for a 12-tooth spur gear driving a 24-tooth spur gear.
    // This is gearing DOWN for less speed and more torque.
    // For gearing UP, use a gear ratio less than 1.0. Note this will affect the direction of wheel rotation.
    ///For drive train motors
    static final double     COUNTS_PER_MOTOR_REV    = 28 ;    // gear ratio * tick per = 20 * 28 = 560
    static final double     DRIVE_GEAR_REDUCTION    = 12 ;     //  External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 3.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 1;
    static final double     TURN_SPEED              = 0.5;

    static final double LIFT_GEAR_RATIO = 13.7;
    static final double LIFT_COUNTS_PER_INCH = (28 * LIFT_GEAR_RATIO) / (Math.PI * Math.pow(1.5,2)) ; //* 28;
}