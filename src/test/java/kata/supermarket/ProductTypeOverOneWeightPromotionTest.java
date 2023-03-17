package kata.supermarket;

import kata.supermarket.promotions.ProductTypeOverOneWeightPromotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ProductTypeOverOneWeightPromotionTest {

    @Test
    void testOneKiloOfVegHalfPrice() {
        ProductTypeOverOneWeightPromotion underTest = new ProductTypeOverOneWeightPromotion("vegetable", new BigDecimal("1.00"));

        BigDecimal discount = underTest.discount(List.of(new ItemByWeight(new WeighedProduct("999", "vegetable", new BigDecimal("1.00")), new BigDecimal("1.00"))));

        Assertions.assertEquals(new BigDecimal("0.50"), discount, "discount is half the item price");
    }

    @Test
    void testUnderThreshold() {
        ProductTypeOverOneWeightPromotion underTest = new ProductTypeOverOneWeightPromotion("vegetable", new BigDecimal("1.00"));

        BigDecimal discount = underTest.discount(List.of(new ItemByWeight(new WeighedProduct("999", "vegetable", new BigDecimal("1.00")), new BigDecimal("0.90"))));

        Assertions.assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void testNoProductTypeMatch() {
        ProductTypeOverOneWeightPromotion underTest = new ProductTypeOverOneWeightPromotion("fruit", new BigDecimal("1.00"));

        BigDecimal discount = underTest.discount(List.of(new ItemByWeight(new WeighedProduct("999", "vegetable", new BigDecimal("1.00")), new BigDecimal("1.00"))));

        Assertions.assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void testOverOneKiloOfVegHalfPrice() {
        ProductTypeOverOneWeightPromotion underTest = new ProductTypeOverOneWeightPromotion("vegetable", new BigDecimal("1.00"));

        BigDecimal discount = underTest.discount(List.of(new ItemByWeight(new WeighedProduct("999", "vegetable", new BigDecimal("1.00")), new BigDecimal("1.50"))));

        Assertions.assertEquals(new BigDecimal("1.00"), discount);


    }
}
