package helpers;

import models.Bank;
import models.BankSimple;

import java.util.HashSet;
import java.util.Set;

public class ConvertToSerializer {
    public static Set<Object> ConvertToBankSimpleList(Set<Bank> list){
        HashSet<Object> convertList= new HashSet<>();
        list.forEach(l -> convertList.add(new BankSimple(l.getName(), l.getNick())));
        return convertList;
    }

    public static Set<Bank> ConvertToBankList(Set<BankSimple> list){
        Set<Bank> convertList= new HashSet<>();
        list.forEach(l -> convertList.add(new Bank(l.getName(), l.getNick())));
        return convertList;
    }
}