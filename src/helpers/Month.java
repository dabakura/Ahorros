package helpers;

public enum Month {
    JANUARY("Enero",0),
    FEBRUARY("Febrero",1),
    MARCH("Marzo",2),
    APRIL("Abril",3),
    MAY("Mayo",4),
    JUNE("Junio",5),
    JULY("Julio",6),
    AUGUST("Agosto",7),
    SEPTEMBER("Septiembre",8),
    OCTOBER("Octubre",9),
    NOVEMBER("Noviembre",10),
    DECEMBER("Diciembre",11),
    JANUARY_NEW_YEAR("Enero",12);
    private String month;
    private int index;

    Month(String month, int index){
        this.month = month;
        this.index = index;
    }

    public static String getMonth(int index) {
        try {
            return Month.values()[index].month;
        } catch (Exception e) {return Month.JANUARY_NEW_YEAR.month;}
    }

    public static int getIndex(String month) {
        for (Month m : Month.values())
            if(m.month.equals(month)) return m.index;
        return Month.JANUARY_NEW_YEAR.index;
    }

    public String getMonth() {
        return month;
    }

    public int getIndex() {
        return index;
    }

    public static Month getMonth(String value){
        for (Month m : Month.values())
            if(m.month.equals(value)) return m;
        return Month.JANUARY_NEW_YEAR;
    }
}
