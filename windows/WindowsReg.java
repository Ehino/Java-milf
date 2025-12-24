package windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WindowsReg extends JFrame {

	JRadioButton employerButton, workerButton;
	JCheckBox checkBox;
	JButton regButton, backButton;
	Font font = new Font("Arial", Font.ITALIC, 16);
	
	public WindowsReg(){
		super("Milf&Altushka Hunter/Регистрация");
		super.setBounds(550, 250, 800,500);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLocationRelativeTo(null);

		Container container = super.getContentPane();
		container.setLayout(null);

		employerButton = new JRadioButton("Работодатель");
		workerButton = new JRadioButton("Работник");
		checkBox = new JCheckBox("Согласие на обработку персональных данных", false);
		regButton = new JButton("Зарегистрироваться");
		backButton = new JButton("Назад");

		employerButton.setFont(font);
		workerButton.setFont(font);
		checkBox.setFont(font);
		regButton.setFont(font);
		backButton.setFont(font);
		

		employerButton.setBounds(150, 150, 200, 30);
		workerButton.setBounds(450, 150, 200, 30);
		checkBox.setBounds(100, 250, 500, 30);
		regButton.setBounds(350,350,300,30);
		backButton.setBounds(100,350,200,30);


		employerButton.setSelected(true);

		container.add(employerButton);
		container.add(workerButton);
		container.add(checkBox);
		container.add(regButton);
		container.add(backButton);

		ButtonGroup roleGroup = new ButtonGroup();
		roleGroup.add(employerButton);
		roleGroup.add(workerButton);

		regButton.addActionListener((ActionListener) new RegButtonManager());
		backButton.addActionListener(new BackAction());
	}

	class RegButtonManager implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
			Boolean check = checkBox.isSelected();

			if(employerButton.isSelected()){
				dispose();
                new WindowsEmployer().setVisible(true);
			} else if(workerButton.isSelected()){
				dispose();
				new WindowsGirls().setVisible(true);
			}
			
        }

	}

	class BackAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new WindowsFirst().setVisible(true);
        }
    }
}
