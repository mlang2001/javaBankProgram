import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JPanel implements ActionListener
{
	Bank[] customer;
	JTextField pinInput;
	JTextField moneyInput;
	JTextField nameInput;
	JButton checkPinButton;
	JButton depositButton;
	JButton withdrawButton;
	JButton updateNameButton;
	JButton signOutButton;
	boolean withdrawal;
	boolean updateName;
	int selectedCustomer;
	public Screen()
	{
		this.setLayout(null);

		withdrawal = true;
		updateName = false;
		selectedCustomer = -1;
		customer = new Bank[3];

		customer[0] = new Bank("John", 1234, 100.99);
		customer[1] = new Bank("Jen", 4321, 1000.01);
		customer[2] = new Bank("Jerry", 1111, 50.50);

		pinInput = new JTextField();
		pinInput.setBounds(300, 260, 200, 30);
		this.add(pinInput);

		checkPinButton = new JButton("Enter");
		checkPinButton.setBounds(300, 300, 200, 30);
		checkPinButton.addActionListener(this);
		this.add(checkPinButton);


		moneyInput = new JTextField();
		moneyInput.setBounds(300, 340, 200, 30);
		this.add(moneyInput);

		depositButton = new JButton("Deposit");
		depositButton.setBounds(300, 380, 200, 30);
		depositButton.addActionListener(this);
		this.add(depositButton);

		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(300, 420, 200, 30);
		withdrawButton.addActionListener(this);
		this.add(withdrawButton);


		
		nameInput = new JTextField();
		nameInput.setBounds(525, 260, 200, 30);
		this.add(nameInput);

		updateNameButton = new JButton("Change Name");
		updateNameButton.setBounds(525, 300, 200, 30);
		updateNameButton.addActionListener(this);
		this.add(updateNameButton);

		signOutButton = new JButton("Sign Out");
		signOutButton.setBounds(300, 500, 200, 30);
		signOutButton.addActionListener(this);
		this.add(signOutButton);
		this.setFocusable(true);
			
	}
	public Dimension getPreferredSize()
	{
		return new Dimension(800,600);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Color black = new Color(0,0,0);
		Color white = new Color(255,255,255);
		Color gray = new Color(100, 100, 100);
		Font title = new Font("Impact", Font.BOLD, 100);
		Font text = new Font("Impact", Font.PLAIN, 15);

		g.setFont(title);

		g.setColor(gray);
		g.fillRect(0, 0, 800, 600);

		g.setColor(black);
		g.fillRect(50, 50, 700, 500);


		g.setColor(white);
		g.drawString(" CS Bank", 210, 150);

		g.setFont(text);

		g.drawString(" Please enter your pin:", 300, 258);

		if (selectedCustomer >= 0 && customer[selectedCustomer].getAccess() == true)
		{
			g.drawString("Welcome " + customer[selectedCustomer].getName(), 300, 170);
			String balanceString = Double.toString(customer[selectedCustomer].getBalance());
			g.drawString("Balance: " + balanceString, 300, 190);
		}
		if (withdrawal == false)
		{
			g.drawString("Insufficient funds", 300, 210);
			withdrawal = true;
		}
		g.drawString("Enter new name: ", 525, 258);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == checkPinButton)
		{
			String pinText = pinInput.getText();
			int pin = Integer.parseInt(pinText);
			for(int i=0; i < customer.length; i++)
			{
				if (customer[i].checkPin(pin))
				{
					selectedCustomer = i;
					pinInput.setText("****");
				}
			}	
		}
		else if(e.getSource() == depositButton)
		{
			String amountText = moneyInput.getText();
			double amount = Double.parseDouble(amountText);
			customer[selectedCustomer].deposit(amount);
			moneyInput.setText("");
		}
		else if(e.getSource() == withdrawButton)
		{
			String amountText = moneyInput.getText();
			double amount = Double.parseDouble(amountText);
			if (amount <= customer[selectedCustomer].getBalance())
			{
				customer[selectedCustomer].withdraw(amount);
				moneyInput.setText("");
			}
			else 
			{
				withdrawal = false;
				moneyInput.setText("");
			}
		}
		else if(e.getSource() == updateNameButton)
		{
			String newName = nameInput.getText();
			customer[selectedCustomer].updateName(newName);
			nameInput.setText("");
		}
		else if(e.getSource() == signOutButton)
		{
			selectedCustomer = -1;
			nameInput.setText("");
			moneyInput.setText("");
			pinInput.setText("");
		}
		repaint();
	}
}