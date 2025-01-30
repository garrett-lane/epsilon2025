package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12.5)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(32, 64.5, Math.toRadians(0)))
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(54, 64.5), Math.toRadians(12.5))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(50.5, 44.5), Math.toRadians(92))
                .waitSeconds(1.9)
                .strafeToLinearHeading(new Vector2d(59, 53.5), Math.toRadians(45))
                .waitSeconds(0.25)
                .strafeToLinearHeading(new Vector2d(60.5, 44.5), Math.toRadians(90))
                .waitSeconds(1.9)
                .strafeToLinearHeading(new Vector2d(57, 53.5), Math.toRadians(45))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(60, 41.5), Math.toRadians(132.5))
                //block 1
                .waitSeconds(2) // Tells the Robot to wait 1.5 seconds after completing the previous movement.
                .strafeToLinearHeading(new Vector2d(57, 56.5), Math.toRadians(45))
                .waitSeconds(0.24)
                .build());



        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}