package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarItem {
    private int _index;
    private String _day;
    private Boolean _title;
    private Boolean _active;
    private final List<ModelCalendarComponent> _models;

    public CalendarItem() {
        _models = new ArrayList<>();
        reset();
    }

    public CalendarItem(int index, Boolean title, Boolean active) {
        _index = index;
        _day = _index==0 ? "" : index + "";
        _title = title;
        _active = active;
        _models = new ArrayList<>();
    }

    public CalendarItem(int index, Boolean title, ModelCalendarComponent[] models) {
        _index = index;
        _day = _index==0 ? "" : index + "";
        _title = title;
        _active = true;
        _models = new ArrayList<>();
        _models.addAll(Arrays.asList(models));
    }

    public void reset() {
        _index = 0;
        _day = "";
        _title = false;
        _active = false;
        _models.clear();
    }

    public String get_day() {
        return _day;
    }

    public Boolean get_title() {
        return _title;
    }

    public void set_title(Boolean title) {
        this._title = title;
    }

    public Boolean get_active() {
        return _active;
    }

    public void set_active(Boolean active) {
        this._active = active;
    }

    public int get_index() {
        return _index;
    }

    public void set_index(int index) {
        this._index = index;
        this._day = _index==0 ? "" : index + "";
    }

    public List<ModelCalendarComponent> get_models() {
        return this._models;
    }
}
