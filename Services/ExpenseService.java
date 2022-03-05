package Services;
import Models.*;
import java.util.*;

class ExpenseService {
  public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits, ExpenseMetadata metadata){
    switch (expenseType){
      case EXACT:
		return new ExactExpense(amount, paidBy, splits, metadata);
            
      case PERCENT:
        for(Split split: splits) {
          PercentSplit percentSplit = (PercentSplit) split;
          split.setAmount(amount*percentSplit.getPercent()/100);
        }
        return new PercentExpense(amount, paidBy, splits, metadata);

      case EQUAL:
        int totalNoOfUsers = splits.size();
        double equalAmount = amount / totalNoOfUsers;

        for(Split split: splits) {
          split.setAmount(equalAmount);
        }

        return new EqualExpense(amount, paidBy, splits, metadata);

      default:
        return null;
    }
  }
}
