package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helpers.IOFileSerializable;
import helpers.Month;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import models.mappers.MapperCoupon;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

public class MatrixCalendar {
    private final List<ICalendarObserver> OBSERVERS;
    private final List<ModelCalendar> calendars;
    private final List<Coupon> coupons;

    MatrixCalendar() {
        OBSERVERS = new ArrayList<>();
        this.calendars = new ArrayList<>();
        this.coupons = new ArrayList<>();
        Initialize();
    }

    public void NewMonthSelected(){
        ClearObservers();
    }

    public void Initialize(){
        ClearObservers();
        this.calendars.clear();
        this.coupons.clear();
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
            //calendar.getCalendarMatrix()[position][dayOfWeek].set_active(true);
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
        this.coupons.add(coupon);
        int max = Math.min((coupon.getNumberCoupons() + coupon.getMonth() - 2), 12);
        int index = coupon.getMonth()-1;
        List<Month> months = new ArrayList<>();
        int acarreo = 0;
        for (int i = index; i <= max; i++) {
            ModelCalendar modelCalendar = calendars.get(i);
            CalendarItem[][] calendarItems = modelCalendar.getCalendarMatrix();
            int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            String input_date = (i == 12) ? "01/01/" + (year+1)
                    : "01/"+(i+1)+"/" + year;
            int[] infoDate = positionDate(input_date);
            int dayOfWeek = infoDate[0];
            int fullDays = infoDate[1];
            if (acarreo > 0){
                int axis_x = ((int)(dayOfWeek+(acarreo-1))%7);
                if (axis_x==0) axis_x++;
                int axis_y = ((int)(dayOfWeek+(acarreo)-1)/7);
                setCoupon(months, calendarItems[axis_y][axis_x], i-1, coupon, calendarItems);
                acarreo = 0;
            }
            int axis_x = ((int)(dayOfWeek+(coupon.getDay()-1))%7);
            if (axis_x==0 || coupon.getDay()>fullDays) {
                if (fullDays>coupon.getDay())
                    axis_x++;
                else {
                    acarreo = (coupon.getDay()-fullDays) > 0 ? (coupon.getDay()-fullDays): 1;
                    continue;
                }
            }
            int axis_y = ((int)(dayOfWeek+(coupon.getDay())-1)/7);
            setCoupon(months, calendarItems[axis_y][axis_x], i, coupon, calendarItems);
        }
        ChangeCalendarCall(months);
    }

    private void setTotalCoupon(CalendarItem[][] calendarItems, Coupon coupon){
        for (int i = 2; i <= 5; i++) {
            if (calendarItems[5][i].get_models().size()<3){
                calendarItems[5][i].set_title(true);
                calendarItems[5][i].get_models().add(new ModelCalendarComponent(
                        coupon.getDay() + " " + coupon.getBank(),
                        "Total: " +coupon.getCapital()));
                break;
            }
        }
    }

    private void setCoupon(List<Month> months, CalendarItem calendarItem, int index, Coupon coupon, CalendarItem[][] calendarItems) {
        String content = "Monto: "+coupon.getAmount();
        String title = coupon.getBank();
        int max = (coupon.getNumberCoupons() + coupon.getMonth() - 2);
        if (coupon.getMonthsCollect() > 1) {
            if((index-(coupon.getMonth()-2))%coupon.getMonthsCollect() != 0 && max != index) return;
            int monthsAcu = (index % coupon.getMonthsCollect()) + 1;
            content = "Monto: X" + monthsAcu + " " + (coupon.getAmount()*monthsAcu);
        }
        if (max == index) {
            title = "** " + title;
            calendarItem.set_title(true);
            setTotalCoupon(calendarItems, coupon);
        }
        calendarItem.get_models().add(new ModelCalendarComponent(title,content));
        months.add(Month.values()[index]);
    }

    public boolean save(){
        List<CouponSimple> couponSimples = MapperCoupon.toCouponsSimple(this.coupons);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(couponSimples);
        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        fileChooser.setTitle("Guardar Cupones");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.setInitialFileName("Cupones.json");
        FileChooser.ExtensionFilter jsonExtensionFilter =
                new FileChooser.ExtensionFilter("JSON files", "*.json");
        fileChooser.getExtensionFilters().add(jsonExtensionFilter);
        fileChooser.setSelectedExtensionFilter(jsonExtensionFilter);
        //Prompt user to select a file
        File file = fileChooser.showSaveDialog(null);
        try (FileWriter fileWriter = new FileWriter(file.getAbsolutePath())) {
            fileWriter.write(json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
