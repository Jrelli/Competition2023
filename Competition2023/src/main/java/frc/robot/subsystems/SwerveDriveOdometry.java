package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class SwerveDriveOdometry extends SubsystemBase {
  private double totalDistanceTraveledFL;
  private double totalDistanceTraveledFR;
  private double totalDistanceTraveleBL;
  private double totalDistanceTraveledBR;
  private double currentPosition[] = new double[1]; // X and Y position
  public SwerveDriveOdometry() {}

  @Override
  public void periodic() {
    // TODO: Multiply by circumferance to get the distance, currently in rotations
    totalDistanceTraveledFL = (RobotContainer.SWERVE_DRIVE_OBJ.getFLDrive().getSelectedSensorPosition() 
                              / Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION);
    totalDistanceTraveledFR = (RobotContainer.SWERVE_DRIVE_OBJ.getFRDrive().getSelectedSensorPosition() 
                              / Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION);
    totalDistanceTraveleBL = (RobotContainer.SWERVE_DRIVE_OBJ.getBLDrive().getSelectedSensorPosition() 
                              / Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION);
    totalDistanceTraveledBR = (RobotContainer.SWERVE_DRIVE_OBJ.getBRDrive().getSelectedSensorPosition() 
                              / Constants.SWERVE_WHEEL_COUNTS_PER_REVOLUTION);
  }

  public double getTotalAverageDistanceTraveled(){
    return((totalDistanceTraveledFL + totalDistanceTraveledFR + totalDistanceTraveleBL + totalDistanceTraveledBR)/4);
  }
}