package kata.supermarket;

import kata.supermarket.promotions.BuyXGetXFreePromotion;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyXGetXFreePromotionTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource
    void correctDiscountCalculatedForItems(String description, List<Item> items, String applicableSku, int threshold, int numberFree, String expectedDiscount) {
        final BuyXGetXFreePromotion promotion = new BuyXGetXFreePromotion(applicableSku, threshold, numberFree);
        final BigDecimal discount = promotion.discount(items);
        assertEquals(new BigDecimal(expectedDiscount), discount);
    }

    static Stream<Arguments> correctDiscountCalculatedForItems() {
        return Stream.of(
                Arguments.of("buy one get one free on 1 applicable item", Collections.singletonList(sausageRoll()), "001", 2, 1, "0"),
                Arguments.of("buy one get one free on 2 applicable items", Arrays.asList(sausageRoll(), sausageRoll()), "001", 2, 1, "2.49"),
                Arguments.of("buy one get one free on 3  applicable items", Arrays.asList(sausageRoll(), sausageRoll(), sausageRoll()), "001", 2, 1, "2.49"),
                Arguments.of("buy one get one free on 4 applicable items", Arrays.asList(sausageRoll(), sausageRoll(), sausageRoll(), sausageRoll()), "001", 2, 1, "4.98"),
                Arguments.of("buy one get one free on 1 not applicable item", Collections.singletonList(porkPie()), "001", 2, 1, "0"),
                Arguments.of("buy one get one free on 2 not applicable items",  Arrays.asList(porkPie(), porkPie()), "001", 2, 1, "0"),
                Arguments.of("buy one get one free on mix of items where discount applicable once",  Arrays.asList(porkPie(), porkPie(), sausageRoll(), sausageRoll()), "001", 2, 1, "2.49")

        );
    }

    private static Item sausageRoll() {
        return new ItemByUnit(new Product("001", new BigDecimal("2.49")));
    }

    private static Item porkPie() {
        return new ItemByUnit(new Product("002", new BigDecimal("1.99")));
    }

}