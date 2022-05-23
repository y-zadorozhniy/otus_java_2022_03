package homework;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class AtmImpl implements Atm {

    private final TreeMap<Integer, Integer> banknotes_storage = new TreeMap<>(Collections.reverseOrder());

    @Override
    public void deposit(Map<Integer, Integer> banknotes) {
        banknotes.forEach((banknote, quantity)
                -> banknotes_storage.compute(banknote, (b, n) -> n == null ? quantity : n + quantity));
    }

    @Override
    public Map<Integer, Integer> getCash(int amount) {
        TreeMap<Integer, Integer> cash = new TreeMap<>();

        if (getBalance() < amount) {
            throw new AtmSumException("Not enough funds");
        }

        Integer higherKey = banknotes_storage.higherKey(amount);

        if (higherKey == null) {
            throw new AtmSumException("No banknotes suitable for dispensing");
        }

        Map<Integer, Integer> tailMap = banknotes_storage.tailMap(higherKey);
        int sum = amount;

        for (Map.Entry<Integer, Integer> entry : tailMap.entrySet()) {
            int banknote = entry.getKey();
            int quantity = entry.getValue();
            int requiredQuantity = sum / banknote;

            if (quantity == 0 || requiredQuantity == 0) {
                continue;
            }

            int takenQuantity = Math.min(quantity, requiredQuantity);
            int takenAmount = takenQuantity * banknote;

            sum -= takenAmount;

            tailMap.put(banknote, quantity - takenQuantity);
            cash.put(banknote, takenQuantity);
        }
        if (sum == 0) {
            return cash;
        } else {
            throw new AtmSumException("No banknotes suitable for dispensing");
        }
    }

    @Override
    public int getBalance() {
        return banknotes_storage.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey() * entry.getValue())
                .sum();
    }

}
