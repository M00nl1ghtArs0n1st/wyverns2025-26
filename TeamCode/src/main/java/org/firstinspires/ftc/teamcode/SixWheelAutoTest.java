package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous
public class SixWheelAutoTest extends LinearOpMode {
    RobotClass robot;

    @Override
    public void runOpMode() {
        robot = new RobotClass(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {

            moveWithEncoders(2500, 2500);
            robot.backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            robot.backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            robot.frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            robot.frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    public void driveBasic(double left, double right, long time /* long variable type is really big so its for time-based purposes */) { //left: left side power, right: right side power, time: for how long
        //set all motor powers
        robot.frontLeft.setPower(left);
        robot.backLeft.setPower(left);
        robot.frontRight.setPower(right);
        robot.backRight.setPower(right);
        sleep(time); //wait for however long
        //stop
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
    }

    public void driveFunction(double left, double right) {
        robot.frontLeft.setPower(left);
        robot.backLeft.setPower(left);
        robot.frontRight.setPower(right);
        robot.backRight.setPower(right);
    } //just sets motor powers

    public void driveStop() {
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
    } //stops all motors

    public void retrieveTelemetry() {
        telemetry.addData("Left Motor Position", robot.backLeft.getCurrentPosition());
        telemetry.addData("Right Motor Position", robot.backRight.getCurrentPosition());
        telemetry.addData("Difference in Position", robot.backLeft.getCurrentPosition() - robot.backRight.getCurrentPosition());
        telemetry.addData("Robot Heading Angle", robot.getHeading());
        telemetry.update();
    }

    public void turnByAngle(double angle) {
        robot.backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.imu.resetYaw();
        //finds how far the robot needs to go
        if (angle < 0) {
            driveFunction(-.375, .375); //sets initial motor powers
            while (robot.getHeading() > (angle + 30)) {
                //empty like my soul
                retrieveTelemetry();
            }//waits until the robot only needs to turn 30 more degrees
            driveFunction(-.25, .25); //robot slows down to be more accurate
            while (robot.getHeading() > (angle + 5)) {
                //EMPTY ON PURPOSE LOSER
                retrieveTelemetry();
            }// waits for the robot to turn all the way
        } else {
            driveFunction(.375, -.375);//sets initial motor powers
            while (robot.getHeading() < (angle - 30)) {
                retrieveTelemetry();
                //do I have to say it again?
            } //waits until the robot only has to turn 30 more degrees
            driveFunction(.25, -.25); //robot slows to be more accurate
            while (robot.getHeading() < (angle - 5)) {
                retrieveTelemetry();
                //EMPTY ON PURPOSE EVEN WORSE LOSER
            } //waits for the robot to turn to the specified angle
        }
        driveStop(); //stops robot after it has turned
    }

    public void moveWithEncoders(int leftPos, int rightPos) { //velocity is in ticks per second not percentages
//        double CPR = 28 * (28); //counts per revolution 28 times gear ratio 20:1
//        double circumference = Math.PI * 115;
//        double countsPerMM = CPR / circumference;
//        double leftPos = ((leftTarget/304.8) * countsPerMM);
//        double rightPos =((rightTarget/304.8) * countsPerMM);
        robot.imu.resetYaw();
        robot.backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        double currentPosRight = robot.backRight.getCurrentPosition();
        double currentPosLeft = robot.backLeft.getCurrentPosition();
        double differencesInPos;
        if (leftPos > 0) {
            driveFunction(.35, .35);
            while (currentPosRight < rightPos - 100 && currentPosLeft < leftPos - 100) {
                currentPosLeft = robot.backLeft.getCurrentPosition();
                currentPosRight = robot.backRight.getCurrentPosition();
                differencesInPos = currentPosLeft - currentPosRight;
                retrieveTelemetry();
                if (differencesInPos > 10) {
                    driveFunction(.34, .36);
                }
                if (differencesInPos < -10) {
                    driveFunction(.36, .34);
                }
            }
            driveFunction(.25, .25);
            while (currentPosRight < rightPos - 50 && currentPosLeft < leftPos - 50) {
                currentPosRight = robot.backRight.getCurrentPosition();
                currentPosLeft = robot.backLeft.getCurrentPosition();
                differencesInPos = currentPosLeft - currentPosRight;
                retrieveTelemetry();
                if (differencesInPos >10) {
                    driveFunction(.34, .36);
                }
                if (differencesInPos < -10) {
                    driveFunction(.36, .34);
                }
            }
        } else {
            driveFunction(-.35, -.35);
            while (currentPosRight > rightPos + 100 && currentPosLeft > leftPos + 100) {
                currentPosLeft = robot.backLeft.getCurrentPosition();
                currentPosRight = robot.backRight.getCurrentPosition();
                differencesInPos = currentPosLeft - currentPosRight;
                retrieveTelemetry();
                if (differencesInPos > 10) {
                    driveFunction(-.34, -.36);
                }
                if (differencesInPos < -10) {
                    driveFunction(-.36, -.34);
                }
            }
            driveFunction(-.25, -.25);
            while (currentPosRight > rightPos + 50 && currentPosLeft > leftPos + 50) {
                currentPosLeft = robot.backLeft.getCurrentPosition();
                currentPosRight = robot.backRight.getCurrentPosition();
                differencesInPos = currentPosLeft - currentPosRight;
                if (differencesInPos > 10) {
                    retrieveTelemetry();
                    driveFunction(-.24, -.26);
                }
                if (differencesInPos < -10) {
                    driveFunction(-.24, -.26);
                }
            }
        }
        if (robot.getHeading() != 0) {
            double angle = -(robot.getHeading());
            turnByAngle(angle);
        }
        driveStop();
//    public void shootArtifacts() {
//        double CPR = 28 * (1/20);
//        double TPS = (175/60) * CPR;
//        robot.flywheelMotor.setVelocity(TPS);
//        sleep(3000); //flywheel get to speed
//        robot.flywheelServo.setPower(.5);
//        sleep(1000); //wait for shooting to happen loser
//        robot.flywheelServo.setPower(0);
//        robot.flywheelMotor.setVelocity(0);
//    }
//    public void intakeArtifact() {
//        double CPR = 28 * (29/860);
//        double TPS = (175/60) * CPR;
//        robot.intakeMotor.setVelocity(TPS);
//    }
//    public void stopIntake() {
//        robot.intakeMotor.setVelocity(0);
//    }
    }
}
