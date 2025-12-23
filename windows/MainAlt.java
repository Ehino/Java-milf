package windows;

import java.awt.*;
import javax.swing.*;
import models.Altushka;
import utils.RefreshVacancies;


public class MainAlt extends JFrame{

	JLabel nameLabel, cityLabel, ageLabel, cookingLabel, cBoyFrinedLabel, girlTypeLabel, aLabel;
	JLabel headLabel;
	JPanel dopPanel;


	Font font = new Font("Arial", Font.ITALIC, 16);
	Font font20 = new Font("Arial", Font.ITALIC, 20);

	public MainAlt(Altushka altushka){
		super("Altushka окно профиля");
		super.setBounds(550, 250, 550, 750);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLocationRelativeTo(null);

		Container container = super.getContentPane();
		container.setLayout(null);

		headLabel = new JLabel("Ваш профиль");
		nameLabel = new JLabel("Логин: " + altushka.getName());
		cityLabel = new JLabel("Город: " + altushka.getCity());
		ageLabel = new JLabel("Возраст: " + altushka.getAge());
		cookingLabel = new JLabel("Умение готовить: " + (altushka.isCooking() ? "Да" : "Нет"));
		cBoyFrinedLabel = new JLabel("Количество парней: " + altushka.getCBoyFriend());
		girlTypeLabel = new JLabel("Тип девушки: Альтушка");
		aLabel = new JLabel("Доступные анкеты");
		
		
		headLabel.setFont(font20);
		nameLabel.setFont(font);
		cityLabel.setFont(font);
		ageLabel.setFont(font);
		cookingLabel.setFont(font);
		cBoyFrinedLabel.setFont(font);
		girlTypeLabel.setFont(font);
		aLabel.setFont(font20);

		headLabel.setBounds(225, 30, 300, 30);
		nameLabel.setBounds(50, 50, 300, 30);
		cityLabel.setBounds(50, 80, 300, 30);
		ageLabel.setBounds(50, 110, 300, 30);
		cookingLabel.setBounds(50, 140, 300, 30);
		cBoyFrinedLabel.setBounds(50, 170, 300, 30);
		girlTypeLabel.setBounds(50, 200, 300, 30);
		aLabel.setBounds(200, 220, 300, 50);

		container.add(headLabel);
		container.add(nameLabel);
		container.add(cityLabel);
		container.add(ageLabel);
		container.add(cookingLabel);
		container.add(cBoyFrinedLabel);
		container.add(girlTypeLabel);
		container.add(aLabel);

		RefreshVacancies.refreshVacancies(container, "Альтушка");
	}
}

