public class Main {
    public static void main(String[] args) {
        testATMConstructor();
        testATMWithdraw();
        testATMDeposit();
        testATMConductedActivitiesAndBalance();
    }

    public static void testATMConstructor() {
        ATM atm = new ATM(0, 0, 0);
        assert 0 == atm.getBALANCE_LIMIT();
        assert 0 == atm.getWITHDRAWAL_LIMIT();

        atm = new ATM(1, 0, 0);
        assert 0 == atm.getBALANCE_LIMIT();
        assert 0 == atm.getWITHDRAWAL_LIMIT();

        atm = new ATM(0, 1, 0);
        assert 1 == atm.getBALANCE_LIMIT();
        assert 0 == atm.getWITHDRAWAL_LIMIT();

        atm = new ATM(1, 2, 0);
        assert 2 == atm.getBALANCE_LIMIT();
        assert 1 == atm.getWITHDRAWAL_LIMIT();

        try {
            atm = new ATM(0, 2, 3);
            throw new IllegalArgumentException("Incorrect ATM object created");
        } catch (Exception exception) { }
    }

    public static void testATMWithdraw() {
        assert 0 == new ATM(0, 2, 0).withdraw(0);
        assert 0 == new ATM(0, 2, 0).withdraw(1);
        assert 0 == new ATM(0, 2, 1).withdraw(0);
        assert 0 == new ATM(0, 2, 1).withdraw(1);
        assert 0 == new ATM(1, 2, 0).withdraw(0);
        assert 0 == new ATM(1, 2, 0).withdraw(1);
        assert 0 == new ATM(1, 2, 1).withdraw(0);
        assert 1 == new ATM(1, 2, 1).withdraw(1);

        assert 0 == new ATM(0, 3, 3).withdraw(4);
        assert 2 == new ATM(2, 3, 3).withdraw(4);
        assert 3 == new ATM(3, 3, 3).withdraw(4);
        assert 3 == new ATM(5, 3, 3).withdraw(4);
    }

    public static void testATMDeposit() {
        assert 0 == new ATM(2, 0, 0).deposit(0);
        assert 1 == new ATM(2, 0, 0).deposit(1);
        assert 0 == new ATM(2, 1, 0).deposit(0);
        assert 0 == new ATM(2, 1, 0).deposit(1);
        assert 0 == new ATM(2, 1, 1).deposit(0);
        assert 1 == new ATM(2, 1, 1).deposit(1);

        assert 2 == new ATM(2, 0, 0).deposit(2);
        assert 1 == new ATM(2, 1, 0).deposit(2);
        assert 2 == new ATM(2, 1, 1).deposit(2);
    }

    public static void  testATMConductedActivitiesAndBalance() {
        ATM atm = new ATM(2, 2, 2);

        assert 0 == atm.deposit(0);
        assert 2 == atm.getBalance();
        assert 0 == atm.getConductedActivities();

        assert 0 == atm.withdraw(0);
        assert 2 == atm.getBalance();
        assert 0 == atm.getConductedActivities();

        assert 1 == atm.deposit(1);
        assert 2 == atm.getBalance();
        assert 0 == atm.getConductedActivities();

        assert 2 == atm.withdraw(3);
        assert 0 == atm.getBalance();
        assert 1 == atm.getConductedActivities();

        assert 0 == atm.withdraw(1);
        assert 0 == atm.getBalance();
        assert 1 == atm.getConductedActivities();

        assert 1 == atm.deposit(3);
        assert 2 == atm.getBalance();
        assert 2 == atm.getConductedActivities();
    }
}
