package homework;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.Random;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AtmTest {

    Atm atm = new Atm();

    @Test
    @DisplayName("Проверяем, что ATM принимает банкноты и изменяет баланс")
    void depositAndChangeBalanceTest() {
        int[] initBanknotesArray = IntStream.generate(() -> new Random().nextInt(100)).limit(500).toArray();
        int balanceBefore = atm.getBalance();
        System.out.println("Balance before:" + balanceBefore);
        atm.deposit(initBanknotesArray);
        int balanceAfter = atm.getBalance();
        System.out.println("Balance after:" + balanceAfter);

        assertThat(balanceBefore).isLessThan(balanceAfter);
    }

    @Test
    @DisplayName("Проверяем, что ATM принимает банкноты разных номиналов")
    void depositDifferentValuesTest() {
        int[] randomBanknotesArray = IntStream.generate(() -> new Random().nextInt(100)).limit(10).toArray();

        for(int i = 0; i < randomBanknotesArray.length; i++){
            int banknote = randomBanknotesArray[i];
            int[] banknotesArray = {banknote};
            int countBefore = atm.getBanknotesCountByValue(banknote);
            atm.deposit(banknotesArray);
            int countAfter = atm.getBanknotesCountByValue(banknote);

            assertThat(countBefore).isEqualTo(countAfter - 1);
        }
    }

    //выдавать запрошенную сумму минимальным количеством банкнот.
    @Disabled
    @Test
    @DisplayName("Проверяем, что банкомат выдаёт запрошенную сумму")
    void getCashTest() {

    }

    //выдать ошибку, если сумму нельзя выдать
    @Disabled
    @Test
    @DisplayName("Проверяем возникновение ошибки если сумму нельзя выдать")
    void raiseErrorWhenCashCantBeGot() {

    }

    @Test
    @DisplayName("Проверяем выдачу суммы остатка денежных средств")
    void getBalanceTest() {
        assertThat(atm.getBalance()).isGreaterThanOrEqualTo(0);
    }
}
