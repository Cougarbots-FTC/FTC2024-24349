package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Sisyphus extends OpMode{
    private PIDController controller;

    public static double p = 0; i = 0; d = 0;
    public static double f=0;

    public static int target = 0;

    //can vary per motor so double check value
    private final double ticks_in_degree = 28 / 360.0;

    private DcMotorEx rotatingArm1;
    private DcMotorEx rotatingArm2;

    @Overides
    public void init(){
    controller = new PIDController(p ,i, d);
    telemetry = new MultipltTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        rotatingArm1 = hardwareMap.get(DcMotorEx.class, "rotatingArm1");
        rotatingArm2 = hardwareMap.get(DcMotorEx.class, "rotatingArm2");
    }

    @Overides
    public void loop(){
        controller.setPID(p, i, d);
        int armPos1 = rotatingArm1.getCurrentPosition();
        int armPos2 = rotatingArm2.getCurrentPosition();
        double pid1 = controller.calculate(armPos1, target);
        double pid2 = controller.calculate(armPos2, target);

        double ff = Math.cos(Math.toRadians(target/ticks_in_degree)) * f;

        double power1 = pid1 + ff;
        double power2 = pid2 + ff;

        rotatingArm1.setPower(power1);
        rotatingArm2.setPower(power2);

        telemetry.addData("pos1 ", armPos1);
        telemetry.addData("pos2 ", armPos2);
        telemetry.addData("target ", target);

    }
}