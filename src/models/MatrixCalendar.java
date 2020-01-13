package models;

import helpers.Month;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

public class MatrixCalendar {
    private final List<ICalendarObserver> OBSERVERS;
    private final List<ModelCalendar> calendars;

    MatrixCalendar() {
        OBSERVERS = new ArrayList<>();
        this.calendars = new ArrayList<>();
        Initialize();
    }

    public void NewMonthSelected(){
        ClearObservers();
    }

    public void Initialize(){
        ClearObservers();
        this.calendars.clear();
        for (Month month: Month.values()){
            CalendarItem[][] calendar = new CalendarItem[6][7];
            for (int i = 0; i < 6; i++)
                for (int j = 0; j < 7; j++)
                    calendar[i][j] = new CalendarItem();
            this.calendars.add(new ModelCalendar(month,calendar));
        }
        this.calendars.forEach(this::InitializeDates);
    }

    private int[] positionDate(String input_date){
        java.util.Calendar c = java.util.Calendar.getInstance();
        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dt1=format1.parse(input_date);
            c.setTime(dt1);
        } catch (ParseException e) {e.printStackTrace();}
        return new int[]{(c.get(Calendar.DAY_OF_WEEK)-1),c.getActualMaximum(Calendar.DAY_OF_MONTH)};
    }

    private void InitializeDates(ModelCalendar calendar){
        /*java.util.Calendar c = java.util.Calendar.getInstance();
        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        int year = c.get(java.util.Calendar.YEAR);
        String input_date = (calendar.getMonth().getIndex() == 12) ? "01/01/" + (year+1)
                : "01/"+(calendar.getMonth().getIndex()+1)+"/" + year;
        try {
            Date dt1=format1.parse(input_date);
            c.setTime(dt1);
        } catch (ParseException e) {e.printStackTrace();}*/
        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        String input_date = (calendar.getMonth().getIndex() == 12) ? "01/01/" + (year+1)
                : "01/"+(calendar.getMonth().getIndex()+1)+"/" + year;
        int[] infoDate = positionDate(input_date);
        int dayOfWeek = infoDate[0];
        int fullDays = infoDate[1];
        int position = 0;
        for (int j = 0; j < fullDays; j++)
        {
            calendar.getCalendarMatrix()[position][dayOfWeek].set_index((j+1));
            calendar.getCalendarMatrix()[position][dayOfWeek].set_active(true);
            if (dayOfWeek == 6) {
                dayOfWeek = 0;
                position++;
            } else dayOfWeek++;
        }
    }

    public CalendarItem[][] getCalendarMatrix(Month month) {
        return calendars.get(month.getIndex()).getCalendarMatrix();
    }

    private void reset() {
        calendars.forEach(m -> {
            for (CalendarItem[] array : m.getCalendarMatrix())
                for (CalendarItem model : array)
                    model.reset();
        });
    }

    public boolean ChangeSubscribe(ICalendarObserver observer) {
        return OBSERVERS.add(observer);
    }

    private void ChangeCalendarCall(List<Month> months){
        OBSERVERS.forEach(o -> {
            if (months.contains(o.getMonth()))
                o.CalendarListener();
        });
    }

    private void ClearObservers(){
        OBSERVERS.clear();
    }

    public void Test(){
        ModelCalendar nn = calendars.get(0);
        CalendarItem[][] ci = nn.getCalendarMatrix();
        ci[2][3].get_models().add(new ModelCalendarComponent("cop-popu","monto: 250000"));
        List<Month> months = new ArrayList<>();
        months.add(Month.JANUARY);
        ChangeCalendarCall(months);
    }

    public void setCoupon(Coupon coupon){
        int max = Math.min(coupon.getNumberCoupons() + coupon.getMonth(), 12);
        int index = coupon.getMonth()-1;
        List<Month> months = new ArrayList<>();
        for (int i = index; i <= max; i++) {
            ModelCalendar modelCalendar = calendars.get(i);
            CalendarItem[][] calendarItems = modelCalendar.getCalendarMatrix();
            int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            String input_date = (i == 12) ? "01/01/" + (year+1)
                    : "01/"+(i+1)+"/" + year;
            int[] infoDate = positionDate(input_date);
            int dayOfWeek = infoDate[0];
            int axis_x = ((int)(dayOfWeek+(coupon.getDay()-1))%7);
            int axis_y = ((int)(dayOfWeek+(coupon.getDay())-1)/7);
            if (coupon.getMonthsCollect() > 1) {
                if(((i-index)+1)%coupon.getMonthsCollect() == 0)
                    calendarItems[axis_y][axis_x].get_models().add(new ModelCalendarComponent(
                            coupon.getBank(),
                            "Monto: X" +coupon.getMonthsCollect()+
                                    " "+(coupon.getAmount()*coupon.getMonthsCollect())));
            } else
                calendarItems[axis_y][axis_x].get_models().add(new ModelCalendarComponent(coupon.getBank(),"Monto: "+coupon.getAmount()));
            months.add(Month.values()[i]);
        }
        ChangeCalendarCall(months);
    }
}
/*
 private int[][] numeros = {{1,2,3,4},{5,6,7}};
 @Override
 public Iterator<Integer> iterator() {
 return new Iterator<Integer>() {
 int x = 0;
 int y = 0;
 @Override
 public boolean hasNext() {
 return (numeros.length > y && numeros[y].length > x);
 }

 @Override
 public Integer next() {
 int numro = numeros[y][x];
 x++;
 if(x == numeros[y].length) { x= 0; y++;}
 return numro;
 }
 };
 //return Arrays.stream(numeros).iterator();
 }

 @Override
 public void forEach(Consumer<? super Integer> action) {
 for (int i : this) {
 action.accept(i);
 }
 }

 @Override
 public Spliterator<Integer> spliterator() {
 int[] numeros2 = new int[7];
 System.arraycopy(numeros[0],0, numeros2,0,4);
 System.arraycopy(numeros[1],4, numeros2,0,3);
 //numeros[0], numeros[1]) result = Stream.of(numeros[0], numeros[1]).flatMap(Stream::of).toArray(Integer[]::new);
 return Arrays.spliterator(numeros2);
 }*/