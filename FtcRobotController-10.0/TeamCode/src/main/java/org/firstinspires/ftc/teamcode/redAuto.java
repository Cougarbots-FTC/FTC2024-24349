package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoardAuto;

@Disabled()
public class RedAuto extends OpMode{
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
        if (getRuntime() < 1.8 && getRuntime() > 1){
            board.runWheelsStraight();
        }
    }
}