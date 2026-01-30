package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@Autonomous
public class autoBlue extends LinearOpMode {
    RobotClass robot;

    @Override
    public void runOpMode() {
        robot = new RobotClass(hardwareMap);
        robot.imu.resetYaw();
//        robot.limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
//        robot.limelight.start();
        waitForStart();
        moveWithEncoders(1650);
        sleep(2000);
        turnByAngle(-43);
        sleep(500);
        shootArtifacts(2);
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
        boolean controlHubData = false;
//        LLResult result = robot.limelight.getLatestResult();
        telemetry.addData("Back Left Motor Position", robot.backLeft.getCurrentPosition());
        telemetry.addData("Back Right Motor Position", robot.backRight.getCurrentPosition());
        telemetry.addData("Front Left Motor Position", robot.frontLeft.getCurrentPosition());
        telemetry.addData("Front Right Motor Position", robot.frontRight.getCurrentPosition());
        telemetry.addData("Difference in Position", robot.backLeft.getCurrentPosition() - robot.backRight.getCurrentPosition());
        telemetry.addData("Robot Heading Angle", robot.getHeading());
        telemetry.addData("control hub updated?", controlHubData);
//        if (result.isValid()) {
//            telemetry.addData("Target X", result.getTx()); //in degrees thats crazy
//            telemetry.addData("Target Y", result.getTy()); //in degrees thats wild
//            telemetry.addData("Target area", result.getTa()); //percentage from 0% to 100%
//        }
        telemetry.update();
    }

    public void turnByAngle(double angle) {
        robot.imu.resetYaw();
        //finds how far the robot needs to go
        double currentAngle = -robot.getHeading();
        if (angle < 0) {
            driveFunction(-.25, .25); //sets initial motor powers
            while (currentAngle > angle + 30) {
                //empty like my soul
                currentAngle = -robot.getHeading();
                retrieveTelemetry();
            }//waits until the robot only needs to turn 30 more degrees
            driveFunction(-.15, .15); //robot slows down to be more accurate
            while (currentAngle >  angle + 5) {
                //EMPTY ON PURPOSE LOSER
                currentAngle = -robot.getHeading();
                retrieveTelemetry();
            }// waits for the robot to turn all the way
        } else {
            driveFunction(.25, -.25);// sets initial motor powers
            while (currentAngle <  angle -30) {
                currentAngle = -robot.getHeading();
                retrieveTelemetry();
                //do I have to say it again?
            } //waits until the robot only has to turn 30 more degrees
            driveFunction(.15, -.15); //robot slows to be more accurate
            while (currentAngle < angle - 5) {
                currentAngle = -robot.getHeading();
                retrieveTelemetry();
                //EMPTY ON PURPOSE EVEN WORSE LOSER
            } //waits for the robot to turn to the specified angle
        }
        driveStop(); //stops robot after it has turned
    }

    public void moveWithEncoders(double position) { //velocity is in ticks per second not percentages
//        double CPR = 28 * (28); //counts per revolution 28 times gear ratio 20:1
//        double circumference = Math.PI * 115;
//        double countsPerMM = CPR / circumference;
//        double positionTicks = ((position/304.8) * countsPerMM);
        robot.imu.resetYaw();
        robot.backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        double currentPos = -robot.backRight.getCurrentPosition();
        if (position > 0) {
            driveFunction(.35, .35);//CHANGE BACK TO .35
            while (currentPos < position - 100) {
                currentPos = -robot.frontRight.getCurrentPosition();
                retrieveTelemetry();
            }
            driveFunction(.25, .25);//CHANGE BACK TO .25
            while (currentPos < position - 50) {
                currentPos = -robot.frontRight.getCurrentPosition();
                retrieveTelemetry();
            }
        } else {
            driveFunction(-.35, -.35); //CHANGE BACK TO .4
            while (currentPos > position + 100) {
                currentPos = -robot.backLeft.getCurrentPosition();
                retrieveTelemetry();
            }
            driveFunction(-.25, -.25);//CHANGE BACK TO .3
            while (currentPos > position + 50) {
                currentPos = -robot.backLeft.getCurrentPosition();
                retrieveTelemetry();
            }
        }
        driveStop();
    }
    public void shootArtifacts(long amountOfArtifacts) {
//         LLResult result = robot.limelight.getLatestResult();
        double CPR =28; //Counts per revolution
        double driveGearReduction = 1;
        double CPW = CPR * driveGearReduction; // counts per wheel
        double targetRPM = 2500;
        double TPS = (targetRPM/ 60) * CPW;
        retrieveTelemetry();
//         double tx = result.getTx();
//         double ty = result.getTy();
//         double ta = result.getTa();
//         while (tx > (farthest right target can be) && tx > (farthest left target can be) && too far away (too small target)< ta < too close(too large target)){
//             while (tx > (farthest right target can be)){
//                 retrieveTelemetry();
//                 result = robot.limelight.getLatestResult();
//                 ty = result.getTy();
//                 tx = result.getTx();
//                 driveFunction(.1,-.1);
//             }
//             while (tx > (farthest left target can be)){
//                 retrieveTelemetry();
//                 result = robot.limelight.getLatestResult();
//                 ty = result.getTy();
//                 tx = result.getTx();
//                 driveFunction(-.1,.1);
//             }
//             while (too far away (too small target) < ta) {
//                 retrieveTelemetry();
//                 result = robot.limelight.getLatestResult();
//                 ta = result.getTa();
//                 driveFunction(.1,.1);
//             }
//             while (ta < too close(too large target)) {
//                 retrieveTelemetry();
//                 result = robot.limelight.getLatestResult();
//                 ta = result.getTa();
//                 driveFunction(-.1,-.1);
//             }
//         }
        driveFunction(0,0);
        robot.flywheelMotor.setVelocity(TPS);
        sleep(7000); //flywheel get to speed
        robot.intakeMotor.setPower(-.25);
        robot.flywheelServo.setPower(.5);
        sleep(3000); //wait for shooting to happen loser
        robot.intakeMotor.setPower(0);
        sleep(2000);
        robot.flywheelServo.setPower(0);
        amountOfArtifacts = amountOfArtifacts - 1;
        while (amountOfArtifacts != 0) {
            sleep(500);
            robot.intakeMotor.setPower(.25);
            sleep(500);
            robot.flywheelServo.setPower(.5);
            sleep(2500);
            robot.intakeMotor.setPower(-.25);
            sleep(2000);
            robot.intakeMotor.setPower(0);
            robot.flywheelServo.setPower(0);
            amountOfArtifacts = amountOfArtifacts - 1;
        }
        robot.flywheelMotor.setVelocity(0);
    }
}