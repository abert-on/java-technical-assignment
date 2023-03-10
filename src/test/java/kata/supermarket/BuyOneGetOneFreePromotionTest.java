package kata.supermarket;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyOneGetOneFreePromotionTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource
    void correctDiscountCalculatedForItems(String description, List<Item> items, String applicableSku, String expectedDiscount) {
        final BuyOneGetOneFreePromotion promotion = new BuyOneGetOneFreePromotion(applicableSku);
        final BigDecimal discount = promotion.discount(items);
        assertEquals(new BigDecimal(expectedDiscount), discount);
    }

    static Stream<Arguments> correctDiscountCalculatedForItems() {
        return Stream.of(
                Arguments.of("one item that is applicable for discount", Collections.singletonList(sausageRoll()), "001", "0"),
                Arguments.of("two items that are applicable for discount", Arrays.asList(sausageRoll(), sausageRoll()), "001", "2.49"),
                Arguments.of("three items that are applicable for discount", Arrays.asList(sausageRoll(), sausageRoll(), sausageRoll()), "001", "2.49"),
                Arguments.of("four items that are applicable for discount", Arrays.asList(sausageRoll(), sausageRoll(), sausageRoll(), sausageRoll()), "001", "4.98"),
                Arguments.of("one item that isn't applicable for discount", Collections.singletonList(porkPie()), "001", "0"),
                Arguments.of("two items that aren't applicable for discount",  Arrays.asList(porkPie(), porkPie()), "001", "0"),
                Arguments.of("mix of items with discount applicable once",  Arrays.asList(porkPie(), porkPie(), sausageRoll(), sausageRoll()), "001", "2.49")

        );
    }

    private static Item sausageRoll() {
        return new ItemByUnit(new Product("001", new BigDecimal("2.49")));
    }

    private static Item porkPie() {
        return new ItemByUnit(new Product("002", new BigDecimal("1.99")));
    }

}