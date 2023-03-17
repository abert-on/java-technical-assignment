package kata.supermarket.promotions;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class ProductTypeOverOneWeightPromotion implements Promotion {
    private final String productType;
    private final BigDecimal weightThreshold;

    public ProductTypeOverOneWeightPromotion(String productType, BigDecimal weightThreshold) {

        this.productType = productType;
        this.weightThreshold = weightThreshold;
    }

    @Override
    public BigDecimal discount(List<Item> items) {
        List<Item> applicableItems = getApplicableItems(items);
        BigDecimal weight = applicableItems.stream()
                .map(Item::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (weight.compareTo(weightThreshold) >= 0) {
            BigDecimal weightRemainder = weight.subtract(weightThreshold);
            BigDecimal previousCost = applicableItems.stream()
                    .map(Item::price)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal pricePerKilo = previousCost.divide(weight, RoundingMode.HALF_UP);

            return pricePerKilo.divide(new BigDecimal("2"), RoundingMode.HALF_UP)
                    .add(weightRemainder.multiply(pricePerKilo)).setScale(2, RoundingMode.HALF_UP);
        }

        return BigDecimal.ZERO;
    }

    private List<Item> getApplicableItems(List<Item> items) {

        return items.stream().filter(item -> item.type().equals(productType) && item.unit().equals("kilos"))
                .collect(Collectors.toList());
    }
}
