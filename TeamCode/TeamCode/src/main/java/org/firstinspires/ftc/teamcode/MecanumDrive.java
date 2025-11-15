package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class MecanumDrive extends LinearOpMode {
    RobotClass robot;

    double robotHeading;

    @Override
    public void runOpMode() throws InterruptedException {
        //ALL TODOS ARE COMMENTS ARE FROM SUSHI
        //defines robot Directories
        robot = new RobotClass(hardwareMap);
        //Variable names should not be capitalized replace 'robot' with just 'robot'. Only classes should be capitalized.
        //makes the play button appear
        waitForStart();
        while (!isStopRequested()) {
            //define basic
            double strafe = gamepad1.left_stick_x; //X_AXIS_VARIABLE
            double drive = -gamepad1.right_stick_y; //the Y axis is negative, not the x axis. //must be negative Y_AXIS_VARIABLE
            double turn = -gamepad1.left_trigger + gamepad1.right_trigger;

            robotHeading = robot.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            //Rotate the movement direction counter to the bot's rotation
            double driveRotation = (strafe) * Math.sin(-robotHeading) + (drive) * Math.cos(-robotHeading);
            double strafeRotation = (strafe) * Math.cos(-robotHeading) - (drive) * Math.sin(-robotHeading);

            //driveRotation is the Y AXIS and should be: X_AXIS_VARIABLE (strafe) * Math.sin(-robotHeading) + Y_AXIS_VARIABLE (drive) * Math.cos(-robotHeading)
            //   strafeRotation is the X AXIS and should be: X_AXIS_VARIABLE (strafe) * Math.cos(-robotHeading) - Y_AXIS_VARIABLE (drive) * Math.sin(-robotHeading)

            double maxMotorPower = Math.abs(driveRotation) + Math.abs(strafeRotation) + Math.abs(turn); //find the max power
            double denominator = Math.max(maxMotorPower, 1); // used in making all of the powers smaller

            //calculate the denominator after you do the matrix rotation. It should be the last thing you calculate after you manipulate all the variables (Put these two line after line 38)
            // Naming variables should help describe what they are used for not just what they contain. I can see that it is the sunOfDrivePlusStrafePlusTurn but why is that?

            //I know they put it in the wiki but don't just throw lines of code in that you aren't sure of what they do.
            // The intended purpose is to make strafing more aggressive to adjust for the increased motor friction when strafing.
            // but your code is adjusting the Y-AXIS power which is not helpful. Try to understand what code is intended for before implementing, with some exceptions of course.

            robot.frontLeft.setPower((driveRotation + strafeRotation + turn) / denominator);
            robot.frontRight.setPower((driveRotation - strafeRotation - turn) / denominator); // strafe is negative because of the wheels orientation
            robot.backLeft.setPower((driveRotation - strafeRotation + turn) / denominator);
            robot.backRight.setPower((driveRotation + strafeRotation - turn) / denominator);
            //Variable names should start with a lower case letter. Unless its a class.

        }
    }

}
