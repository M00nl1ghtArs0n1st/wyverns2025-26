package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.qualcomm.robotcore.hardware.CRServo;
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
//        robot.frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        robot.frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        robot.backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        robot.backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        robot.frontRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        robot.backLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        robot.backRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        robot.imu.resetYaw();

        // Put stuff here you want to do after "init", before "play"



        waitForStart();
        while (!isStopRequested()) { //program wouldnt start without this
            //gamepad1: driver gamepad
            //gamepad2: tools gamepad
            retrieveTelemetry();
            double tankLeft = -gamepad1.left_stick_y; // will also be used for arcade controls (would be called arcadeForward)
            double tankRight = +gamepad1.right_stick_y;
            double arcadeTurn = gamepad1.right_stick_x;
            //this seems useless, but if you need to reverse a control, you can just add "-" before the reference
            boolean intakeStart = gamepad2.left_bumper;
            double flywheelStart = gamepad2.right_trigger;
            boolean intakeBack= gamepad2.right_bumper;
            boolean threeProngStart = gamepad2.b;
            if (intakeStart) {
                robot.intakeMotor.setPower(1);
            } else if(intakeBack) {
                robot.intakeMotor.setPower(-1);
            } else {
                robot.intakeMotor.setPower(0);
            }
            if (threeProngStart) {
                robot.flywheelServo.setPower(1);
            } else {
                robot.flywheelServo.setPower(0);
            }
            robot.flywheelMotor.setPower(flywheelStart * .5);
            if (usingTankDrive) {
                //left side
                robot.frontLeft.setPower(tankLeft);
                robot.backLeft.setPower(tankLeft);
                //right side
                robot.frontRight.setPower(-tankRight);
                robot.backRight.setPower(-tankRight);
            } else {
                retrieveTelemetry();
                robot.frontLeft.setPower(tankLeft +arcadeTurn);
                robot.backLeft.setPower(tankLeft +arcadeTurn);
                robot.frontRight.setPower(tankLeft -arcadeTurn);
                robot.backRight.setPower(tankLeft -arcadeTurn);
            }
        }
    }
    public void retrieveTelemetry() {
        boolean controlHubData = true;
        telemetry.addData("Back Left Motor Position", robot.backLeft.getCurrentPosition());
        telemetry.addData("Back Right Motor Position", robot.backRight.getCurrentPosition());
        telemetry.addData("Front Left Motor Position", robot.frontLeft.getCurrentPosition());
        telemetry.addData("Front Right Motor Position", robot.frontRight.getCurrentPosition());
//        telemetry.addData("Difference in Position", robot.backLeft.getCurrentPosition() - robot.backRight.getCurrentPosition());
        telemetry.addData("Robot Heading Angle", robot.getHeading());
        telemetry.addData("control hub updated?", controlHubData);
        telemetry.update();
    }
}
