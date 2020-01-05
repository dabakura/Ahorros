package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface IStorage {
    ListBank getBanks();
    MatrixCalendar getCalendarMatrix();
}
