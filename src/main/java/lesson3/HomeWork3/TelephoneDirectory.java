package lesson3.HomeWork3;


import java.util.*;
import java.util.List;

public class TelephoneDirectory {

    private Map<String, List<String>> contact = new HashMap<>();
    private List<String> list;


    public void add(String family, String number) {
        if (contact.containsKey(family)) {
            list = contact.get(family);
            list.add(number);
            contact.put(family, list);
        } else {
            list = new ArrayList<>();
            list.add(number);
            contact.put(family, list);
        }
    }

    public List<String> getContact(String family) {
        return contact.get(family);
    }

    public void get(String family) {
        System.out.println(family + " = " + getContact(family));
    }

}



