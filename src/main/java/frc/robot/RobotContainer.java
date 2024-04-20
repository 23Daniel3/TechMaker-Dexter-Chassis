package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * Essa classe junta todos os elementos do  robo e  gerencia os funcionamento geral,
 * associando subsistemas e comandos
 */
public class RobotContainer {
  // Cria um objeto de cada subsistema
  private final Drivetrain drive = Drivetrain.getInstance();
  private final CommandXboxController driver = new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static final ShuffleboardTab mainTab = Shuffleboard.getTab("Robot");
  private static final String kNothingAuto = "nothing";
  private static final String kchargerStation = "station";
  private static final String kTaxi = "leave";

 // Trigger tLowBatt = new Trigger(pdp::getLowVoltage);

  public RobotContainer() {
    m_chooser.setDefaultOption("Ficar Parado", kNothingAuto);
    m_chooser.addOption("Subir na Charger Station", kchargerStation);
    m_chooser.addOption("Sair da area", kTaxi);
    SmartDashboard.putData("Comportamento", m_chooser);
    configureBindings();
  }

  private void configureBindings() {

    //Seta a navegação padrão pelo  controle
     drive.setDefaultCommand(new RunCommand(()->drive.setDriveMotors(driver.getLeftY()*-1, 
                              driver.getLeftX()*-1), drive));
   
   

    //tLowBatt.onTrue(new InstantCommand(()->SmartDashboard.putString("ALERTA BATERIA", "BATERIA BAIXA")))
    //        .onFalse(new InstantCommand(()->SmartDashboard.putString("ALERTA BATERIA", "BATERIA OK")));

    
  }
    public Command getAutonomousCommand() {
    return null;
    }
}

