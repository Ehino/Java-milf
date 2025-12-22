package windows;

import database.DatabaseHandler;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import models.UserEmployer;

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
        
        refreshVacancies(container, employer.getName());
    }

    class AddVacancyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            WindowsAddVacancy addVacancyWindow = new WindowsAddVacancy(employer);
            addVacancyWindow.setVisible(true);
            addVacancyWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    refreshVacancies(getContentPane(), employer.getName());
                }
            });
        }
    }

    private void refreshVacancies(Container container, String vacancyLogin) {
        Component[] components = container.getComponents();
        for (Component comp : components) {
            if (comp instanceof JScrollPane) {
                container.remove(comp);
            }
        }
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        DatabaseHandler dbHandler = new DatabaseHandler();
        List<String[]> allVacancies = dbHandler.getEmployerVacancies(vacancyLogin);
        if (allVacancies.isEmpty()) {
            JLabel noVacanciesLabel = new JLabel("У вас пока нет вакансий");
            noVacanciesLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            noVacanciesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(Box.createVerticalGlue());
            listPanel.add(noVacanciesLabel);
            listPanel.add(Box.createVerticalGlue());
        } else {
            for (String[] vac : allVacancies) {
                if (vac.length < 4) {
                    System.out.println("Некорректный формат данных вакансии: " + String.join(", ", vac));
                    continue;
                }
                JPanel card = new JPanel();
                card.setBorder(BorderFactory.createEtchedBorder());
                card.setLayout(new GridLayout(4, 1));
                Dimension cardDim = new Dimension(480, 120);
                card.setPreferredSize(cardDim);
                card.setMaximumSize(cardDim);
                card.setMinimumSize(cardDim);
                card.setMaximumSize(new Dimension(500, 120));
                card.setBackground(Color.WHITE);
                card.setAlignmentX(Component.CENTER_ALIGNMENT);
                
                JLabel girlTypeLabel = new JLabel(" Кого ищем: " + vac[0]);  
                JLabel jobDescribeLabel = new JLabel(" Описание: " + vac[1]);   
                JLabel requirementsLabel = new JLabel(" Требования: " + vac[2]);  

                String status = vac[3];
                JLabel advertStatusLabel = new JLabel(" Статус: " + status);
                if (status.equalsIgnoreCase("Активна")) {
                    advertStatusLabel.setForeground(new Color(0, 150, 0)); 
                } else {
                    advertStatusLabel.setForeground(Color.RED); 
                }
                
                advertStatusLabel.setFont(new Font("Arial", Font.BOLD, 14));
                card.add(girlTypeLabel);
                card.add(jobDescribeLabel);
                card.add(requirementsLabel);
                card.add(advertStatusLabel);
                listPanel.add(card);
                listPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(20, 310, 500, 340);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        container.add(scrollPane);
        container.revalidate();
        container.repaint();
    }
}