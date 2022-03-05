package Models;

public class ExactSplit extends Split{
	public ExactSplit(User payingUser, double amount){
		super(payingUser);
		this.setAmount(amount);
	}
}