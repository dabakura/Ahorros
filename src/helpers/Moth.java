package helpers;

public enum Moth {
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
    private String moth;
    private int index;

    Moth(String moth, int index){
        this.moth = moth;
        this.index = index;
    }

    public static String getMoth(int index) {
        try {
            return Moth.values()[index].moth;
        } catch (Exception e) {return Moth.JANUARY_NEW_YEAR.moth;}
    }

    public static int getIndex(String moth) {
        for (Moth m : Moth.values())
            if(m.moth.equals(moth)) return m.index;
        return Moth.JANUARY_NEW_YEAR.index;
    }

    public String getMoth() {
        return moth;
    }

    public int getIndex() {
        return index;
    }
}
