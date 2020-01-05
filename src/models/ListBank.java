package models;

import java.util.*;

public class ListBank implements Set<Bank> {
    private Set<Bank> Banks = new HashSet<>();
    private List<IBanksObserver> OBSERVERS = new ArrayList<>();

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return Banks.contains(o);
    }

    @Override
    public Iterator<Bank> iterator() {
        return Banks.iterator();
    }

    @Override
    public Object[] toArray() {
        return Banks.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) Banks.toArray(a);
    }

    @Override
    public boolean add(Bank bank) {
        bank.setObservers(OBSERVERS);
        boolean status = Banks.add(bank);
        if(status) OBSERVERS.forEach(IBanksObserver::BankListener);
        return status;
    }

    @Override
    public boolean remove(Object o) {
        boolean status = Banks.remove(o);
        if(status) OBSERVERS.forEach(IBanksObserver::BankListener);
        return status;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return Banks.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Bank> c) {
        boolean status = Banks.addAll(c);
        if(status) OBSERVERS.forEach(IBanksObserver::BankListener);
        return status;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean status = Banks.retainAll(c);
        if(status) OBSERVERS.forEach(IBanksObserver::BankListener);
        return status;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean status = Banks.removeAll(c);
        if(status) OBSERVERS.forEach(IBanksObserver::BankListener);
        return status;
    }

    @Override
    public void clear() {
        Banks.clear();
        OBSERVERS.forEach(IBanksObserver::BankListener);
    }

    @Override
    public Spliterator<Bank> spliterator() {
        return Banks.spliterator();
    }

    public Bank Find(String name) {
        for (Bank bank : Banks){
            if (bank.getName().equals(name)) return bank;
        }
        return null;
    }

    public boolean ChangeSubscribe(IBanksObserver observer) {
        return OBSERVERS.add(observer);
    }
}
