package kata.supermarket;

import kata.supermarket.promotions.BuyXGetXFreePromotion;
import kata.supermarket.promotions.ProductTypeOverOneWeightPromotion;
import kata.supermarket.promotions.Promotion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items, List<Promotion> promotions) {
        final Basket basket = new Basket(promotions);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                twoItemsPricedByUnitOnBuyOneGetOneFreeDiscount(),
                threeItemsPricedByUnitOnBuyThreeForThePriceOfTwoDiscount(),
                vegatablesOver1Kilo()
        );
    }

    private static Arguments vegatablesOver1Kilo() {
        return Arguments.of("a kilo of different vegtables", "0.75", Arrays.asList(fiveHundredGramsCarrots(), fiveHundredGramsSprouts()), Arrays.asList(new ProductTypeOverOneWeightPromotion("vegetable", BigDecimal.ONE)));
    }

    private static Item fiveHundredGramsSprouts() {
        return new ItemByWeight(new WeighedProduct("501", "vegetable", new BigDecimal("1.50")), new BigDecimal("0.50"));
    }

    private static Item fiveHundredGramsCarrots() {
        return new ItemByWeight(new WeighedProduct("500", "vegetable", new BigDecimal("1.50")), new BigDecimal("0.50"));
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()), Collections.emptyList());
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix()), Collections.emptyList()
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()), Collections.emptyList());
    }

    private static Arguments twoItemsPricedByUnitOnBuyOneGetOneFreeDiscount() {
        return Arguments.of("two items priced per unit on buy on get on free discount",
                "2.50",
                Arrays.asList(aPackOfSixSausageRolls(), aPackOfSixSausageRolls()),
                Collections.singletonList(new BuyXGetXFreePromotion("003", 2, 1)));
    }

    private static Arguments threeItemsPricedByUnitOnBuyThreeForThePriceOfTwoDiscount() {
        return Arguments.of("three items priced per unit on buy three for the price  of two promotion",
                "5.00",
                Arrays.asList(aPackOfSixSausageRolls(), aPackOfSixSausageRolls(), aPackOfSixSausageRolls()),
                Collections.singletonList(new BuyXGetXFreePromotion("003", 3, 1)));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()), Collections.emptyList());
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList(), Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product("001", "dairy", new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product("002", "biscuitss", new BigDecimal("1.55")).oneOf();
    }

    private static Item aPackOfSixSausageRolls() {
        return new Product("003", "deli", new BigDecimal("2.50")).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct("004", "sweets", new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct("005", "sweets", new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}