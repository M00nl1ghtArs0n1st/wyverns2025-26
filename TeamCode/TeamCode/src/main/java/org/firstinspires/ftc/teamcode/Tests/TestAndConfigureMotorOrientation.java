package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotClass;

@Autonomous
public class TestAndConfigureMotorOrientation extends LinearOpMode {

    RobotClass robot;

    @Override
    public void runOpMode() throws InterruptedException{
        // defines robot configuration Directories
        // NOTE: this must be defined in the OP mode or the program will be unresponsive
        // and it will through no errors
        robot = new RobotClass(hardwareMap);

        //makes the play button appear and is required for competition
        waitForStart();


        while (!gamepad1.a && !isStopRequested()) {
            // watch the motors on the drive train and they should all spin forward, if they don't then you need to reverse them if
            // they don't spin at all then you need to fix the hardware issue

            // spins the all of the drivetrain motors in a positive direction
            // NOTE: they all should spin at the same power as well if they do not check the
            // voltage on the driver station and make sure the motors are configured correctly

            robot.frontLeft.setPower(1);
            robot.frontRight.setPower(1);
            robot.backLeft.setPower(1);
            robot.backRight.setPower(1);


        }
        // if you press the controller button a then you stop all motors
        stopMotors();
    }
    // stops the motors in the drivetrain by setting the power to zero
    private void stopMotors() {
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);
    }
}
