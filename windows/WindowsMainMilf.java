package windows;

import database.DatabaseHandler;
import java.awt.*;
import javax.swing.*;
import models.Milfa;
import utils.RefreshVacancies;


public class WindowsMainMilf extends JFrame{

	private JLabel nameLabel, cityLabel, ageLabel, cookingLabel, childrenLabel, husbandLabel, girlTypeLabel, aLabel;
	private JLabel headLabel;

	private JButton editProfileButton, responsesButton;

	private final Font font = new Font("Arial", Font.ITALIC, 16);
	private final Font font20 = new Font("Arial", Font.ITALIC, 20);

	private final DatabaseHandler dbHandler = new DatabaseHandler();

	public WindowsMainMilf(Milfa milfa){
		super("Milf окно профиля");
		super.setBounds(550, 250, 550, 750);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLocationRelativeTo(null);

		Container container = super.getContentPane();
		container.setLayout(null);

		headLabel = new JLabel("Ваш профиль");
		nameLabel = new JLabel("Логин: " + milfa.getName());
		cityLabel = new JLabel("Город: " + milfa.getCity());
		ageLabel = new JLabel("Возраст: " + milfa.getAge());
		cookingLabel = new JLabel("Умение готовить: " + (milfa.isCooking() ? "Да" : "Нет"));
		childrenLabel = new JLabel("Количество детей: " + milfa.getChildren());
        husbandLabel = new JLabel("Количество мужей: " + milfa.getHusband());
		girlTypeLabel = new JLabel("Тип девушки: Милфа");
		aLabel = new JLabel("Доступные анкеты");
		editProfileButton = new JButton("Редактировать профиль");
		responsesButton = new JButton("Посмотреть отклики");
		
		
		headLabel.setFont(font20);
		nameLabel.setFont(font);
		cityLabel.setFont(font);
		ageLabel.setFont(font);
		cookingLabel.setFont(font);
		childrenLabel.setFont(font);
        husbandLabel.setFont(font);
		girlTypeLabel.setFont(font);
		aLabel.setFont(font20);

		editProfileButton.setFont(font);
		responsesButton.setFont(font);
		editProfileButton.addActionListener(e ->{
			dispose();
			WindowsEditDirtyGirl windowsEditDirtyGirl = new WindowsEditDirtyGirl(milfa.getName(), milfa.getPassword());
			windowsEditDirtyGirl.setVisible(true);
		});
		responsesButton.addActionListener(e ->{
			WindowsResponsesVacancy windowsResponsesVacancy = new WindowsResponsesVacancy(milfa.getName(), milfa.getPassword());
			windowsResponsesVacancy.setVisible(true);
		});

		headLabel.setBounds(225, 10, 300, 30);
		nameLabel.setBounds(50, 50, 300, 30);
		cityLabel.setBounds(50, 80, 300, 30);
		ageLabel.setBounds(50, 110, 300, 30);
		cookingLabel.setBounds(50, 140, 300, 30);
		childrenLabel.setBounds(50, 170, 300, 30);
        husbandLabel.setBounds(50, 200, 300, 30);
		girlTypeLabel.setBounds(50, 230, 300, 30);
		aLabel.setBounds(50, 260, 250, 50);

		editProfileButton.setBounds(250, 50, 250, 30);
		responsesButton.setBounds(250, 270, 250, 30);

		container.add(headLabel);
		container.add(nameLabel);
		container.add(cityLabel);
		container.add(ageLabel);
		container.add(cookingLabel);
		container.add(childrenLabel);
        container.add(husbandLabel);
		container.add(girlTypeLabel);
		container.add(aLabel);
		container.add(editProfileButton);
		container.add(responsesButton);

		RefreshVacancies.refreshVacancies(container, dbHandler.getUserRole(milfa.getName(), milfa.getPassword()), milfa.getName(), false);

	}
}


