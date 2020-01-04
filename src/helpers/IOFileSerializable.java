package helpers;

import models.BankSimple;
import java.io.*;
import java.util.*;

public class IOFileSerializable {
    public boolean Export(Collection<Object> objects, String title) throws IOException {
        try (ObjectOutputStream writeFile = new ObjectOutputStream(new FileOutputStream("/home/david/"+title+".acb")))
        {
            for(Object o:objects.toArray())
                writeFile.writeObject(o);
        }
        return true;
    }

    public Set<BankSimple> ImportBacks(String title) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream("/home/david/"+title+".acb");
                ObjectInputStream readFile = new ObjectInputStream(fis)){
            Set<BankSimple> list = new HashSet<>();
            while(fis.available() > 0) {
                list.add((BankSimple)readFile.readObject());
            }
            return list;
        }
    }
}
