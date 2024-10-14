package lab2.task1;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Person, Double> accounts;
    private String name;

    public Bank(String name) {
        this.accounts = new HashMap<Person, Double>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        log("This bank is called \"" + name + "\" now");
        this.name = name;
    }

    public void openAccount(Person person) {
        this.accounts.put(person, 0.0);
        log(person + " opened an account");
    }

    public void deposit(Person person, Double amount) {
        throwIfNoAccount(person);
        throwIfBadAmount(amount);
        accounts.put(person, accounts.get(person) + amount);
        log(person + " deposit " + amount + " UAH");
    }

    public void withdraw(Person person, Double amount) {
        throwIfNoAccount(person);
        throwIfBadAmount(person, amount);
        accounts.put(person, accounts.get(person) - amount);
        log(person + " withdraw " + amount + " UAH");
    }

    public void transfer(Person sender, Person receiver, Double amount) {
        throwIfNoAccount(sender);
        throwIfNoAccount(receiver);
        throwIfBadAmount(sender, amount);
        accounts.put(sender, accounts.get(sender) - amount);
        accounts.put(receiver, accounts.get(receiver) + amount);
        log(sender + " send " + amount + " UAH to " + receiver);
    }

    private void throwIfNoAccount(Person person) {
        if(!accounts.containsKey(person)) {
            throw new IllegalArgumentException(person + " does not have an account in this bank");
        }
    }

    private void throwIfBadAmount(double amount) {
        if(amount < 0){
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
    }

    private void throwIfBadAmount(Person person, double amount) {
        throwIfBadAmount(amount);
        throwIfNotEnoughMoney(person, amount);
    }

    private void throwIfNotEnoughMoney(Person person, Double amount) {
        if(amount > accounts.get(person)) {
            throw new IllegalArgumentException("There is not enough money on the " + person + "'s account");
        }
    }

    private void log(String message){
        System.out.println("[" + name + "] " + message);
    }
}
