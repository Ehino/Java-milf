package windows;

import database.DatabaseHandler;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import models.UserEmployer;
import utils.RefreshVacancies;

public class WindowsMainEmp extends JFrame {

    JLabel nameLabel, cityLabel, cNameLabel;
    JLabel headLabel, aLabel;
    JButton addVacancyButton, editProfileButton, responsesButton;

    Font font = new Font("Arial", Font.ITALIC, 16);
    Font font18 = new Font("Arial", Font.ITALIC, 18);
    Font font20 = new Font("Arial", Font.ITALIC, 20);

    DatabaseHandler dbHandler = new DatabaseHandler();

    UserEmployer employer;

    public WindowsMainEmp(UserEmployer employer) {
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
        nameLabel.setFont(font18);
        cityLabel.setFont(font18);
        cNameLabel.setFont(font18);

        headLabel.setBounds(225, 30, 300, 30);
        nameLabel.setBounds(50, 80, 300, 30);
        cityLabel.setBounds(50, 110, 300, 30);
        cNameLabel.setBounds(50, 140, 300, 30);

        container.add(headLabel);
        container.add(nameLabel);
        container.add(cityLabel);
        container.add(cNameLabel);

        addVacancyButton = new JButton("Добавить вакансию");
        editProfileButton = new JButton("Редактировать профиль");
        responsesButton = new JButton("Посмотреть отклики");

        addVacancyButton.setFont(font);
        editProfileButton.setFont(font);
        responsesButton.setFont(font);
        

        addVacancyButton.setBounds(50, 180, 200, 30);
        editProfileButton.setBounds(270, 80, 270, 30);
        responsesButton.setBounds(50, 220, 270, 30);
        
        addVacancyButton.addActionListener(new addVacancyButtonListener());
        editProfileButton.addActionListener(new editProfileButtonLisner());

        container.add(addVacancyButton);
        container.add(editProfileButton);
        container.add(responsesButton);


        aLabel = new JLabel("Ваши вакансии");
        aLabel.setFont(font20);
        aLabel.setBounds(190, 260, 250, 30); 
        container.add(aLabel);
        
        RefreshVacancies.refreshVacancies(container, dbHandler.getUserRole(employer.getName(), employer.getPassword()),employer.getName(), false);
    }

    class addVacancyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            WindowsAddVacancy addVacancyWindow = new WindowsAddVacancy(employer);
            addVacancyWindow.setVisible(true);
            addVacancyWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    RefreshVacancies.refreshVacancies(getContentPane(), dbHandler.getUserRole(employer.getName(), employer.getPassword()), employer.getName(), false);
                }
            });
        }
    }

    class editProfileButtonLisner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new WindowsEditEmployer(employer).setVisible(true);
        }
    }
}