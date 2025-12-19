package windows;

import database.DatabaseHandler;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import models.UserEmployer;

public class WindowsLogin extends JFrame {

    JLabel loginLabel, passwordLabel;
    JTextField loginField;
    JPasswordField passwordField; 
    JButton enterButton, backButton;

    Font font = new Font("Arial", Font.ITALIC, 16);

    public WindowsLogin() {
        super("Milf&Altushka Hunter/Вход");
        super.setBounds(550, 250, 500, 300);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLocationRelativeTo(null);

        Container container = super.getContentPane();
        container.setLayout(null);

        loginLabel = new JLabel("Введите логин:");
        loginField = new JTextField();
        passwordLabel = new JLabel("Введите пароль:");
        passwordField = new JPasswordField(); 

        loginLabel.setFont(font);
        passwordLabel.setFont(font);

        loginLabel.setBounds(50, 50, 150, 30);
        loginField.setBounds(200, 50, 200, 30);

        passwordLabel.setBounds(50, 100, 150, 30);
        passwordField.setBounds(200, 100, 200, 30);

        enterButton = new JButton("Войти");
        backButton = new JButton("Назад");

        enterButton.setFont(font);
        backButton.setFont(font);

        enterButton.setBounds(250, 180, 150, 30);
        backButton.setBounds(50, 180, 150, 30);

        container.add(loginLabel);
        container.add(loginField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(enterButton);
        container.add(backButton);

        enterButton.addActionListener((ActionListener) new LoginAction());
        backButton.addActionListener((ActionListener) new BackAction());
    }


    class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword()); 

            if (login.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(WindowsLogin.this, "Введите логин и пароль!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DatabaseHandler dbHandler = new DatabaseHandler();
            String userRole = dbHandler.ResultUser(login, password);

            if (userRole.equals("NotFound")) {
                JOptionPane.showMessageDialog(WindowsLogin.this, 
                    "Неверный логин или пароль, либо такого пользователя не существует.", 
                    "Ошибка входа", JOptionPane.ERROR_MESSAGE);
            } else {
                if(userRole == "Altushka"){
                    dispose();   
                } 
                if(userRole == "Milfa"){
                    dispose();
                } 
                if(userRole.equals("Employer")){
                    UserEmployer employer = dbHandler.getInfoEmp(login);

                    dispose();
                    new MainEmp(employer).setVisible(true);
                } 
                    
            }
        }
    }
    

    class BackAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new FirstWindows().setVisible(true);
        }
    }
}