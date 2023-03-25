package frc.robot.commands.autonomousCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.RobotContainer;

public class AutonomousLRCommunityZone extends SequentialCommandGroup{
    public AutonomousLRCommunityZone(){
        System.out.println("Running autonomous get out of community zone command...");
        addCommands(            
            // move robot forward at 40% power for 2.25 seconds
            new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, -0.4, 0.0)),
            new WaitCommand(2.25),

            // stop robot
            new InstantCommand(() -> RobotContainer.SWERVE_DRIVE_OBJ.convertSwerveValues(0.0, 0.0, 0.0))
        );
    }
}