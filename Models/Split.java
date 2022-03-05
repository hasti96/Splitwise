package Models;


public abstract class Split{
	private User payingUser;
	private double amount;

	public Split(User payingUser){
		this.payingUser = payingUser;
	}

	//TODO: add getters and setters
  
  	public void setPayingUser(User payingUser){
      this.payingUser = payingUser;
    }
  
  	public User getPayingUser(){
      return this.payingUser;
    }
  
  	public void setAmount(double amount){
      this.amount = amount;
    }
  
  	public double getAmount(){
      return this.amount;
    }
}
