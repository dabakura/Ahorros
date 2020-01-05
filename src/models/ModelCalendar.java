package models;

import helpers.Month;

public class ModelCalendar {
    private Month month;
    private CalendarItem[][] calendarMatrix;

    public ModelCalendar() {
        month = null;
        calendarMatrix = null;
    }

    public ModelCalendar(Month month, CalendarItem[][] calendarMatrix) {
        this.month = month;
        this.calendarMatrix = calendarMatrix;
    }

    public Month getMonth() {
        return this.month;
    }

    public CalendarItem[][] getCalendarMatrix() {
        return this.calendarMatrix;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public void setCalendarMatrix(CalendarItem[][] calendarMatrix) {
        this.calendarMatrix = calendarMatrix;
    }
}
