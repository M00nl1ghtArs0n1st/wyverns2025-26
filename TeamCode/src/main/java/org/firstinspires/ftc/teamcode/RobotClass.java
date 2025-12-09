package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;

public class RobotClass {
        public DcMotorEx frontLeft, frontRight, backRight, backLeft/*, intakeMotor, flywheelMotor */;
//        public CRServo flywheelServo;
        public IMU imu;
        public Telemetry telemetry;
//        public Limelight3A limelight;

        public RobotClass(HardwareMap hardwareMap){


            // configures your robot so that the program can interact with it
//            limelight = hardwareMap.get(Limelight3A.class, "limelight");

//            intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");
//            flywheelMotor = hardwareMap.get(DcMotorEx.class, "flywheelMotor");
//            flywheelServo = hardwareMap.get(CRServo.class, "flywheelServo");

            frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
            frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
            backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
            backRight = hardwareMap.get(DcMotorEx.class, "backRight");




            // robot configuration for test chassis
//            intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
//            flywheelMotor.setDirection(DcMotorSimple.Direction.FORWARD);
//            flywheelServo.setDirection(CRServo.Direction.FORWARD);

            backLeft.setDirection(DcMotorSimple.Direction.FORWARD); //sixWheelPracticeBot REVERSE
            frontLeft.setDirection(DcMotorSimple.Direction.FORWARD); //sixWheelPracticeBotREVERSE
            backRight.setDirection(DcMotorSimple.Direction.REVERSE); //sixWheelPracticeBot FORWARD
            frontRight.setDirection(DcMotorSimple.Direction.REVERSE); //sixWheelPracticeBot FORWARD

//            intakeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//            flywheelMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

            frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER); //STOP_AND RESET
            frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER); //With just back medium amount to the right, with just front slightly to left, with all of them a lot to the left

//            intakeMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//            flywheelMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

            frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

//            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            // Retrieve the IMU from the hardware map
            imu = hardwareMap.get(IMU.class, "imu");
            // Adjust the orientation parameters to match your robot
            IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
            // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
            imu.initialize(parameters);
        }
    public double getHeading() {
        Orientation theta = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
        return theta.thirdAngle;
    }
}
// TO DO
//FRONT RIGHT MOTOR MAIN ISSUE, FIX OR DIE
//