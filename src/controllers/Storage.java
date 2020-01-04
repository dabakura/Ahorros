package controllers;


import models.Bank;
import models.IStorage;
import java.util.HashSet;
import java.util.Set;

public class Storage implements IStorage {
    private String Name= "Carlos";
    private Set<Bank> Banks = new HashSet<>();
    String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public Set<Bank> getBanks() {
        return Banks;
    }
    public Set<Bank> getBanksSimple() {
        return Banks;
    }
}
