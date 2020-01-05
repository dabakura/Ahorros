package models;


import models.Bank;
import models.IStorage;
import java.util.HashSet;
import java.util.Set;

public class Storage implements IStorage {
    private ListBank Banks = new ListBank();
    private MatrixCalendar CalendarMatrix = new MatrixCalendar();

    @Override
    public ListBank getBanks() {
        return Banks;
    }

    @Override
    public MatrixCalendar getCalendarMatrix() { return CalendarMatrix; }
}
