package windows;

import database.DBHandlerAltushka;
import database.DBHandlerMilfa;
import database.DatabaseHandler;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import models.Altushka;
import models.Milfa;
import utils.SaveUsers;

public class WindowsGirls extends JFrame{

	JLabel nameLabel, cityLabel, passwordLabel, ageLabel, childrenLabel, husbandLabel, boyfriendLabel;
	JPasswordField passwordField;
	JTextField nameField, cityField, ageField;
	JCheckBox cookingbox;
	JRadioButton milfButton, altushkaButton;
	JTextField childrenField, husbandField, boyfriendField;
	JPanel dopPanel;
	JButton regButton, backButton;
	DatabaseHandler dbHandler = new DatabaseHandler();
	DBHandlerMilfa dbHandlerMilfa = new DBHandlerMilfa();
	DBHandlerAltushka dbHandlerAltushka = new DBHandlerAltushka();
	
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
		passwordField = new JPasswordField("");
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
		backButton = new JButton("Назад");

		regButton.setFont(font);
		backButton.setFont(font);

		regButton.setBounds(500, 200, 200, 30);
		backButton.setBounds(500, 250, 200, 30);

		container.add(regButton);
		container.add(backButton);

		regButton.addActionListener((ActionListener) new RegButtonGirls());
		backButton.addActionListener((ActionListener) new BackAction());

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
			String password = new String(passwordField.getPassword());
			String city = cityField.getText();
			String ageText = ageField.getText();
			boolean cooking = cookingbox.isSelected();

			if(name.isEmpty() || password.isEmpty() || city.isEmpty() || ageText.isEmpty()){
				JOptionPane.showMessageDialog(WindowsGirls.this, "Заполните все поля!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
			}else if (!isNum(ageText)) {
				JOptionPane.showMessageDialog(null, "В поле \"Возраст\" должно быть число!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
				return;
			}	

			int age = Integer.parseInt(ageField.getText());

			if(dbHandler.isNameUser(name)){
				JOptionPane.showMessageDialog(WindowsGirls.this, "Логин занят, придумайте другой", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
			}else {
				if (milfButton.isSelected()) {
					String childrenText = childrenField.getText();
					String husbandsText = childrenField.getText();

					if(childrenText.isEmpty() && childrenText.isEmpty()){
						JOptionPane.showMessageDialog(WindowsGirls.this, "Поля \"Количество детей\" и \"Количество мужей\" должны быть заполнены", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
					}else if(!isNum(childrenText) && !isNum(husbandsText)){
						JOptionPane.showMessageDialog(null, "В полях \"Количество детей\" и \"Количество мужей\" должно быть число!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
						return;
					}

					int children = Integer.parseInt(childrenField.getText());
					int husbands = Integer.parseInt(husbandField.getText());

					
					Milfa milf = new Milfa(name, password, city, age, cooking, children, husbands);
					SaveUsers.saveDitryGirls(milf);
					try {
						
						dbHandlerMilfa.addMilf(name, password, city, age, cooking, children, husbands);

					} catch (ClassNotFoundException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, 
							"Драйвер базы данных не найден!", 
							"Ошибка драйвера", JOptionPane.ERROR_MESSAGE);
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, 
							"Ошибка SQL:\n" + ex.getMessage(), 
							"Ошибка базы данных", JOptionPane.ERROR_MESSAGE);
					}

					dispose();
					new WindowsFirst().setVisible(true);
				} else {
					String boyfriendsText = boyfriendField.getText();

					if(boyfriendsText.isEmpty()){
						JOptionPane.showMessageDialog(WindowsGirls.this, "Поле \"Количество парней\" должно быть заполнено", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
					}else if(!isNum(boyfriendsText)){
						JOptionPane.showMessageDialog(null, "В поле \"Количество парней\" должно быть число!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
						return;
					}

					int boyfriends = Integer.parseInt(boyfriendField.getText());

					Altushka alt = new Altushka(name, password, city, age, cooking, boyfriends);
					SaveUsers.saveDitryGirls(alt);
					try {
						
						dbHandlerAltushka.addAlt(name, password, city, age, cooking, boyfriends);

					} catch (ClassNotFoundException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, 
							"Драйвер базы данных не найден!", 
							"Ошибка драйвера", JOptionPane.ERROR_MESSAGE);
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, 
							"Ошибка SQL:\n" + ex.getMessage(), 
							"Ошибка базы данных", JOptionPane.ERROR_MESSAGE);
					}

					dispose();
					new WindowsFirst().setVisible(true);
				}	
			}
		}	
	}

	class BackAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new WindowsReg().setVisible(true);
		}
    }

	private boolean isNum (String str) {
		if (str == null || str.isEmpty()) return false;
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}



