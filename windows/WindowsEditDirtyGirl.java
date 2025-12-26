package windows;

import configs.Const;
import database.DBHandlerAltushka;
import database.DBHandlerMilfa;
import database.DatabaseHandler;
import java.awt.*;
import javax.swing.*;
import models.Altushka;
import models.DirtyGirls;
import models.Milfa;

public class WindowsEditDirtyGirl extends JFrame {
    private JLabel titleLabel, loginLabel, passwordLabel, cityLabel, ageLabel, cookingLabel, childrenLabel, husbandLabel, cBoyfrinedLabel;
    private JLabel passwordValueLabel, cityValueLabel, ageValueLabel, cookingValueLabel, childrenValueLabel, husbandValueLabel, cBoyfriendValueLabel;

    private JButton saveButton, backButton;
    private JButton passwordEditButton, cityEditButton, ageEditButton, cookingEditButton, childrenEditButton, husbandEditButton, cBoyfriendButton;

    private JPasswordField passwordField;
    private JTextField cityField, ageField, childrenField, husbandField, cBoyfriendField;
    private JComboBox<String> cookingBox;

    private DirtyGirls girl;
    private DatabaseHandler dbHandler;
    private DBHandlerMilfa dbHandlerMilfa;
    private DBHandlerAltushka dbHandlerAltushka;
    private String userRole;

    private final Font font = new Font("Arial", Font.PLAIN, 16);
    private final Font titleFont = new Font("Arial", Font.BOLD, 20);
    private final Font smallFont = new Font("Arial", Font.ITALIC, 12);

    public WindowsEditDirtyGirl(String login, String password) {
        super("Редактирование профиля");
        
        this.dbHandler = new DatabaseHandler();
        this.dbHandlerMilfa = new DBHandlerMilfa();
        this.dbHandlerAltushka = new DBHandlerAltushka();

        setBounds(550, 150, 700, 500); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(null);

        userRole = dbHandler.getUserRole(login, password);

        if(userRole.equals("Milfa")){
            girl = dbHandlerMilfa.getInfoMilf(login);
        } else {
            girl = dbHandlerAltushka.getInfoAlt(login);
        }


        titleLabel = new JLabel("Редактирование профиля");
        loginLabel = new JLabel("Логин: " + girl.getName());
        JLabel loginEditLabel = new JLabel("Редактирование запрещено");

        passwordLabel = new JLabel("Пароль:");
        passwordValueLabel = new JLabel(girl.getPassword());
        passwordField = new JPasswordField();
        passwordField.setVisible(false);

        cityLabel = new JLabel("Город:");
        cityValueLabel = new JLabel(girl.getCity());
        cityField = new JTextField(girl.getCity());
        cityField.setVisible(false);
        
        ageLabel = new JLabel("Возраст:");
        ageValueLabel = new JLabel(String.valueOf(girl.getAge()));
        ageField = new JTextField(String.valueOf(girl.getAge()));
        ageField.setVisible(false);

        cookingLabel = new JLabel("Готовка: ");
        String cookStatus = girl.isCooking() ? "Умеет" : "Не умеет";
        cookingValueLabel = new JLabel(cookStatus);
        cookingBox = new JComboBox<>(new String[]{"Умеет", "Не умеет"});
        cookingBox.setSelectedItem(cookStatus);
        cookingBox.setVisible(false);

        saveButton = new JButton("Сохранить изменения");
        backButton = new JButton("Назад");

        titleLabel.setFont(titleFont);
        loginLabel.setFont(font);
        loginEditLabel.setFont(smallFont);
        loginEditLabel.setForeground(Color.GRAY);

        passwordLabel.setFont(font);
        passwordValueLabel.setFont(font);
        passwordField.setFont(font);

        cityLabel.setFont(font);
        cityValueLabel.setFont(font);
        cityField.setFont(font);
        
        ageLabel.setFont(font);
        ageValueLabel.setFont(font);
        ageField.setFont(font);

        cookingBox.setFont(font);
        cookingLabel.setFont(font);
        cookingValueLabel.setFont(font);

        saveButton.setFont(font);
        backButton.setFont(font);

        titleLabel.setBounds(150, 20, 300, 30);
        loginLabel.setBounds(50, 70, 150, 30);
        loginEditLabel.setBounds(450, 70, 200, 30);

        passwordLabel.setBounds(50, 110, 150, 30);
        passwordValueLabel.setBounds(250, 110, 150, 30);
        passwordField.setBounds(250, 110, 150, 30);
        passwordEditButton = createEditButton(110);
        passwordEditButton.addActionListener(e -> toggleEditField(passwordValueLabel, passwordField, passwordEditButton));

        cityLabel.setBounds(50, 150, 150, 30);
        cityValueLabel.setBounds(250, 150, 150, 30);
        cityField.setBounds(250, 150, 150, 30);
        cityEditButton = createEditButton(150);
        cityEditButton.addActionListener(e -> toggleEditField(cityValueLabel, cityField, cityEditButton));
        
        ageLabel.setBounds(50, 190, 150, 30);
        ageValueLabel.setBounds(250, 190, 150, 30);
        ageField.setBounds(250, 190, 150, 30);
        ageEditButton = createEditButton(190);
        ageEditButton.addActionListener(e -> toggleEditField(ageValueLabel, ageField, ageEditButton));

        cookingBox.setBounds(250, 230, 150, 30);
        cookingLabel.setBounds(50, 230, 150, 30);
        cookingValueLabel.setBounds(250, 230, 150, 30);
        cookingEditButton = createEditButton(230);
        cookingEditButton.addActionListener(e -> {
             if (cookingBox.isVisible()) {
                cookingValueLabel.setVisible(true);
                cookingBox.setVisible(false);
                cookingEditButton.setText("Редактировать");
            } else {
                cookingValueLabel.setVisible(false);
                cookingBox.setVisible(true);
                cookingEditButton.setText("Отмена");
            }
        });

        saveButton.setBounds(355, 350, 250, 40);
        backButton.setBounds(50, 350, 250, 40);

        saveButton.addActionListener(e -> saveChanges(login, userRole));
        backButton.addActionListener(e -> backButtonLisner(login, userRole));

        container.add(titleLabel);
        container.add(loginLabel);
        container.add(loginEditLabel);
        
        container.add(passwordLabel);
        container.add(passwordValueLabel);
        container.add(passwordField);
        container.add(passwordEditButton);
        
        container.add(cityLabel);
        container.add(cityValueLabel);
        container.add(cityField);
        container.add(cityEditButton);
        
        container.add(ageLabel);
        container.add(ageValueLabel);
        container.add(ageField);
        container.add(ageEditButton);

        container.add(cookingLabel);
        container.add(cookingValueLabel);
        container.add(cookingBox);
        container.add(cookingEditButton);
        
        container.add(saveButton);
        container.add(backButton);
        

        if (userRole.equals("Milfa")) {
            Milfa milfa = dbHandlerMilfa.getInfoMilf(login);

            childrenLabel = new JLabel("Количество детей: ");
            childrenValueLabel = new JLabel(String.valueOf(milfa.getChildren()));
            childrenField = new JTextField(String.valueOf(milfa.getChildren()));
            childrenField.setVisible(false);

            husbandLabel = new JLabel("Количество мужей: ");
            husbandValueLabel = new JLabel(String.valueOf(milfa.getHusband()));
            husbandField = new JTextField(String.valueOf(milfa.getHusband()));
            husbandField.setVisible(false);

            childrenLabel.setFont(font);
            childrenValueLabel.setFont(font);
            childrenField.setFont(font);
            
            husbandLabel.setFont(font);
            husbandValueLabel.setFont(font);
            husbandField.setFont(font);

            childrenLabel.setBounds(50, 270, 300, 30);
            childrenValueLabel.setBounds(250, 270, 150, 30);
            childrenField.setBounds(250, 270, 150, 30);
            childrenEditButton = createEditButton(270);
            childrenEditButton.addActionListener(e -> toggleEditField(childrenValueLabel, childrenField, childrenEditButton));


            
            husbandLabel.setBounds(50, 310, 300, 30);
            husbandValueLabel.setBounds(250, 310, 150, 30);
            husbandField.setBounds(250, 310, 150, 30);
            husbandEditButton = createEditButton(310);
            husbandEditButton.addActionListener(e -> toggleEditField(husbandValueLabel, husbandField, husbandEditButton));

            
            container.add(childrenLabel);
            container.add(childrenValueLabel);
            container.add(childrenField);
            container.add(childrenEditButton);
            
            container.add(husbandLabel);
            container.add(husbandValueLabel);
            container.add(husbandField);
            container.add(husbandEditButton);


        } else {
            Altushka altushka = dbHandlerAltushka.getInfoAlt(login);
            
            cBoyfrinedLabel = new JLabel("Парни:");
            cBoyfriendValueLabel = new JLabel(String.valueOf(altushka.getCBoyFriend()));
            cBoyfriendField = new JTextField(String.valueOf(altushka.getCBoyFriend()));
            cBoyfriendField.setVisible(false);
            
            cBoyfrinedLabel.setFont(font);
            cBoyfriendValueLabel.setFont(font);
            cBoyfriendField.setFont(font);

            cBoyfrinedLabel.setBounds(50, 270, 300, 30);
            cBoyfriendValueLabel.setBounds(250, 270, 150, 30);
            cBoyfriendField.setBounds(250, 270, 150, 30);
            cBoyfriendButton = createEditButton(270);
            cBoyfriendButton.addActionListener(e -> toggleEditField(cBoyfriendValueLabel, cBoyfriendField, cBoyfriendButton));

            container.add(cBoyfrinedLabel);
            container.add(cBoyfriendValueLabel);
            container.add(cBoyfriendField);
            container.add(cBoyfriendButton);

        }

        container.add(saveButton);
        container.add(backButton);
    }

    private JButton createEditButton(int y) {
        JButton button = new JButton("Редактировать");
        button.setFont(smallFont);
        button.setBounds(450, y, 150, 30);
        return button;
    }

    private void toggleEditField(JLabel label, JTextField field, JButton button) {
        label.setVisible(false);
        field.setVisible(true);
        field.setText(label.getText());
        button.setText("Отмена");

        button.removeActionListener(button.getActionListeners()[0]);
        button.addActionListener(e -> {
            label.setVisible(true);
            field.setVisible(false);
            button.setText("Редактировать");
            button.removeActionListener(button.getActionListeners()[0]);
            button.addActionListener(ev -> toggleEditField(label, field, button));
        });
    }

    private void saveChanges(String login, String userRole) {      
        if (passwordField.isVisible() && !new String(passwordField.getPassword()).isEmpty()) {
            dbHandler.updateDirtyCirls(Const.MILF_PASSWORD, new String(passwordField.getPassword()), login, userRole);
        }

        if (cityField.isVisible() && !cityField.getText().equals(cityValueLabel.getText())){
            dbHandler.updateDirtyCirls(Const.MILF_CITY, cityField.getText(), login, userRole);
        }
        
        
        if (ageField.isVisible() && !ageField.getText().equals(ageValueLabel.getText())) {
            try {
                Integer.parseInt(ageField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Возраст должен быть числом!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            dbHandler.updateDirtyCirls(Const.MILF_AGE, ageField.getText(), login, userRole);
        }
        
        if (cookingBox.isVisible() && !cookingBox.getSelectedItem().equals(cookingValueLabel.getText())) {
            dbHandler.updateDirtyCirls(Const.MILF_COOKING, (String) cookingBox.getSelectedItem(), login, userRole);
        }
        
        if(userRole.equals("Milfa")){
            if (childrenField.isVisible() && !childrenField.getText().equals(childrenValueLabel.getText())) {
                try {
                    Integer.parseInt(childrenField.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Количество детей должно быть числом!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dbHandler.updateDirtyCirls(Const.MILF_CHILDREN, childrenField.getText(), login, userRole);
            }
            
            
            if (husbandField.isVisible() && !husbandField.getText().equals(husbandValueLabel.getText()) && userRole.equals("Milfa")) {
                try {
                    Integer.parseInt(husbandField.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Количество мужей должно быть числом!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dbHandler.updateDirtyCirls(Const.MILF_HUSBAND, husbandField.getText(), login, userRole);
            }
        } else {
            if (cBoyfriendField.isVisible() && !cBoyfriendField.getText().equals(cBoyfriendValueLabel.getText()) && userRole.equals("Altushka")) {
                try {
                    Integer.parseInt(childrenField.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Количество парней должно быть числом!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dbHandler.updateDirtyCirls(Const.ALT_CBOYFRIEND, cBoyfriendField.getText(), login, userRole);
            }
        }
        
        JOptionPane.showMessageDialog(this, "Изменения сохранены успешно!", "Успех", JOptionPane.INFORMATION_MESSAGE);

        dispose();
        
        DBHandlerMilfa dbHandlerMilfa = new DBHandlerMilfa();
        DBHandlerAltushka dbHandlerAltushka = new DBHandlerAltushka();

        if(userRole.equals("Milfa")){
            Milfa milfa = dbHandlerMilfa.getInfoMilf(login);
			new WindowsMainMilf(milfa).setVisible(true);
        } else {
            Altushka altushka = dbHandlerAltushka.getInfoAlt(login);            
			new WindowsMainAlt(altushka).setVisible(true);
        }
    }

    private void backButtonLisner(String login, String userRole){
        dispose();
        
        DBHandlerMilfa dbHandlerMilfa = new DBHandlerMilfa();
        DBHandlerAltushka dbHandlerAltushka = new DBHandlerAltushka();

        if(userRole.equals("Milfa")){
            Milfa milfa = dbHandlerMilfa.getInfoMilf(login);
			new WindowsMainMilf(milfa).setVisible(true);
        } else {
            Altushka altushka = dbHandlerAltushka.getInfoAlt(login);            
			new WindowsMainAlt(altushka).setVisible(true);
        }
    }
}