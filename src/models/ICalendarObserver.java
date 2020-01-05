package models;

import helpers.Month;

import java.util.List;

public interface ICalendarObserver {
    void CalendarListener();
    Month getMonth();
}
