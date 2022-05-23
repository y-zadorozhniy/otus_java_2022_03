package homework;

import java.util.TreeMap;
import java.util.Map;

public class Atm {

    private int balance;
    private TreeMap<Integer, Integer> banknotes = new TreeMap<>();

    public void deposit(int[] banknotesArray) {
        for (int i = 0; i < banknotesArray.length; i++) {
            int banknote = banknotesArray[i];
            banknotes.merge(banknote, 1, Integer::sum);
            increaseBalance(banknote);
        }
    }
    public int getBanknotesCountByValue(int banknote){
        return banknotes.containsKey(banknote) ? banknotes.get(banknote) : 0;
    }

    public int getBalance() {
        return balance;
    }

    private void increaseBalance(int val){
        balance += val;
    }

    private void decreaseBalance(int val){
        balance -= val;
    }

    public int[] getCash(int sum){
        int[] cash = {1};
        if (banknotes.containsKey(sum)){
            cash[0] = 10;
        }
        return cash;

    }
}
