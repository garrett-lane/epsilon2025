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
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private Servo swivel;
    private Servo clawRot;
    private DcMotor slideLeft;
    private Servo freakyClaw;
    private DcMotor slideRight;
    private Servo armL;
    private Servo armR;
    private Servo outakeSwivel;
    private Servo outakeClaw;
    private DcMotor horSlide;

//lift class
    
    @Override
    public void runOpMode() {
        // Motors and Servos - this is where the configuration is assigned. Assign motors and names in the config on the driver station first, then do it here, you will not see this in block code
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        //initializing non-drive motors as classes so that they can be later called as actions
        class swivel {
            private Servo swivel;

            public swivel(HardwareMap hardwareMap) {
                swivel = hardwareMap.get(Servo.class, "Swivel");
            }

            class Down implements Action {

            //sets the swivel to the downward position

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    swivel.setPosition(0.75);
                    return false;
                }
            }

            class Up implements Action {

            //sets the swivel to the upward position

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    swivel.setPosition(0.19);
                    return false;
                }
            }
        }
        class FreakyClaw {
            private final Servo freakyClaw;

            public FreakyClaw(HardwareMap hardwareMap) {
                freakyClaw = hardwareMap.get(Servo.class, "clawRot");
            }

            class Open implements Action {

            //opens the freaky claw

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    freakyClaw.setPosition(0.85);
                    return false;
                }

                public Action open() {
                    return new Open();
                }
            }

            class Close implements Action {

            //closes the freaky claw

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    freakyClaw.setPosition(0.65);
                    return false;
                }
            }
        }
        class slides {
            private DcMotor slideLeft;
            private DcMotor slideRight;

            public slides(HardwareMap hardwareMap) {
                slideLeft = hardwareMap.get(DcMotor.class, "slideLeft");
                slideRight = hardwareMap.get(DcMotor.class, "slideRight");
            }

            class Up implements Action {

            //raises the slide rails to place in the top bucket

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    slideLeft.setTargetPosition(4000);
                    slideRight.setTargetPosition(4000);
                    slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    slideLeft.setPower(1);
                    slideRight.setPower(1);
                    return false;
                }
            }

            class Down implements Action {

            //lowers the slide rail back down to the default position

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    slideLeft.setTargetPosition(0);
                    slideRight.setTargetPosition(0);
                    slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    slideLeft.setPower(1);
                    slideRight.setPower(1);
                    return false;
                }
            }
        }
        class clawRot {
            private Servo clawRot;

            public clawRot(HardwareMap hardwareMap) {
                clawRot = hardwareMap.get(Servo.class, "freakyClaw");
            }

            class turn45 implements Action {

            //turns the claw 45 degrees

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    clawRot.setPosition(0.75);
                    return false;
                }
            }

            class reset implements Action {

            //sets the claw rotation back to the default value

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    clawRot.setPosition(0.5);
                    return false;
                }
            }
        }
        class arms {
            private Servo armL;
            private Servo armR;

            public arms(HardwareMap hardwareMap) {
                armL = hardwareMap.get(Servo.class, "armL");
                armR = hardwareMap.get(Servo.class, "armR");
            }

            class up implements Action {

            //sets the arms to the upward position

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    armL.setPosition(0.55);
                    armR.setPosition(0.45);
                    return false;
                }
            }

            class down implements Action {

            //sets the arms to the downward position

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    armL.setPosition(1);
                    armR.setPosition(0);
                    return false;
                }
            }
        }
        class outakeClaw {
            private final Servo outakeClaw;

            public outakeClaw(HardwareMap hardwareMap) {
                outakeClaw = hardwareMap.get(Servo.class, "outakeClaw");
            }

            class open implements Action {

            //opens the outake claw

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    outakeClaw.setPosition(0.75);
                    return false;
                }
            }
            class close implements Action {

            //closes the outake claw

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    outakeClaw.setPosition(1);
                    return false;
                }
            }
        }
        class outakeSwivel {
            private Servo outakeSwivel;

            public outakeSwivel(HardwareMap hardwareMap) {
                outakeSwivel = hardwareMap.get(Servo.class, "outakeSwivel");
            }

            class in implements Action {

            //rotates the outake swivel into the robot

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    outakeSwivel.setPosition(0.95);
                    return false;
                }
            }

            class out implements Action {

            //rotates the outake swivel outwards to outake

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    outakeSwivel.setPosition(0.5);
                    return false;
                }
            }
        }
        class HorSlide {
            private DcMotorEx horSlide;

            public HorSlide(HardwareMap hardwareMap) {
                horSlide = hardwareMap.get(DcMotorEx.class, "horSlide");
            }

            class Out implements Action {

            //extends the horizontal slides to pick up blocks

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    horSlide.setTargetPosition(800);
                    horSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    horSlide.setPower(1);
                    return false;
                }
                public Action out() {
                    return new Out();
                }
            }

            public Action Test() {
                return new Action() {
                    private boolean initialized = false;

                    @Override
                    public boolean run(@NonNull TelemetryPacket packet) {
                        if (!initialized) {
                            horSlide.setPower(0.8);
                            initialized = true;
                        }

                        double vel = horSlide.getVelocity();
                        packet.put("shooterVelocity", vel);
                        return vel < 10_000.0;

                    }
                };
            };

            class In implements Action {

            //resets the horizontal slides

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    horSlide.setTargetPosition(0);
                    horSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    horSlide.setPower(1);
                    return false;
                }
                public Action in() {
                    return new In();
                }
            }
        }

        // Initialization behavior and positions
        swivel.setPosition(0.04);
        armL.setPosition(0.08);
        armR.setPosition(0.04);
        outakeClaw.setPosition(0.7);
        outakeSwivel.setPosition(0.4);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        swivel.setPosition(0.04);
        freakyClaw.setPosition(0.3);
        clawRot.setPosition(0.52);
        slideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setDirection(DcMotor.Direction.REVERSE);
        slideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outakeClaw.setDirection(Servo.Direction.REVERSE);
        horSlide.setDirection((DcMotor.Direction.REVERSE));
        armL.setDirection(Servo.Direction.REVERSE);


        // Sets the robot's starting position and heading, assigning it to the startPose variable.
        Pose2d startPose = new Pose2d(32, 64.5, Math.toRadians(0));
        // Sets the starting pose (position)
        MecanumDrive drive = new MecanumDrive(hardwareMap, startPose);

        Action Movement1;
        Action Movement2;
        Action Movement3;
        Action Movement4;
        Action Movement5;
        Action Movement6;
        Action Movement7;
        Action Movement8;
        Action Movement9;


        Movement1 = drive.actionBuilder(drive.pose)
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(54, 64.5), Math.toRadians(12.5))
                .afterTime(0.5, HorSlide.Test())
               .build();

        Movement2 = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(51.5, 65), Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(-20, 64.75), Math.toRadians(0))
                .waitSeconds(3)
                .build();

        Movement3 = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(54, 61.5), Math.toRadians(15))
                .waitSeconds(1)
                .build();

        Movement4 = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(50.5, 44.5), Math.toRadians(92))
                .waitSeconds(1.9)
                .build();

        Movement5 = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(59, 53.5), Math.toRadians(45))
                .waitSeconds(0.25)
                .build();

        Movement6 = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(60.5, 44.5), Math.toRadians(90))
                .waitSeconds(1.9)
                .build();

        Movement7 = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(57, 53.5), Math.toRadians(45))
                .waitSeconds(1)
                .build();

        Movement8 = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(60, 41.5), Math.toRadians(132.5))
                .waitSeconds(2)
                .build();

        Movement9 = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(57, 56.5), Math.toRadians(45))
                .waitSeconds(0.24)
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
                        Movement1,
                        Movement2,
                        Movement3,
                        Movement4,
                        Movement5,
                        Movement6,
                        Movement7,
                        Movement8,
                        Movement9
                )
        );

    }
}