
public class ATM {
    private int balance;
    private final int withdrawalLimit;
    private final int balanceLimit;
    private int conductedActivities = 0;

    public ATM(int withdrawalLimit, int balanceLimit, int balance) {
        this.withdrawalLimit = Math.min(withdrawalLimit, balanceLimit);
        this.balanceLimit = balanceLimit;

        if (balance > balanceLimit) {
            throw new IllegalArgumentException("Incorrect balance value: balance is greater than balance limit");
        }

        this.balance = balance;
    }

    public int withdraw(int cashToWithdraw) {
        cashToWithdraw = Math.min(cashToWithdraw, withdrawalLimit);
        cashToWithdraw = Math.min(cashToWithdraw, balance);

        balance -= cashToWithdraw;
        if (cashToWithdraw != 0) {
            addConductedActivity();
        }

        return cashToWithdraw;
    }

    public int deposit(int cashToDeposit) {

        int depositedCash = (cashToDeposit + balance > balanceLimit) ? balanceLimit - balance : cashToDeposit;

        balance += depositedCash;
        if (depositedCash != 0) {
            addConductedActivity();
        }

        return  cashToDeposit - depositedCash;
    }

    private void addConductedActivity() {
        conductedActivities++;
    }

    public int getConductedActivities() {
        return conductedActivities;
    }

    public int getBalance() {
        return balance;
    }

    public int getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public int getBalanceLimit() {
        return balanceLimit;
    }

}
