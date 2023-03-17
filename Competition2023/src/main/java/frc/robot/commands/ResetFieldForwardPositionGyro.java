// Copyright (c) FIRST and other WPILib contributors.
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;

public class ResetFieldForwardPositionGyro extends CommandBase {
  /** Creates a new ResetFieldForwardPositionGyro. */
  public ResetFieldForwardPositionGyro() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.SWERVE_DRIVE_OBJ.resetFieldCentricity();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
