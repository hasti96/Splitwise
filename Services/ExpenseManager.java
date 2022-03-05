package Services;
import java.util.*;
import Models.*;

class ExpenseManager{
    List<Expense> expenses;
    Map<String, User> userMap;
    Map<String, Map<String, Double>> balanceSheet;

    public ExpenseManager() { 
        this.expenses = new ArrayList<Expense>();
        this.userMap = new HashMap<String, User>();
        this.balanceSheet = new HashMap<String, Map<String, Double>>();
    }

    // add User
    public void addUser(User user){
        this.userMap.put(user.getId(), user);
        this.balanceSheet.put(user.getId(), new HashMap<String, Double>());
    }

    // add Expenses
    public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits, ExpenseMetadata metadata) {
        Expense expense = ExpenseService.createExpense(expenseType, amount, userMap.get(paidBy), splits, metadata);
        expenses.add(expense);

        // update the balance sheet for all the users
        for(Split split: expense.getSplits()) {
            String paidTo = split.getPayingUser().getId();

            Map<String, Double> balancesForPaidByUser = balanceSheet.get(paidBy);
            // checking if paidBy balancesheet contains paidTo User
            if(!balancesForPaidByUser.containsKey(paidTo)){
                balancesForPaidByUser.put(paidTo, 0.0);
            }
            balancesForPaidByUser.put(paidTo, balancesForPaidByUser.get(paidTo) + split.getAmount());

            Map<String, Double> balancesForPaidToUser = balanceSheet.get(paidTo);
            // checking if paidTo balancesheet contains paidBy User
            if(!balancesForPaidToUser.containsKey(paidBy)){
                balancesForPaidToUser.put(paidBy, 0.0);
            }
            balancesForPaidToUser.put(paidBy, balancesForPaidToUser.get(paidBy) - split.getAmount());
        }

    }

    // show balances - show balance for a specific userId or show balance for all users
    public void showBalance(String userId){
        boolean isEmpty = true;

        for(Map.Entry<String, Double> userBalance: balanceSheet.get(userId).entrySet()){
            if(userBalance.getValue() != 0){
                isEmpty = false;
                this.printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }

        if(isEmpty) { 
            System.out.println("No Balances");
        }
    }

    public void showBalances(){
        boolean isEmpty = true;

        for(Map.Entry<String, Map<String, Double>> allBalances: balanceSheet.entrySet()) {
            System.out.println("Showing balances for user - "+ userMap.get(allBalances.getKey()).getName());
          	isEmpty = true;
            for(Map.Entry<String, Double> userBalance: allBalances.getValue().entrySet()) {
                if(userBalance.getValue() > 0){
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
          
            if(isEmpty) { 
                System.out.println("No Balances");
            }
        }
        
    }

    private void printBalance(String user1, String user2, double amount) {
        String user1Name = userMap.get(user1).getName();
        String user2Name = userMap.get(user2).getName();

        if(amount < 0){
            System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
        } else if (amount > 0){
            System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
        }
    }

}
