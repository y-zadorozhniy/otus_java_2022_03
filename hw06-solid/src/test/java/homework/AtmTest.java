package homework;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AtmTest {

    private Atm atm;

    @BeforeEach
    private void setUp() {
        Map<Integer, Integer> banknotes = new TreeMap();
        banknotes.put(50, 5);
        banknotes.put(100, 5);
        banknotes.put(200, 5);
        banknotes.put(500, 5);
        banknotes.put(1000, 5);
        banknotes.put(2000, 5);
        banknotes.put(5000, 5);

        atm = new AtmImpl();
        atm.deposit(banknotes);
    }

    @Test
    @DisplayName("Проверяем, что ATM принимает банкноты разных номиналов")
    void depositDifferentValuesTest() {

        Map<Integer, Integer> banknotes = new TreeMap<>();
        banknotes.put(50, 5);
        banknotes.put(100, 5);
        banknotes.put(200, 5);

        int balanceBeforeDeposit = atm.getBalance();
        atm.deposit(banknotes);
        int balanceAfterDeposit = atm.getBalance();
        Assertions.assertThat(balanceAfterDeposit - balanceBeforeDeposit).isEqualTo(1750);
    }

    @DisplayName("Проверяем, что банкомат выдаёт запрошенную сумму минимальным количеством банкнот")
    @Test
    void getCashTest() {
        TreeMap<Integer, Integer> expectedBanknotes = new TreeMap<>();
        expectedBanknotes.put(50, 1);
        expectedBanknotes.put(200, 2);
        expectedBanknotes.put(500, 1);
        expectedBanknotes.put(1000, 1);
        expectedBanknotes.put(2000, 1);

        Map<Integer, Integer> banknotes = atm.getCash(3950);

        System.out.println(banknotes);
        Assertions.assertThat(banknotes).isEqualTo(expectedBanknotes);
    }

    @Test()
    @DisplayName("Проверяем возникновение ошибки если сумму нельзя выдать")
    void raiseErrorWhenCashCantBeGot() {
        assertThrows(AtmSumException.class, () -> atm.getCash(49000000));
    }

    @Test
    @DisplayName("Проверяем выдачу суммы остатка денежных средств")
    void getBalanceTest() {
        assertThat(atm.getBalance()).isEqualTo(44250);
    }
}
