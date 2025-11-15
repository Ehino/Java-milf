import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WindowsGirls extends JFrame{

	JLabel nameLabel, cityLabel, passwordLabel, ageLabel, childrenLabel, husbandLabel, boyfriendLabel;
	JTextField nameField, cityField, ageField, passwordField;
	JCheckBox cookingbox;
	JRadioButton milfButton, altushkaButton;
	JTextField childrenField, husbandField, boyfriendField;
	JPanel dopPanel;
	JButton regButton;
	
    Font font = new Font("Arial", Font.ITALIC, 16);
    public WindowsGirls(){
        super("Регистрация DirtyGirls");
		super.setBounds(550, 250, 800,500);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLocationRelativeTo(null);

        Container container = super.getContentPane();
		container.setLayout(null);

		nameLabel = new JLabel("Введите свой логин:");
		nameField = new JTextField("");
		passwordLabel = new JLabel("Придумайте пароль:");
		passwordField = new JTextField("");
		cityLabel = new JLabel("Введите свой город:");
		cityField = new JTextField("");
		ageLabel = new JLabel("Введите свой возраст:");
		ageField = new JTextField();
		cookingbox = new JCheckBox("Умеете ли вы готовить?");

		nameLabel.setFont(font);
		cityLabel.setFont(font);
		ageLabel.setFont(font);
		cookingbox.setFont(font);
		passwordLabel.setFont(font);


		nameLabel.setBounds(50, 50, 200, 30);
		nameField.setBounds(250, 50, 200, 30);
		passwordLabel.setBounds(50, 100, 200, 30);
		passwordField.setBounds(250, 100, 200, 30);
		cityLabel.setBounds(50, 150, 200, 30);
		cityField.setBounds(250, 150, 200, 30);
		ageLabel.setBounds(50, 200, 200, 30);
		ageField.setBounds(250, 200, 200, 30);
		cookingbox.setBounds(50, 250, 250, 30);

		container.add(nameLabel);
		container.add(nameField);
		container.add(passwordLabel);
		container.add(passwordField);
		container.add(cityLabel);
		container.add(cityField);
		container.add(ageLabel);
		container.add(ageField);
		container.add(cookingbox);

		milfButton = new JRadioButton("Милфа");
		altushkaButton = new JRadioButton("Альтушка");

		milfButton.setFont(font);
		altushkaButton.setFont(font);

		milfButton.setBounds(500, 50, 200, 30);
		altushkaButton.setBounds(500, 100, 200, 30);

		milfButton.setSelected(true);

		container.add(milfButton);
		container.add(altushkaButton);

		ButtonGroup roleGroup = new ButtonGroup();
		roleGroup.add(milfButton);
		roleGroup.add(altushkaButton);

		dopPanel = new JPanel();
		dopPanel.setLayout(null);
		dopPanel.setBounds(50, 300, 500, 150);
		container.add(dopPanel);

		childrenLabel = new JLabel("Количество детей:");
		childrenField = new JTextField();
		husbandLabel = new JLabel("Количество мужей:");
		husbandField = new JTextField();
		boyfriendLabel = new JLabel("Количество парней:");
		boyfriendField = new JTextField();

		childrenLabel.setFont(font);
		husbandLabel.setFont(font);
		boyfriendLabel.setFont(font);

		childrenLabel.setBounds(0, 0, 150, 30);
        childrenField.setBounds(160, 0, 100, 30);
        husbandLabel.setBounds(0, 40, 150, 30);
        husbandField.setBounds(160, 40, 100, 30);
		boyfriendLabel.setBounds(0, 0, 200, 30);
        boyfriendField.setBounds(170, 0, 100, 30);

		RegButtonManager manager = new RegButtonManager();
        milfButton.addActionListener(manager);
        altushkaButton.addActionListener(manager);

		manager.updatePanel();

		regButton = new JButton("Зарегистрироваться");
		regButton.setFont(font);
		regButton.setBounds(500, 200, 200, 30);
		container.add(regButton);

		regButton.addActionListener((ActionListener) new RegButtonGirls());

    }	

	class RegButtonManager implements ActionListener{

		@Override
        public void actionPerformed(ActionEvent e) {
            updatePanel();
        }

        public void updatePanel() {
            dopPanel.removeAll();
            if (milfButton.isSelected()) {
                dopPanel.add(childrenLabel);
                dopPanel.add(childrenField);
                dopPanel.add(husbandLabel);
                dopPanel.add(husbandField);
            } else {
                dopPanel.add(boyfriendLabel);
                dopPanel.add(boyfriendField);
            }
            dopPanel.revalidate();
            dopPanel.repaint();
        }
		
	}

	class RegButtonGirls implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = nameField.getText();
			String city = cityField.getText();
			int age = Integer.parseInt(ageField.getText());
			boolean cooking = cookingbox.isSelected();
			String password = passwordField.getText();

			if (milfButton.isSelected()) {
				int children = Integer.parseInt(childrenField.getText());
				int husbands = Integer.parseInt(husbandField.getText());
				Milfa milf = new Milfa(name, password, city, age, cooking, children, husbands, false);
				SaveDitryGirls.save(milf);
			} else {
				int boyfriends = Integer.parseInt(boyfriendField.getText());
				Altushka alt = new Altushka(name, password, city, age, cooking, boyfriends);
				SaveDitryGirls.save(alt);
    }
		}
		
	}

}
