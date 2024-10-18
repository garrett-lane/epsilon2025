package org.firstinspires.ftc.teamcode;

// RR-specific imports
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;


//Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Config
@Autonomous(name = "TEST_AUTO_ITD", group = "Autonomous")
public class TestAutoITD extends LinearOpMode {


    // lift class
    public class pluh {
        private DcMotor Pluh;

        public void pluh(@NonNull HardwareMap hardwareMap) {
            Pluh = hardwareMap.get(DcMotor.class, "Pluh");
            Pluh.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Pluh.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        public class MotorOn implements Action {
            // checks if the lift motor has been powered on


            // actions are formatted via telemetry packets as below
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                // powers on motor, if it is not on

                Pluh.setPower(1);
                return false;
            }

            public Action MotorOn() {
                return new MotorOn();
            }
        }

        public class MotorOff implements Action {
            // checks if the lift motor has been powered on


            // actions are formatted via telemetry packets as below
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                // powers on motor, if it is not on

                Pluh.setPower(0);
                return false;
            }

            public Action MotorOff() {
                return new MotorOff();
            }
        }
    }
        // claw class
        public class superPluh {
            private Servo SuperPluh;

            public superPluh(HardwareMap hardwareMap) {
                SuperPluh = hardwareMap.get(Servo.class, "SuperPluh");
            }

            public class ServoRight implements Action {
                @Override
                public boolean run(@NonNull TelemetryPacket packet) {
                    SuperPluh.setPosition(1);
                    return false;
                }
            }

            public Action ServoRight() {
                return new ServoRight();
            }

            public class ServoLeft implements Action {
                @Override
                public boolean run(@NonNull TelemetryPacket packet) {
                    SuperPluh.setPosition(0.55);
                    return false;
                }
            }

            public Action ServoLeft() {
                return new ServoLeft();
            }
        }

        // Declare OpMode members for each of the 4 motors.
        private ElapsedTime runtime = new ElapsedTime();
        private Servo SuperPluh = null;
        private DcMotor leftFrontDrive = null;
        private DcMotor leftBackDrive = null;
        private DcMotor rightFrontDrive = null;
        private DcMotor rightBackDrive = null;

        public void runOpMode() {
            leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFront");
            leftBackDrive = hardwareMap.get(DcMotor.class, "leftBack");
            rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFront");
            rightBackDrive = hardwareMap.get(DcMotor.class, "rightBack");


            MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(64, 0, Math.toRadians(180)));

            Action MoveToSub;
            Action MoveToBasket;
            Action PickUpBlock1;
            Action PickUpBlock2;
            Action PickUpBlock3;
            Action PlaceInBasket;
            Action Park;
            Action TestMotorOn;
            Action TestMotorOff;
            Action TestServoOn;
            Action TestServoOff;
            Action AllMovements;

            MoveToSub = drive.actionBuilder(drive.pose)
                    //Move to Submersible

                    .strafeTo(new Vector2d(24, 0))
                    .build();

            MoveToBasket = drive.actionBuilder(drive.pose)
                    //Move to Baskets
                    .strafeTo(new Vector2d(36, 0))
                    .strafeTo(new Vector2d(60, -60))
                    .build();

            PickUpBlock1 = drive.actionBuilder(drive.pose)
                    //Pick up First Block
                    .strafeTo(new Vector2d(24, -60))
                    .build();

            PickUpBlock2 = drive.actionBuilder(drive.pose)
                    //Pick up Second Block
                    .strafeTo(new Vector2d(60, -48))
                    .strafeTo(new Vector2d(24, -48))
                    .build();

            PickUpBlock3 = drive.actionBuilder(drive.pose)
                    //Pick up Second Block
                    .strafeToLinearHeading(new Vector2d(24, -60), -90)
                    .strafeTo(new Vector2d(32, -60))
                    .build();


            PlaceInBasket = drive.actionBuilder(drive.pose)
                    //Place in Basket
                    .strafeToLinearHeading(new Vector2d(64.25,0), 0)
                    .build();
            AllMovements = drive.actionBuilder(drive.pose)
                    .strafeTo(new Vector2d(10, 0))
                    // slide up
                    //.waitSeconds(2)
                    //.strafeToLinearHeading(new Vector2d(35,-42 ),315)
                    //.strafeTo(new Vector2d(15,-52))
                    //.strafeToLinearHeading(new Vector2d(68,-68),325)
                    .waitSeconds(2)
                    //.strafeTo(new Vector2d(50, -50))
                    // turn 90
                    //.waitSeconds(2)
                    //.strafeToLinearHeading(new Vector2d(36, -58), 0)
                    //.strafeTo(new Vector2d(50, -50))
                    //.waitSeconds(2)
                    //.strafeToLinearHeading(new Vector2d(21, -48),-92.68)
                    //turn back to 0
                    //.strafeToLinearHeading(new Vector2d(55, -55), 0)
                    //.waitSeconds(2)
                    //.strafeTo(new Vector2d(55,-55))
                    .build();






            //park


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
                            //MoveToSub,
                            //MoveToBasket,
                            //PickUpBlock1,
                            //PlaceInBasket,
                            //PickUpBlock2,
                            //PlaceInBasket,
                            //PickUpBlock3,
                            //PlaceInBasket
                            AllMovements
                            )



                    );



        }

}