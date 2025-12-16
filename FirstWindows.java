import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FirstWindows extends JFrame{
	
	Font font = new Font("Arial", Font.ITALIC, 16);

	public FirstWindows(){
		super("Milf&Altushka Hunter/Вход или регистрация");
		super.setBounds(550, 250, 550,300);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLocationRelativeTo(null);

		Container container = super.getContentPane();
		container.setLayout(null);

		JLabel choiceLabel = new JLabel("Выберите действие");

		JButton loginLabel = new JButton("Вход");
		JButton regLabel = new JButton("Регистрация");

		choiceLabel.setFont(font);
		loginLabel.setFont(font);
		regLabel.setFont(font);

		choiceLabel.setBounds(200, 40, 200, 30);
		loginLabel.setBounds(50, 100, 200, 30);
		regLabel.setBounds(300, 100, 200, 30);

		container.add(choiceLabel);
		container.add(loginLabel);
		container.add(regLabel);

		regLabel.addActionListener((ActionListener) new RegButtonManager());
		loginLabel.addActionListener((ActionListener) new LoginButtonManager());

	}

	class RegButtonManager implements  ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new WindowsReg().setVisible(true);
		}
	}

	class LoginButtonManager implements  ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new WindowsLogin().setVisible(true);
		}
	}
}
	

