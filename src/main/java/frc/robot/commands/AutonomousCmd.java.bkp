// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.autonomousConstants;
import frc.robot.subsystems.Drivetrain;

public class AutonomousCmd extends Command {
  Drivetrain drive = Drivetrain.getInstance();
  int lastDistancePosition = 0;
  double lastDistance = autonomousConstants.kDistanceToPark[lastDistancePosition];
  int status = 0;
  int direction = 1;
  double timeout;
  boolean park = true;

  public AutonomousCmd(boolean park) {
    addRequirements(drive);
    lastDistancePosition = 0;
    lastDistance = autonomousConstants.kDistanceToPark[lastDistancePosition];
    status = 0;
    direction = 1;
    drive.arcadeDrive(autonomousConstants.kDriveSpeed, 0);
    drive.brake(true);
    drive.resetEncoders();
    this.park = park;
    SmartDashboard.putBoolean("Park", park);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // * ATENÇÃO

  //Este autônomo teve todos os Subsistemas do operador removidos 


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Timer.getFPGATimestamp()>timeout){
      if(status == 0){
          timeout = Timer.getFPGATimestamp()+autonomousConstants.kExtendArmTime;
          status = 1;        
      }
      else if(status==1){
            timeout = Timer.getFPGATimestamp()+autonomousConstants.kExtendArmTime;
           status = 3; 
      }
      else if(status==3){
        timeout = Timer.getFPGATimestamp()+autonomousConstants.kReleaseTime;
        status = 4; 
      }
      else if(status==4){
        timeout = Timer.getFPGATimestamp()+autonomousConstants.kExtendArmTime;
        status = 5; 
      }
      else if(status==5){
        drive.resetEncoders();
        drive.setDriveMotors(-autonomousConstants.kDriveTaxi,0); 
        if(park){
          status=7;
          drive.setDriveMotors(-autonomousConstants.kDriveTaxi,0); 

        }
        else{
          status = 6; 
        }        

      }
      else if (status==6){
        if(Math.abs(drive.GetAverageEncoderDistance())>Math.abs(autonomousConstants.kDistanceOutCommunity)){
          drive.arcadeDrive(0, 0);
          if(park == false)
          {
            status = 11;
          }
          else{
            timeout=Timer.getFPGATimestamp()+autonomousConstants.kWaitTimeToDock;
            status = 7;
          }
        }
        else{
          drive.setDriveMotors(-autonomousConstants.kDriveTaxi,0); 
        }
      } 
      else if (status==7){
        if(Math.abs(drive.GetAverageEncoderDistance())>Math.abs(autonomousConstants.kDistanceDock)){
          drive.arcadeDrive(0, 0);
            timeout=Timer.getFPGATimestamp()+autonomousConstants.kWaitTimeToDock;
            drive.resetEncoders();
            status = 10;
          
        }
        else{
          drive.setDriveMotors(-autonomousConstants.kDriveTaxi,0); 
        }
      } 
      else if(status==10)
      {
          drive.arcadeDrive(drive.GetAverageEncoderDistance()*-20, 0);
      }
      else if(status==11){
        drive.stopDrivetrain();
      }
    }
    else{
      drive.arcadeDrive(0, 0);;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (status == 11);
  }
}
