package Models;
import java.util.*;

public class EqualExpense extends Expense{
	public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata metadata){
		super(amount, paidBy, splits, metadata);
	}

	public boolean validate(){
		for(Split split: this.getSplits()){
			if(!(split instanceof EqualSplit))
				return false;
		}
		return true;
	}
}