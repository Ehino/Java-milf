package windows;

import java.awt.*;
import javax.swing.*;
import models.UserEmployer;


public class MainEmp extends JFrame{

	JLabel nameLabel, cityLabel, cNameLabel, jDescribeLabel, girlTyprLabel, requirementsLabel, advertStatusLabel;
	JLabel headLabel, aLabel;
	JPanel dopPanel;

	Font font = new Font("Arial", Font.ITALIC, 16);
	Font font20 = new Font("Arial", Font.ITALIC, 20);

	public MainEmp(UserEmployer employer){
		super("Employer окно профиля");
		super.setBounds(900, 1500, 550, 800);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLocationRelativeTo(null);

		Container container = super.getContentPane();
		container.setLayout(null);

		headLabel = new JLabel("Ваш профиль");
		nameLabel = new JLabel("Логин: " + employer.getName());
		cityLabel = new JLabel("Город: " + employer.getCity());
		cNameLabel = new JLabel("Название компании: " + employer.getCompanyName());
		
		headLabel.setFont(font20);
		nameLabel.setFont(font);
		cityLabel.setFont(font);
		cNameLabel.setFont(font);

		headLabel.setBounds(225, 30, 300, 30);
		nameLabel.setBounds(50, 50, 300, 30);
		cityLabel.setBounds(50, 80, 300, 30);
		cNameLabel.setBounds(50, 110, 300, 30);

		container.add(headLabel);
		container.add(nameLabel);
		container.add(cityLabel);
		container.add(cNameLabel);

		aLabel = new JLabel("Ваши вакансии");
		jDescribeLabel = new JLabel("Описание вакансии: " + employer.getJobDescribe());
		girlTyprLabel = new JLabel("Требуемый тип девушки: " + employer.getGirlType());
		requirementsLabel = new JLabel("Требование к девушке " + employer.getRequirements());
		advertStatusLabel = new JLabel("Статус объявления: " + (employer.isAdvertStatus() ? "Актуально" : "Закрыто"));

		aLabel.setFont(font20);
		jDescribeLabel.setFont(font);
		girlTyprLabel.setFont(font);
		requirementsLabel.setFont(font);
		advertStatusLabel.setFont(font);

		dopPanel = new JPanel();
		dopPanel.setLayout(null);
		dopPanel.setBackground(new Color(220, 220, 220));
		dopPanel.setBounds(20, 190, 450, 150);
		container.add(dopPanel);
		

		aLabel.setBounds(215, 150, 300, 30);
		jDescribeLabel.setBounds(10, 10, 440, 30);
		girlTyprLabel.setBounds(10, 40, 440, 30);
		requirementsLabel.setBounds(10, 70, 440, 30);
		advertStatusLabel.setBounds(10, 100, 440, 30);

		container.add(aLabel);
		dopPanel.add(jDescribeLabel);
		dopPanel.add(girlTyprLabel);
		dopPanel.add(requirementsLabel);
		dopPanel.add(advertStatusLabel);
	}
}

