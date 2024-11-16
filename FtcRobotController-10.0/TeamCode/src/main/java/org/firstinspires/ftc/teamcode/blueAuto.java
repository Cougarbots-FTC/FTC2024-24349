package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoardAuto;

@Disabled()
public class blueAuto extends OpMode{
    private final ProgrammingBoardAuto board = new ProgrammingBoardAuto();

    @Override
    public void init(){
        board.init(hardwareMap);
    }

    @Override
    public void start(){
        resetRuntime();
    }

    @Override
    public void loop(){
        //more arm power, pause between strafe and straight.
        if (getRuntime() < 2.4 && getRuntime() > 1){
            board.runWheelsStrafe(0.5);
        }
        else if (getRuntime() < 2.95){
            board.runWheelsStrafe(0.0);
        }
        else if (getRuntime() < 4.0){
            board.runWheelsStraight(0.65);
        }
        else if (getRuntime() < 4.4) {
            board.runWheelsStraight(0.0);
        }
        else if (getRuntime() < 5.4){
            board.setBaseArm(0.7);
        }
        else if (getRuntime() < 5.8){
            board.setBaseArm(0.3);
        }
        else if (getRuntime() < 7.8 && getRuntime() > 6.8){
            board.setClaw(true);
        }
        else if (getRuntime() < 7.8){
            board.setBaseArm(-0.6);
        }
        else if (getRuntime() > 8.2){
            board.setBaseArm(0.0);
        }
    }
}