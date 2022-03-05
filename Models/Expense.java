package Models;
import java.util.*;

public abstract class Expense{
	private String id;
	private double amount;
	private User paidBy;
	private List<Split> splits;
  	private ExpenseMetadata metadata;

	public Expense(double amount, User paidBy, List<Split> splits, ExpenseMetadata metadata){
		this.amount = amount;
		this.paidBy = paidBy;
		this.splits = splits;
      	this.metadata = metadata;
	}

	//TODO: add getters and setters
  
  	public String getId(){
      return this.id;
    }
  	
  	public double getAmount(){
      return this.amount;
    }
  
  	public User getPaidBy(){
      return this.paidBy;
    }
  
  	public List<Split> getSplits(){
      return this.splits;
    }
  
  	public ExpenseMetadata getMetadata(){
      return this.metadata;
    }

	public abstract boolean validate();
}