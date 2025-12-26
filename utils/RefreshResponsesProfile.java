package utils;

import database.DBHandlerAltushka;
import database.DBHandlerMilfa;
import database.DBHandlerResponses;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import models.Altushka;
import models.DirtyGirls;
import models.Milfa;



public class RefreshResponsesProfile {
    
    public static void refreshResponsesProfile(Container container, int idVacancy){
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

        DBHandlerResponses dbHandlerResponses = new DBHandlerResponses();
        DBHandlerMilfa dbHandlerMilfa = new DBHandlerMilfa();
        DBHandlerAltushka dbHandlerAltushka = new DBHandlerAltushka();

        List<String[]> responsesProfile = new ArrayList<>();
        

        responsesProfile = dbHandlerResponses.responsesVacancy(idVacancy);


        if (responsesProfile.isEmpty()) {
            JLabel noVacanciesLabel = new JLabel("Откликов нет");
            noVacanciesLabel.setFont(font);
            noVacanciesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(Box.createVerticalGlue());
            listPanel.add(noVacanciesLabel);
        } else{
            for (String[] prof : responsesProfile){
                JPanel card = new JPanel();
                card.setBorder(BorderFactory.createEtchedBorder());
                card.setLayout(null);
                card.setPreferredSize(new Dimension(480, 240));
                card.setMinimumSize(new Dimension(480, 220));
                card.setMaximumSize(new Dimension(480, 260));
                card.setBackground(Color.WHITE);
                card.setAlignmentX(Component.CENTER_ALIGNMENT);

                DirtyGirls girl;

                if(prof[2].equals("Milfa")){
                    girl = dbHandlerMilfa.getInfoMilf(prof[1]);
                }else {
                    girl = dbHandlerAltushka.getInfoAlt(prof[1]);
                }

                JLabel girlLogin = new JLabel("Имя девушки: " + girl.getName());
                JLabel girltelegramm = new JLabel("Тг: " + prof[4]);
                JLabel girlCity = new JLabel("Город: " + girl.getCity());
                JLabel girlAge = new JLabel("Возраст: " + girl.getAge());
                JLabel girlCooking = new JLabel("Умение готовить: " + (girl.isCooking()? "Умеет" : "Не умеет"));

                
                girlLogin.setFont(font);
                girltelegramm.setFont(font);
                girlCity.setFont(font);
                girlAge.setFont(font);
                girlCooking.setFont(font);
            
                girlLogin.setBounds(20, 20, 300, 30);
                girltelegramm.setBounds(20, 50, 300, 30);
                girlCity.setBounds(20, 80, 300, 30);
                girlAge.setBounds(20, 110, 300, 30);
                girlCooking.setBounds(20, 140, 300, 30);
                
                card.add(girlLogin);
                card.add(girltelegramm);
                card.add(girlCity);
                card.add(girlAge);
                card.add(girlCooking);
            
                if(prof[2].equals("Milfa")){
                    Milfa milfa = dbHandlerMilfa.getInfoMilf(prof[1]);
                
                    JLabel milfaChildren = new JLabel("Количество детей: " + milfa.getChildren());
                    JLabel milfaHusband = new JLabel("Количество мужей: " + milfa.getHusband());
                    
                    milfaChildren.setFont(font);
                    milfaHusband.setFont(font);

                    milfaChildren.setBounds(20, 170, 300, 30);
                    milfaHusband.setBounds(20, 200, 300, 30);

                    card.add(milfaChildren);
                    card.add(milfaHusband);
                } else {

                    Altushka altushka =  dbHandlerAltushka.getInfoAlt(prof[1]);
                    JLabel altushkaCBoyfrined = new JLabel("Количество парней: " + altushka.getCBoyFriend());
                    altushkaCBoyfrined.setFont(font);
                    altushkaCBoyfrined.setBounds(20, 170, 300, 30);
                    card.add(altushkaCBoyfrined);

                }

                JButton responsesButton = new JButton("Отклонить");
                responsesButton.addActionListener(e ->{
                    try {
                        dbHandlerResponses.deleteResponses(idVacancy, prof[1]);
                    } catch (ClassNotFoundException | SQLException ex) {
                        ex.printStackTrace();
                    }
                    refreshResponsesProfile(container, idVacancy);
                });
                responsesButton.setForeground(Color.RED);
                responsesButton.setFont(font);
                responsesButton.setBounds(250, 20, 200, 30);
                card.add(responsesButton);

                listPanel.add(card);
                listPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                
            }
        
        } 
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(20, 60, 500, 340);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        container.add(scrollPane);
        container.revalidate();
        container.repaint();
    }
}
