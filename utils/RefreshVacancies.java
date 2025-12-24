package utils;

import database.DBHandlerResponses;
import database.DBHandlerVacancy;
import database.DatabaseHandler;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class RefreshVacancies{

    public static void refreshVacancies(Container container, String userRole, String login, boolean buttonRes) {
        Component[] components = container.getComponents();
        for (Component comp : components) {
            if (comp instanceof JScrollPane) {
                container.remove(comp);
            }
        }
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        Font font = new Font("Arial", Font.ITALIC, 16);

        JButton buttonStatus, responsesButton, deleteButton;

        DatabaseHandler dbHandler = new DatabaseHandler();
        DBHandlerVacancy dbHandlerVacancy = new DBHandlerVacancy();
        DBHandlerResponses dbHandlerResponses = new DBHandlerResponses();

        List<String[]> allVacancies = new ArrayList<>();
        List<Integer> idResponsesVacancy = null;
        
        if(buttonRes){
            idResponsesVacancy = dbHandlerResponses.getIdVacancyResponses(login);

            for(Integer idvac : idResponsesVacancy){
                allVacancies.addAll(dbHandlerVacancy.getResponsesVacancies(idvac));
            }
        }else {
            if(userRole.equals("Milfa")){
            allVacancies = dbHandlerVacancy.getFilterVacancies("Милфа", true);
            } else if(userRole.equals("Altushka")){
                allVacancies = dbHandlerVacancy.getFilterVacancies("Альтушка", true);
            } else {
                allVacancies = dbHandlerVacancy.getEmployerVacancies(login);
            } 
        }


        
        
        if (allVacancies.isEmpty()) {
            JLabel noVacanciesLabel = new JLabel("Вакансии отсутствуют");
            noVacanciesLabel.setFont(font);
            noVacanciesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(Box.createVerticalGlue());
            listPanel.add(noVacanciesLabel);
        } else {
            for (String[] vac : allVacancies) {
                if (vac.length < 4) {
                    System.out.println("Некорректный формат данных вакансии: " + String.join(", ", vac));
                    continue;
                }
                JPanel card = new JPanel();
                card.setBorder(BorderFactory.createEtchedBorder());
                card.setLayout(new GridLayout(7, 1));
                Dimension cardDim = new Dimension(480, 220);
                card.setPreferredSize(cardDim);
                card.setMaximumSize(cardDim);
                card.setMinimumSize(cardDim);
                card.setMaximumSize(new Dimension(480, 220));
                card.setBackground(Color.WHITE);
                card.setAlignmentX(Component.CENTER_ALIGNMENT);
                
                JLabel idVacancyLabel = new JLabel(" Id: " + vac[0]);
                JLabel girlTypeLabel = new JLabel(" Кого ищем: " + vac[1]);  
                JLabel jobDescribeLabel = new JLabel(" Описание: " + vac[2]);   
                JLabel requirementsLabel = new JLabel(" Требования: " + vac[3]);  

                String status = vac[4];
                JLabel advertStatusLabel = new JLabel(" Статус: " + status);
                if (status.equals("Активна")) {
                    advertStatusLabel.setForeground(new Color(0, 150, 0)); 
                } else {
                    advertStatusLabel.setForeground(Color.RED); 
                }


                idVacancyLabel.setFont(font);
                girlTypeLabel.setFont(font);
                jobDescribeLabel.setFont(font);
                requirementsLabel.setFont(font);
                advertStatusLabel.setFont(font);

                card.add(idVacancyLabel);
                card.add(girlTypeLabel);
                card.add(jobDescribeLabel);
                card.add(requirementsLabel);
                card.add(advertStatusLabel);
                if(!userRole.equals("Milfa") && !userRole.equals("Altushka")){   
                    if(vac[4].equals("Активна")){
                        buttonStatus = new JButton("Отключить вакансию");
                        buttonStatus.addActionListener(e -> {
                            dbHandlerVacancy.updateVacancyStatus(Integer.parseInt(vac[0]), false);
                            refreshVacancies(container,"Работодатель", login, false);
                            
                        });
                        advertStatusLabel.setFont(font);
                        card.add(buttonStatus);
                    }else {
                        buttonStatus = new JButton("Включить вакансию");
                        buttonStatus.addActionListener(e -> {
                            dbHandlerVacancy.updateVacancyStatus(Integer.parseInt(vac[0]), true);
                            refreshVacancies(container,"Работодатель", login, false);
                        });
                        advertStatusLabel.setFont(font);
                        card.add(buttonStatus);
                    }
                    deleteButton = new JButton("Удалить вакансию");
                    deleteButton.addActionListener(e -> {
                        dbHandlerVacancy.deleteVacancy(Integer.parseInt(vac[0]), login);
                        refreshVacancies(container,"Работодатель", login, false);
                    });
                    deleteButton.setForeground(Color.RED); 
                    card.add(deleteButton);
                    
                    
                } else {
                    boolean statusResponses = dbHandlerResponses.statusResponses(Integer.parseInt(vac[0]), login);

                    if(statusResponses){
                        responsesButton = new JButton("Отменить отклик");
                        responsesButton.addActionListener(e ->{
                            try {
                                dbHandlerResponses.deleteResponses(Integer.parseInt(vac[0]), login);
                            } catch (ClassNotFoundException | SQLException ex) {
                                ex.printStackTrace();
                            }
                            refreshVacancies(container,userRole, login, false);
                        });
                        responsesButton.setForeground(Color.RED);
                        responsesButton.setFont(font);
                        card.add(responsesButton);
                    } else{
                        responsesButton = new JButton("Откликнуться");
                        responsesButton.addActionListener(e ->{
                            String telegramNickname = JOptionPane.showInputDialog(null, "Введите свой никнейм в Telegram:", "Подтверждение отклика", JOptionPane.QUESTION_MESSAGE);
                            if (telegramNickname != null && !telegramNickname.trim().isEmpty()){
                                try {
                                    dbHandlerResponses.addResponses(Integer.parseInt(vac[0]), login, userRole, telegramNickname, true);
                                    refreshVacancies(container,userRole, login, false);
                                } catch (ClassNotFoundException | SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }else if (telegramNickname != null) {
                                JOptionPane.showMessageDialog(null, "Пожалуйста, введите никнейм в Telegram", "Ошибка", JOptionPane.WARNING_MESSAGE);
                            }
                        });
                        responsesButton.setFont(font);
                        card.add(responsesButton);
                    }
                }
                listPanel.add(card);
                listPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            }
        }
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        if(buttonRes){
            scrollPane.setBounds(20,20,500,400);
        }else{
            scrollPane.setBounds(20, 310, 500, 340);
        }
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        container.add(scrollPane);
        container.revalidate();
        container.repaint();
    }
}
