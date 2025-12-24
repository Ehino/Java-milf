package windows;

import database.DatabaseHandler;
import java.awt.*;
import javax.swing.JFrame;
import utils.RefreshVacancies;

public class WindowsResponsesVacancy extends JFrame{

    Font font = new Font("Arial", Font.ITALIC, 16);
    Font font18 = new Font("Arial", Font.ITALIC, 18);
    Font font20 = new Font("Arial", Font.ITALIC, 20);

    DatabaseHandler dbHandler = new DatabaseHandler();

    public WindowsResponsesVacancy(String login, String password){
        super("Откликнутые вакансии");

        super.setBounds(900, 250, 550, 500); 
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLocationRelativeTo(null);

        Container container = super.getContentPane();
        container.setLayout(null);
        
        RefreshVacancies.refreshVacancies(container, dbHandler.getUserRole(login, password), login, true);
    }
}
