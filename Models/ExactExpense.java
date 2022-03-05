package Models;
import java.util.*;

public class ExactExpense extends Expense{
	public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata metadata){
		super(amount, paidBy, splits, metadata);
	}

	public boolean validate(){
		for(Split split: this.getSplits()){
			if(!(split instanceof ExactSplit))
				return false;
		}

		double totalAmount = this.getAmount();
		double sumOfSplitAmount = 0;
		for(Split split: this.getSplits()){
			sumOfSplitAmount += split.getAmount();
		}
		
		if(sumOfSplitAmount!=totalAmount)
			return false;

		return true;
	}
}