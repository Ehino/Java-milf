package windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import models.UserEmployer;
import utils.RefreshVacancies;

public class MainEmp extends JFrame {

    JLabel nameLabel, cityLabel, cNameLabel, jDescribeLabel, girlTyprLabel, requirementsLabel, advertStatusLabel;
    JLabel headLabel, aLabel;
    JPanel dopPanel;
    JButton addVacancyButton;

    Font font = new Font("Arial", Font.ITALIC, 16);
    Font font20 = new Font("Arial", Font.ITALIC, 20);

    UserEmployer employer;

    public MainEmp(UserEmployer employer) {
        super("Employer окно профиля");
        this.employer = employer; 

        super.setBounds(900, 250, 550, 800); 
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLocationRelativeTo(null);

        Container container = super.getContentPane();
        container.setLayout(null);

        headLabel = new JLabel("Ваш профиль");
        nameLabel = new JLabel("Логин: " + employer.getName());
        cityLabel = new JLabel("Город: " + employer.getCity());
        cNameLabel = new JLabel("Название компании: " + employer.getCompanyName());

        headLabel.setFont(font20);
        nameLabel.setFont(font);
        cityLabel.setFont(font);
        cNameLabel.setFont(font);

        headLabel.setBounds(225, 30, 300, 30);
        nameLabel.setBounds(50, 50, 300, 30);
        cityLabel.setBounds(50, 80, 300, 30);
        cNameLabel.setBounds(50, 110, 300, 30);

        container.add(headLabel);
        container.add(nameLabel);
        container.add(cityLabel);
        container.add(cNameLabel);

        addVacancyButton = new JButton("Добавить вакансию");
        addVacancyButton.setFont(font);
        addVacancyButton.setBounds(50, 150, 200, 30);
        addVacancyButton.addActionListener(new AddVacancyButtonListener());
        container.add(addVacancyButton);


        aLabel = new JLabel("Ваши вакансии"); 
        
        RefreshVacancies.refreshVacancies(container, employer.getName());
    }

    class AddVacancyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            WindowsAddVacancy addVacancyWindow = new WindowsAddVacancy(employer);
            addVacancyWindow.setVisible(true);
            addVacancyWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    RefreshVacancies.refreshVacancies(getContentPane(), employer.getName());
                }
            });
        }
    }
}