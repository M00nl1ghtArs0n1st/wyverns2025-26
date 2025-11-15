package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

//Made by Finch (Will)

@TeleOp
public class SixWheelDrive extends LinearOpMode{
    RobotClass robot;

    boolean usingTankDrive = false; //easy switch between tank drive and arcade drive
    //tank drive: left_stick_y is used for forward and back for the left side, same for right_stick_y and right side
    //arcade drive: left_stick_y is used for forward and back for both sides, right_stick_x is used for turning

    @Override
    public void runOpMode() throws InterruptedException {
        //hardware mapping from driver hub
        robot = new RobotClass(hardwareMap);

        // Put stuff here you want to do after "init", before "play"

        waitForStart();
        while (!isStopRequested()) { //program wouldnt start without this
            //gamepad1: driver gamepad
            //gamepad2: tools gamepad
            double tankLeft = -gamepad1.left_stick_y; // will also be used for arcade controls (would be called arcadeForward)
            double tankRight = gamepad1.right_stick_y;
            double arcadeTurn = gamepad1.right_stick_x;
            //this seems useless, but if you need to reverse a control, you can just add "-" before the reference

            if (usingTankDrive) {
                //left side
                robot.frontLeft.setPower(tankLeft);
                robot.backLeft.setPower(tankLeft);
                //right side
                robot.frontRight.setPower(-tankRight);
                robot.backRight.setPower(-tankRight);
            } else {
                robot.frontLeft.setPower(tankLeft + arcadeTurn);
                robot.backLeft.setPower(tankLeft + arcadeTurn);
                robot.frontRight.setPower(tankLeft - arcadeTurn);
                robot.backRight.setPower(tankLeft - arcadeTurn);
                }
            }

        }
    }
