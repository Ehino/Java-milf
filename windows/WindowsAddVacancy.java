package windows;

import database.DatabaseHandler;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import models.UserEmployer;


public class WindowsAddVacancy extends JFrame {

    JLabel girlTypeLabel, jDescribeLabel, requirementsLabel;
    JTextArea jDescribeArea, requirementsArea;
    JButton addButton, backButton;
    UserEmployer employer;
    JComboBox<String> girlTypeCombo;

    Font font = new Font("Arial", Font.ITALIC, 16);

    public WindowsAddVacancy(UserEmployer employer) {
        super("Добавление вакансии");
        this.employer = employer;
        
        super.setBounds(550, 250, 600, 700);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setResizable(false);
        super.setLocationRelativeTo(null);

        Container container = super.getContentPane();
        container.setLayout(null);

        girlTypeLabel = new JLabel("Кого ищем: ");
        String[] girlTypes = {"Милфа", "Альтушка", "Любой"};
        girlTypeCombo = new JComboBox<>(girlTypes);

        jDescribeLabel = new JLabel("Описание вакансии:");
        jDescribeArea = new JTextArea();
        jDescribeArea.setLineWrap(true);
        JScrollPane descScroll = new JScrollPane(jDescribeArea);


        requirementsLabel = new JLabel("Требования:");
        requirementsArea = new JTextArea();
        requirementsArea.setLineWrap(true);
        JScrollPane reqScroll = new JScrollPane(requirementsArea);

        addButton = new JButton("Опубликовать");
        addButton.addActionListener(new Publication());

        girlTypeLabel.setFont(font);
        girlTypeCombo.setFont(font);
        jDescribeLabel.setFont(font);
        requirementsLabel.setFont(font);
        addButton.setFont(font);

        girlTypeLabel.setBounds(100, 100, 200, 30);
        girlTypeCombo.setBounds(300, 100, 200, 30);
        jDescribeLabel.setBounds(100, 150, 200, 30);
        descScroll.setBounds(100, 200, 400, 100);
        requirementsLabel.setBounds(100, 350, 200, 30);
        reqScroll.setBounds(100, 400, 400, 100);
        addButton.setBounds(100, 550, 400, 30);

        container.add(girlTypeLabel);
        container.add(girlTypeCombo);
        container.add(jDescribeLabel);
        container.add(descScroll);
        container.add(requirementsLabel);
        container.add(reqScroll);
        container.add(addButton);
    }

    class Publication implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String girlType = (String) girlTypeCombo.getSelectedItem();
            String jobDescribe = jDescribeArea.getText();
            String requirements = requirementsArea.getText();


            if(girlType.isEmpty() || jobDescribe.isEmpty() || requirements.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Заполните все поля!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DatabaseHandler dbHandler = new DatabaseHandler();

            dbHandler.addVacancy(employer.getName(), jobDescribe, girlType, requirements);

            JOptionPane.showMessageDialog(null, "Вакансия добавлена!");
            dispose();
        }
    }
}