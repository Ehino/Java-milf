import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Windows extends JFrame {

	JTextField nameField;
	JRadioButton employerButton, workerButton;
	JCheckBox checkBox;
	
	public Windows(){
		super("Milf&Altushka Hunter/Регистрация");
		super.setBounds(550, 250, 800,500);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = super.getContentPane();
		container.setLayout(new GridLayout(5,5,3,3));

		JLabel nameLabel = new JLabel("Ваше имя или никнейм");
		nameField = new JTextField("", 1);

		container.add(nameLabel);
		container.add(nameField);

		employerButton = new JRadioButton("Работодатель");
		workerButton = new JRadioButton("Работник");
		checkBox = new JCheckBox("Согласие на обработку персональных данных", false);
		JButton regButton = new JButton("Зарегестрироваться");

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
