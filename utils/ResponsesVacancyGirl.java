package utils;

import database.DBHandlerResponses;
import database.DBHandlerVacancy;
import database.DatabaseHandler;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ResponsesVacancyGirl{

    public static void ResponsesVacancyGirl(Container container, String userRole, String login) {
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

        JButton responsesButton;

        DatabaseHandler dbHandler = new DatabaseHandler();
        DBHandlerVacancy dbHandlerVacancy = new DBHandlerVacancy();
        DBHandlerResponses dbHandlerResponses = new DBHandlerResponses();

        List<String[]> allVacancies = new ArrayList<>();
        List<Integer> idResponsesVacancy;

        idResponsesVacancy = dbHandlerResponses.getIdVacancyResponses(login);

        for(Integer idvac : idResponsesVacancy){
            allVacancies.addAll(dbHandlerVacancy.getResponsesVacancies(idvac));
        }

        for (String[] vac : allVacancies) {
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
            

            boolean statusResponses = dbHandlerResponses.statusResponses(Integer.parseInt(vac[0]), login);

            if(statusResponses){
                responsesButton = new JButton("Отменить отклик");
                responsesButton.addActionListener(e ->{
                    try {
                        dbHandlerResponses.deleteResponses(Integer.parseInt(vac[0]), login);
                    } catch (ClassNotFoundException | SQLException ex) {
                        ex.printStackTrace();
                    }
                    ResponsesVacancyGirl(container,userRole, login);
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
                            ResponsesVacancyGirl(container,userRole, login);
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
            
            listPanel.add(card);
            listPanel.add(Box.createRigidArea(new Dimension(0, 10)));
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
