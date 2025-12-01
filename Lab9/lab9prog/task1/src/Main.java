import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import bankingSystem.*;

public class Main {
    public static void main(String[] args){
        int accountsNum = 100, parallelTransNum = 10000;
        Bank bank = new Bank();        
        bank.createRangomAccounts(accountsNum);        
        double initialBalance = bank.countAccountsTotalBalance();

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();      
        for (int taskNumber = 0; taskNumber < parallelTransNum; taskNumber++) {
            executor.submit(() -> {                    
                try {
                    ArrayList<Account> accounts = bank.getAccounts();
                    Account from = accounts.get(ThreadLocalRandom.current().nextInt(0, accounts.size()));
                    Account to = accounts.get(ThreadLocalRandom.current().nextInt(0, accounts.size()));
                    bank.transfer(from, to, ThreadLocalRandom.current().nextDouble(0, 100));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch(IllegalArgumentException e) {/*Not enouch money was on account*/}
            });
        }            
        executor.close();

        double resBalance = bank.countAccountsTotalBalance();
        System.out.println("Initial balance: " + initialBalance);
        System.out.println("Initial balance: " + resBalance);
    }
}



// compile: javac -d ./out/ ./src/*.java ./src/bankingSystem/*.java
// start: java -cp ./out Main