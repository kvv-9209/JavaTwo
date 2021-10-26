package lesson3.HomeWork3;

import java.util.*;

public class App {
    public static void main(String[] args) {
        arrayUnique();
        TelephoneDirectory cont = new TelephoneDirectory();
        cont.add("Borisov", "+78003523535");
        cont.add("Borisov", "+78003523222");
        cont.add("Gusev", "+79043523222");
        cont.add("Zyablikov", "+78003525622");
        cont.get("Borisov");
    }

    public static void arrayUnique() {
        Map<String, Integer> hm = new HashMap<>();
        List<String> list = List.of("Medvedev", "Obama", "Shoygu", "Obama", "Buch", "Kopernik",
                "Hertz", "Medvedev", "Obama", "Hertz", "Buch", "Shoygu", "Shoygu", "Hertz");
        Iterator<String> iter = list.iterator();

        while (iter.hasNext()) {
            String str = iter.next();
            if (hm.containsKey(str))
                hm.put(str, hm.get(str) + 1);
            else
                hm.put(str, 1);
        }
        System.out.println(hm);
    }

}
