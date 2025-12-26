package windows;

import database.DBHandlerResponses;
import database.DBHandlerVacancy;
import database.DatabaseHandler;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import models.UserEmployer;
import utils.RefreshVacancies;

public class WindowsMainEmp extends JFrame {

    private JLabel nameLabel, cityLabel, cNameLabel;
    private JLabel headLabel, aLabel;
    private JButton addVacancyButton, editProfileButton, responsesButton;

    private final Font font = new Font("Arial", Font.ITALIC, 16);
    private final Font font18 = new Font("Arial", Font.ITALIC, 18);
    private final Font font20 = new Font("Arial", Font.ITALIC, 20);

    DatabaseHandler dbHandler = new DatabaseHandler();
    DBHandlerResponses dbHandlerResponses = new DBHandlerResponses();
    DBHandlerVacancy dbHandlerVacancy = new DBHandlerVacancy();

    UserEmployer employer;

    List<String[]> activeVacancies = new ArrayList<>();
    List<String[]> allVacancies = new ArrayList<>();

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

        
        activeVacancies = dbHandlerResponses.getIdActiveVacancyResponses(true);

        for(String[] actvac : activeVacancies){
            allVacancies.addAll(dbHandlerVacancy.getFilterEmployerResponsesVacancies(Integer.parseInt(actvac[0]), employer.getName()));
        }

        int sizeVacancies = allVacancies.size();
        responsesButton = new JButton("Посмотреть отклики " + sizeVacancies);

        addVacancyButton.setFont(font);
        editProfileButton.setFont(font);
        responsesButton.setFont(font);
        

        addVacancyButton.setBounds(50, 180, 200, 30);
        editProfileButton.setBounds(270, 80, 270, 30);
        responsesButton.setBounds(50, 220, 270, 30);
        
        addVacancyButton.addActionListener(new addVacancyButtonListener());
        editProfileButton.addActionListener(new editProfileButtonLisner());
        responsesButton.addActionListener(e ->{
            dispose();
            new WindowsResponsesVacancy(employer.getName(), employer.getPassword()).setVisible(true);
        });

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
            dispose();
            WindowsAddVacancy addVacancyWindow = new WindowsAddVacancy(employer);
            addVacancyWindow.setVisible(true);
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