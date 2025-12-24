package windows;



import database.DBHandlerEmployer;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import models.UserEmployer;
import utils.RefreshResponsesProfile;

public class WindowsResponsesProfile extends JFrame{

    JButton backButton;

    Font font = new Font("Arial", Font.ITALIC, 16);
    Font font18 = new Font("Arial", Font.ITALIC, 18);
    Font font20 = new Font("Arial", Font.ITALIC, 20);

    
    public WindowsResponsesProfile(int idVacancy, String login){
        super("Профили девушек id: " + idVacancy);

        super.setBounds(900, 250, 550, 600); 
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setResizable(false);
        super.setLocationRelativeTo(null);

        Container container = super.getContentPane();
        container.setLayout(null);

        backButton = new JButton("Назад");
        backButton.setFont(font);
        backButton.addActionListener(e ->{
            dispose();
            DBHandlerEmployer dbHandlerEmployer = new DBHandlerEmployer();
            UserEmployer employer = dbHandlerEmployer.getInfoEmp(login);
            new WindowsResponsesVacancy(employer.getName(), employer.getPassword()).setVisible(true);
        });
        backButton.setBounds(20, 20, 150, 30);
        container.add(backButton);

        RefreshResponsesProfile.refreshResponsesProfile(container, idVacancy);
    }
}
