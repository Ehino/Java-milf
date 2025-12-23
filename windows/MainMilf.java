package windows;

import java.awt.*;
import javax.swing.*;
import models.Milfa;
import utils.RefreshVacancies;


public class MainMilf extends JFrame{

	JLabel nameLabel, cityLabel, ageLabel, cookingLabel, childrenLabel, husbandLabel, girlTypeLabel, aLabel;
	JLabel headLabel;
	JPanel dopPanel;


	Font font = new Font("Arial", Font.ITALIC, 16);
	Font font20 = new Font("Arial", Font.ITALIC, 20);

	public MainMilf(Milfa milfa){
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
		
		
		headLabel.setFont(font20);
		nameLabel.setFont(font);
		cityLabel.setFont(font);
		ageLabel.setFont(font);
		cookingLabel.setFont(font);
		childrenLabel.setFont(font);
        husbandLabel.setFont(font);
		girlTypeLabel.setFont(font);
		aLabel.setFont(font20);

		headLabel.setBounds(225, 30, 300, 30);
		nameLabel.setBounds(50, 50, 300, 30);
		cityLabel.setBounds(50, 80, 300, 30);
		ageLabel.setBounds(50, 110, 300, 30);
		cookingLabel.setBounds(50, 140, 300, 30);
		childrenLabel.setBounds(50, 170, 300, 30);
        husbandLabel.setBounds(50, 200, 300, 30);
		girlTypeLabel.setBounds(50, 230, 300, 30);
		aLabel.setBounds(200, 260, 300, 50);

		container.add(headLabel);
		container.add(nameLabel);
		container.add(cityLabel);
		container.add(ageLabel);
		container.add(cookingLabel);
		container.add(childrenLabel);
        container.add(husbandLabel);
		container.add(girlTypeLabel);
		container.add(aLabel);

		RefreshVacancies.refreshVacancies(container, "Милфа");

	}
}


