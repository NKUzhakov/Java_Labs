package bankingSystem;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private static int currentId = 1;

    private final int id;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(double balance) {
        if(balance < 0) throw new IllegalArgumentException("Balance can not be negative");
        this.id = currentId;        
        this.balance = balance;
        currentId++;
    }

    public int getId() { return id; }
    public ReentrantLock getLock() { return lock; }

    public void withdraw(double amount) { 
        if(amount < 0) throw new IllegalArgumentException("Amount can not be negative");
        if(balance - amount < 0) throw new IllegalArgumentException("Not enough money");
        balance -= amount; 
    }
    public void deposit(double amount)  { 
        if(amount < 0) throw new IllegalArgumentException("Amount can not be negative");
        balance += amount; 
    }
    public double getBalance(){ return balance; }
}
