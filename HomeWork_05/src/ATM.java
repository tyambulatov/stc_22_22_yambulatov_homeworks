import javax.naming.directory.InvalidSearchFilterException;

public class ATM {
    private int balance;
    private final int WITHDRAWAL_LIMIT;
    private final int BALANCE_LIMIT;
    private int ConductedActivities = 0;

    public ATM(int WithdrawalLimit, int BalanceLimit, int balance) {
        WITHDRAWAL_LIMIT = (WithdrawalLimit > BalanceLimit) ? BalanceLimit : WithdrawalLimit;
        BALANCE_LIMIT = BalanceLimit;

        if (balance > BalanceLimit) {
            throw new IllegalArgumentException("Incorrect balance value: balance is greater than balance limit");
        }

        this.balance = balance;
    }

    public int withdraw(int cashToWithdraw) {
        cashToWithdraw = (cashToWithdraw > WITHDRAWAL_LIMIT) ? WITHDRAWAL_LIMIT : cashToWithdraw;
        cashToWithdraw = (cashToWithdraw > balance) ? balance : cashToWithdraw;

        balance -= cashToWithdraw;
        if (cashToWithdraw != 0) {
            AddConductedActivity();
        }

        return cashToWithdraw;
    }

    public int deposit(int cashToDeposit) {

        int depositedCash = (cashToDeposit + balance > BALANCE_LIMIT) ? BALANCE_LIMIT - balance : cashToDeposit;

        balance += depositedCash;
        if (depositedCash != 0) {
            AddConductedActivity();
        }

        return  cashToDeposit - depositedCash;
    }

    public void AddConductedActivity() {
        ConductedActivities++;
    }

    public int getConductedActivities() {
        return ConductedActivities;
    }

    public int getBalance() {
        return balance;
    }

    public int getWITHDRAWAL_LIMIT() {
        return WITHDRAWAL_LIMIT;
    }

    public int getBALANCE_LIMIT() {
        return BALANCE_LIMIT;
    }

}
