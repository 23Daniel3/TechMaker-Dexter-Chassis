package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveBalance;
import frc.robot.commands.DriveDistance;
import frc.robot.subsystems.Drivetrain;

public class AutoChargeStation extends SequentialCommandGroup {
  public AutoChargeStation() {
    addCommands(
      new DriveDistance(4,Drivetrain.getInstance()),
      new DriveBalance()
    );
  }
}
