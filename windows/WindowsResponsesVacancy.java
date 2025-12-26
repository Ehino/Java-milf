package windows;


import database.DBHandlerEmployer;
import database.DatabaseHandler;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import models.UserEmployer;
import utils.RefreshVacancies;

public class WindowsResponsesVacancy extends JFrame{

    private JButton backButton;

    private final Font font = new Font("Arial", Font.ITALIC, 16);
    private final Font font18 = new Font("Arial", Font.ITALIC, 18);
    private final Font font20 = new Font("Arial", Font.ITALIC, 20);

    private final DatabaseHandler dbHandler = new DatabaseHandler();
    private final DBHandlerEmployer dbHandlerEmployer = new DBHandlerEmployer();
    
    public WindowsResponsesVacancy(String login, String password){
        super("Откликнутые вакансии");

        super.setBounds(900, 250, 550, 600); 
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setResizable(false);
        super.setLocationRelativeTo(null);

        Container container = super.getContentPane();
        container.setLayout(null);

        String userRole = dbHandler.getUserRole(login, password);

        backButton = new JButton("Назад");
        backButton.setFont(font);
        backButton.addActionListener(e ->{
            dispose();
            if (userRole.equals("Employer")) {
                UserEmployer employer = dbHandlerEmployer.getInfoEmp(login);
                new WindowsMainEmp(employer).setVisible(true);
            }
        });
        backButton.setBounds(20, 20, 150, 30);
        container.add(backButton);
        
        RefreshVacancies.refreshVacancies(container, userRole, login, true);
    }
}
