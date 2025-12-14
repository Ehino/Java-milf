import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WindowsEmployer extends JFrame {

    JLabel nameLabel, passwordLabel, cityLabel, companyLabel, jobLabel, girlTypeLabel, requirementsLabel, statusLabel;
    JTextField nameField, passwordField, cityField, companyField, jobField, requirementsField;
    JComboBox<String> girlTypeCombo;
    JCheckBox statusCheckBox;
    JButton regButton;

    Font font = new Font("Arial", Font.ITALIC, 16);

    public WindowsEmployer() {
        super("Регистрация работодателя");
        super.setBounds(550, 250, 800, 600);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setLocationRelativeTo(null);

        Container container = super.getContentPane();
        container.setLayout(null);

        nameLabel = new JLabel("Введите логин:");
        nameField = new JTextField("");
        passwordLabel = new JLabel("Придумайте пароль:");
        passwordField = new JTextField("");
        cityLabel = new JLabel("Введите город:");
        cityField = new JTextField("");
        companyLabel = new JLabel("Название компании:");
        companyField = new JTextField("");
        jobLabel = new JLabel("Описание вакансии:");
        jobField = new JTextField("");
        girlTypeLabel = new JLabel("Тип девушки:");
        String[] girlTypes = {"Милфа", "Альтушка", "Любой"};
        girlTypeCombo = new JComboBox<>(girlTypes);
        requirementsLabel = new JLabel("Требования к девушке:");
        requirementsField = new JTextField("");
        statusLabel = new JLabel("Статус объявления:");
        statusCheckBox = new JCheckBox("Актуально");
        statusCheckBox.setSelected(true);

        nameLabel.setFont(font);
        passwordLabel.setFont(font);
        cityLabel.setFont(font);
        companyLabel.setFont(font);
        jobLabel.setFont(font);
        girlTypeLabel.setFont(font);
        requirementsLabel.setFont(font);
        statusLabel.setFont(font);
        statusCheckBox.setFont(font);

        nameLabel.setBounds(50, 50, 200, 30);
        nameField.setBounds(250, 50, 200, 30);
        passwordLabel.setBounds(50, 100, 200, 30);
        passwordField.setBounds(250, 100, 200, 30);
        cityLabel.setBounds(50, 150, 200, 30);
        cityField.setBounds(250, 150, 200, 30);
        companyLabel.setBounds(50, 200, 200, 30);
        companyField.setBounds(250, 200, 200, 30);
        jobLabel.setBounds(50, 250, 200, 30);
        jobField.setBounds(250, 250, 200, 30);
        girlTypeLabel.setBounds(50, 300, 200, 30);
        girlTypeCombo.setBounds(250, 300, 200, 30);
        requirementsLabel.setBounds(50, 350, 200, 30);
        requirementsField.setBounds(250, 350, 200, 30);
        statusLabel.setBounds(50, 400, 200, 30);
        statusCheckBox.setBounds(250, 400, 200, 30);

        container.add(nameLabel);
        container.add(nameField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(cityLabel);
        container.add(cityField);
        container.add(companyLabel);
        container.add(companyField);
        container.add(jobLabel);
        container.add(jobField);
        container.add(girlTypeLabel);
        container.add(girlTypeCombo);
        container.add(requirementsLabel);
        container.add(requirementsField);
        container.add(statusLabel);
        container.add(statusCheckBox);

        regButton = new JButton("Зарегистрироваться");
        regButton.setFont(font);
        regButton.setBounds(50, 450, 200, 30);
        container.add(regButton);

        regButton.addActionListener(new RegButtonEmployer());
    }

    class RegButtonEmployer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String password = passwordField.getText();
            String city = cityField.getText();
            String companyName = companyField.getText();
            String jobDescribe = jobField.getText();
            String girlType = (String) girlTypeCombo.getSelectedItem();
            String requirements = requirementsField.getText();
            boolean advertStatus = statusCheckBox.isSelected();

            if (name.isEmpty() || password.isEmpty() || city.isEmpty() ||
                    companyName.isEmpty() || jobDescribe.isEmpty() || requirements.isEmpty()) {
                JOptionPane.showMessageDialog(WindowsEmployer.this,
                        "Заполните все поля!",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            UserEmployer employer = new UserEmployer(
                    name, password, city, companyName,
                    jobDescribe, girlType, requirements, advertStatus
            );

            SaveEmployer.save(employer);

            JOptionPane.showMessageDialog(WindowsEmployer.this,
                    "Работодатель зарегистрирован!",
                    "Успех",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}