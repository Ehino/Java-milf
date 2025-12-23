package utils;

import database.DatabaseHandler;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;

public class RefreshVacancies{

    public static void refreshVacancies(Container container, String userRole, String login) {
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

        JButton buttonStatus, responsesButton;
        DatabaseHandler dbHandler = new DatabaseHandler();
        List<String[]> allVacancies;

        if(userRole.equals("Милфа")){
            allVacancies = dbHandler.getFilterVacancies("Милфа", true);
        } else if(userRole.equals("Альтушка")){
            allVacancies = dbHandler.getFilterVacancies("Альтушка", true);
        } else {
            allVacancies = dbHandler.getEmployerVacancies(login);
        }

        
        if (allVacancies.isEmpty()) {
            JLabel noVacanciesLabel = new JLabel("Вакансии отсутствуют");
            noVacanciesLabel.setFont(font);
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
                card.setLayout(new GridLayout(6, 1));
                Dimension cardDim = new Dimension(480, 120);
                card.setPreferredSize(cardDim);
                card.setMaximumSize(cardDim);
                card.setMinimumSize(cardDim);
                card.setMaximumSize(new Dimension(500, 120));
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
                if(!userRole.equals("Милфа") && !userRole.equals("Альтушка")){   
                    if(vac[4].equals("Активна")){
                        buttonStatus = new JButton("Отключить вакансию");
                        buttonStatus.addActionListener(e -> {
                            dbHandler.updateVacancyStatus(Integer.parseInt(vac[0]), false);
                            refreshVacancies(container,"Работодатель", login);
                            
                        });
                        advertStatusLabel.setFont(font);
                        card.add(buttonStatus);
                    }else {
                        buttonStatus = new JButton("Включить вакансию");
                        buttonStatus.addActionListener(e -> {
                            dbHandler.updateVacancyStatus(Integer.parseInt(vac[0]), true);
                            refreshVacancies(container,"Работодатель", login);
                        });
                        advertStatusLabel.setFont(font);
                        card.add(buttonStatus);
                    }
                } else {
                    boolean statusResponses = dbHandler.statusResponses(Integer.parseInt(vac[0]), login);

                    if(statusResponses){
                        responsesButton = new JButton("Отменить отклик");
                        responsesButton.addActionListener(e ->{
                            try {
                                dbHandler.deleteResponses(Integer.parseInt(vac[0]), login);
                            } catch (ClassNotFoundException | SQLException e1) {
                                e1.printStackTrace();
                            }
                            refreshVacancies(container,userRole, login);
                        });
                        responsesButton.setFont(font);
                        card.add(responsesButton);
                    } else{
                        responsesButton = new JButton("Откликнуться");
                        responsesButton.addActionListener(e ->{
                            try {
                                dbHandler.addResponses(Integer.parseInt(vac[0]), login, userRole, true);
                                refreshVacancies(container,userRole, login);
                            } catch (ClassNotFoundException | SQLException e1) {
                                e1.printStackTrace();
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
        scrollPane.setBounds(20, 310, 500, 340);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        container.add(scrollPane);
        container.revalidate();
        container.repaint();
    }
}
