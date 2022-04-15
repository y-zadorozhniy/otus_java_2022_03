package homework;

import java.util.Stack;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private final Stack<Customer> stk = new Stack<>();

    public void add(Customer customer) {
        stk.push(customer);
    }

    public Customer take() {
        return stk.pop();
    }
}
