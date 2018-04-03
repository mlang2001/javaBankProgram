public class Bank
{
	String name;
	double balance;
	int pin;
	boolean access;
	public Bank(String name, int pin, double balance)
	{
		this.name = name;
		this.balance = balance;
		this.pin = pin;
		access = false;
	}
	public String getName()
	{
		if(access == true)
		{
			return(name);
		}
		else
		{
			return("No Access");
		}
	}
	public double getBalance()
	{
		return(balance);
	}
	public void disableAccess()
	{
		access = false;
	}
	public boolean getAccess()
	{
		return access;
	}
	public boolean checkPin(int pin)
	{
		if (this.pin == pin)
		{
			access = true;
			return true;
		}
		else
		{
			return false;
		}
	}
	public void withdraw(double withdrawal)
	{
		balance = balance - withdrawal;
	}
	public void deposit(double deposit)
	{
		balance = balance + deposit;
	}
	public void updateName(String newName)
	{
		name = newName;
	}
}