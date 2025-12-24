package windows;

import database.DBHandlerEmployer;
import database.DBHandlerVacancy;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import models.UserEmployer;


public class WindowsAddVacancy extends JFrame {

    JLabel girlTypeLabel, jDescribeLabel, requirementsLabel;
    JTextArea jDescribeArea, requirementsArea;
    JButton addButton, backButton;
    UserEmployer employer;
    JComboBox<String> girlTypeCombo;

    Font font = new Font("Arial", Font.ITALIC, 16);

    public WindowsAddVacancy(UserEmployer employer) {
        super("Добавление вакансии");
        this.employer = employer;
        
        super.setBounds(550, 250, 600, 700);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setResizable(false);
        super.setLocationRelativeTo(null);

        Container container = super.getContentPane();
        container.setLayout(null);

        girlTypeLabel = new JLabel("Кого ищем: ");
        String[] girlTypes = {"Милфа", "Альтушка", "Любой"};
        girlTypeCombo = new JComboBox<>(girlTypes);

        jDescribeLabel = new JLabel("Описание вакансии:");
        jDescribeArea = new JTextArea();
        jDescribeArea.setLineWrap(true);
        JScrollPane descScroll = new JScrollPane(jDescribeArea);


        requirementsLabel = new JLabel("Требования:");
        requirementsArea = new JTextArea();
        requirementsArea.setLineWrap(true);
        JScrollPane reqScroll = new JScrollPane(requirementsArea);

        addButton = new JButton("Опубликовать");
        backButton = new JButton("Назад");

        addButton.addActionListener(new PublicationButtonLisner());
        backButton.addActionListener(new BackbuttonLisner());

        girlTypeLabel.setFont(font);
        girlTypeCombo.setFont(font);
        jDescribeLabel.setFont(font);
        requirementsLabel.setFont(font);
        addButton.setFont(font);
        backButton.setFont(font);

        girlTypeLabel.setBounds(100, 100, 200, 30);
        girlTypeCombo.setBounds(300, 100, 200, 30);
        jDescribeLabel.setBounds(100, 150, 200, 30);
        descScroll.setBounds(100, 200, 400, 100);
        requirementsLabel.setBounds(100, 350, 200, 30);
        reqScroll.setBounds(100, 400, 400, 100);
        addButton.setBounds(100, 550, 400, 30);
        backButton.setBounds(100, 590, 400, 30);

        container.add(girlTypeLabel);
        container.add(girlTypeCombo);
        container.add(jDescribeLabel);
        container.add(descScroll);
        container.add(requirementsLabel);
        container.add(reqScroll);
        container.add(addButton);
        container.add(backButton);
    }

    class PublicationButtonLisner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String girlType = (String) girlTypeCombo.getSelectedItem();
            String jobDescribe = jDescribeArea.getText();
            String requirements = requirementsArea.getText();


            if(jobDescribe.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Заполните описание вакансии!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(requirements.isEmpty()){
                JOptionPane.showMessageDialog(null, "Заполните требования вакансии!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }


            DBHandlerVacancy dbHandlerVacancy = new DBHandlerVacancy();

            dbHandlerVacancy.addVacancy(employer.getName(), jobDescribe, girlType, requirements);

            JOptionPane.showMessageDialog(null, "Вакансия добавлена!");
            
            dispose();

            DBHandlerEmployer dbHandlerEmployer = new DBHandlerEmployer();
            UserEmployer updateEmployer = dbHandlerEmployer.getInfoEmp(employer.getName());
            new WindowsMainEmp(updateEmployer).setVisible(true);
        }
    }
    
    class BackbuttonLisner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();

            DBHandlerEmployer dbHandlerEmployer = new DBHandlerEmployer();
            UserEmployer updateEmployer = dbHandlerEmployer.getInfoEmp(employer.getName());
            new WindowsMainEmp(updateEmployer).setVisible(true);
        }
    }
}