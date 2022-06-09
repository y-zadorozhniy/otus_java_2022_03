package homework;

import java.util.Map;

public interface Atm {
    void deposit(Map<Integer, Integer> banknotes);

    Map<Integer, Integer> getCash(int amount);

    int getBalance();
}
