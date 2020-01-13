package helpers;

import models.BankSimple;
import java.io.*;
import java.util.*;

public class IOFileSerializable {
    public boolean Export(Collection<Object> objects, String path) throws IOException {
        try (ObjectOutputStream writeFile = new ObjectOutputStream(new FileOutputStream(path)))
        {
            for(Object o:objects.toArray())
                writeFile.writeObject(o);
        }
        return true;
    }

    Set<BankSimple> ImportBacks(String path) throws IOException, ClassNotFoundException {
        File f = new File(path);
        if (f.exists()){
            try (FileInputStream fis = new FileInputStream(path);
                 ObjectInputStream readFile = new ObjectInputStream(fis)){
                Set<BankSimple> list = new HashSet<>();
                while(fis.available() > 0) {
                    list.add((BankSimple)readFile.readObject());
                }
                return list;
            }
        }
        return new HashSet<BankSimple>();
    }
}
