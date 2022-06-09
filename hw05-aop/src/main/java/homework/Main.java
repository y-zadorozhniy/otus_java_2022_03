package homework;

public class Main {
    public static void main(String[] args) {
        TestLogging calculation = Ioc.createMyClass();
        calculation.calculation(6);
        calculation.calculation(7, 8);
        calculation.calculation(9, 10, "Otus");
        calculation.test();
    }
}
