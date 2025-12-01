package bankingSystem;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Bank {
    private final int transactionTimeout = 50;
    private final TimeUnit transactionTimeoutTimeUnit = TimeUnit.MILLISECONDS;
    private final ArrayList<Account> accounts = new ArrayList<>();

    public void transfer(Account from, Account to, double amount) throws InterruptedException {
        while (true) {
            boolean fromLocked = from.getLock().tryLock(transactionTimeout, transactionTimeoutTimeUnit);
            boolean toLocked   = to.getLock().tryLock(transactionTimeout, transactionTimeoutTimeUnit);

            try {
                if (fromLocked && toLocked) {
                    from.withdraw(amount);
                    to.deposit(amount);                    
                    return;
                }
            } finally {
                if (fromLocked) from.getLock().unlock();
                if (toLocked)   to.getLock().unlock();
            }
            Thread.sleep(10);
        }
    }

    public void createRangomAccounts(int count){        
        double minBalance = 0, maxBalance = 100;
        for(int accInd = 0; accInd < count; accInd++)
            accounts.add(new Account(ThreadLocalRandom.current().nextDouble(minBalance, maxBalance)));
    }
    public ArrayList<Account> getAccounts(){ return accounts; }
    public double countAccountsTotalBalance(){
        double totalBalance = 0;
        for(Account account : accounts)
            totalBalance += account.getBalance();
        return totalBalance;
    }
}
