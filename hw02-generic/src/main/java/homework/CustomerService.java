package homework;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong((Customer::getScores)));

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> entry = map.firstEntry();
        Customer customerSource = entry.getKey();
        Customer customerCopy = new Customer(customerSource.getId(), customerSource.getName(), customerSource.getScores());
        return new AbstractMap.SimpleEntry<>(customerCopy, entry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        if (map.higherEntry(customer) != null) {
            Map.Entry<Customer, String> entry = map.higherEntry(customer);
            Customer customerSource = entry.getKey();
            Customer customerCopy = new Customer(customerSource.getId(), customerSource.getName(), customerSource.getScores());
            return new AbstractMap.SimpleEntry<>(customerCopy, entry.getValue());
        } else {
            return null;
        }
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
