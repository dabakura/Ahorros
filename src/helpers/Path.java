package helpers;

import java.io.File;

public enum Path {
    BANKS("/Bank.acb"),
    COUPONS("/Ahorros.json");

    private String path;

    Path(String path){
        this.path = path;
    }

    public String getPath(){
        return Canonical()+path;
    }

    private String Canonical(){
        File miDir = new File (".");
        try {
            return miDir.getCanonicalPath();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}