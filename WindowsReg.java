import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WindowsReg extends JFrame {

	JTextField nameField;
	JRadioButton employerButton, workerButton;
	JCheckBox checkBox;
	Font font = new Font("Arial", Font.ITALIC, 16);
	
	public WindowsReg(){
		super("Milf&Altushka Hunter/Регистрация");
		super.setBounds(550, 250, 800,500);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLocationRelativeTo(null);

		Container container = super.getContentPane();
		container.setLayout(null);

		JLabel nameLabel = new JLabel("Ваше имя или никнейм");
		nameField = new JTextField("");

		nameLabel.setFont(font);

		nameLabel.setBounds(50, 50, 200, 30);
		nameField.setBounds(250, 50, 200, 30);

		container.add(nameLabel);
		container.add(nameField);

		employerButton = new JRadioButton("Работодатель");
		workerButton = new JRadioButton("Работник");
		checkBox = new JCheckBox("Согласие на обработку персональных данных", false);
		JButton regButton = new JButton("Зарегестрироваться");

		employerButton.setFont(font);
		workerButton.setFont(font);
		checkBox.setFont(font);
		regButton.setFont(font);

		employerButton.setBounds(150, 150, 200, 30);
		workerButton.setBounds(450, 150, 200, 30);
		checkBox.setBounds(100, 250, 500, 30);
		regButton.setBounds(300,350,300,30);


		employerButton.setSelected(true);

		container.add(employerButton);
		container.add(workerButton);
		container.add(checkBox);
		container.add(regButton);

		ButtonGroup roleGroup = new ButtonGroup();
		roleGroup.add(employerButton);
		roleGroup.add(workerButton);

		regButton.addActionListener((ActionListener) new RegButtonManager());
	}

	class RegButtonManager implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String nameUser = nameField.getText();
			Boolean check = checkBox.isSelected();

			if(employerButton.isSelected() && check){
				//Реализовать другую страницу для регистрации работодателя и создания анкет
			} else if(workerButton.isSelected() && check){
				//Реализовать другую страницу для регистрации работников и приема анкет
			}
			
        }

	}
}
