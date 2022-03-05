package Models;
import java.util.*;


public class PercentExpense extends Expense{
	public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata metadata){
		super(amount, paidBy, splits, metadata);
	}

	public boolean validate(){
		for(Split split: this.getSplits()){
			if(!(split instanceof PercentSplit))
				return false;
		}

		double totalPercent = 100;
		double sumOfSplitPercent = 0;
		for(Split split: this.getSplits()){
          	PercentSplit exactSplit = (PercentSplit) split;
			sumOfSplitPercent += exactSplit.getPercent();
		}
		
		if(sumOfSplitPercent !=totalPercent)
			return false;

		return true;
	}
}