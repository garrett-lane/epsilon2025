package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

//Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MecanumDrive;

import java.security.cert.CertPathBuilderResult;
import java.util.Objects;

@Config
@Autonomous(name = "TEST_AUTO1", group = "Autonomous")
public class TestAuto1 extends LinearOpMode {

    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    @Override
    public void runOpMode() {
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFront");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftBack");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFront");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBack");

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(60, 60, Math.toRadians(0)));

        Action TrajectoryAction1;

        TrajectoryAction1 = drive.actionBuilder(drive.pose)
               .strafeTo(new Vector2d(60, 30))
               .strafeTo(new Vector2d(30, 30))
               .strafeTo(new Vector2d(30, 60))
               .strafeTo(new Vector2d(60, 60))
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(30, 30), 0)
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(60, 60), Math.PI/2)
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(30, 30), Math.PI)
                .strafeTo(new Vector2d(60, 60))
                .strafeTo(new Vector2d(30, 30))
                .strafeTo(new Vector2d(60, 60))
               .build();

        while (!isStopRequested() && !opModeIsActive()) {

            telemetry.addData("You Look Funny", 0);
            telemetry.update();

        }


        telemetry.addData("You smell funny", 0);
        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;

        //Put Movements here

        Actions.runBlocking(
                new SequentialAction(
                        TrajectoryAction1
                )
        );

    }
}