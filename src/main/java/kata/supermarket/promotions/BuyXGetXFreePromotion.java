package kata.supermarket.promotions;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BuyXGetXFreePromotion implements Promotion {

    private final String applicableSku;

    private final int threshold;

    private final int numberFree;

    public BuyXGetXFreePromotion(String applicableSku, int threshold, int numberFree) {
        this.applicableSku = applicableSku;
        this.threshold = threshold;
        this.numberFree = numberFree;
    }

    @Override
    public BigDecimal discount(List<Item> items) {
        List<Item> applicableItems = items.stream()
                .filter(item -> item.sku().equals(applicableSku))
                .collect(Collectors.toList());

        if (applicableItems.isEmpty() || applicableItems.size() < threshold) {
            return BigDecimal.ZERO;
        }
        else {
            return applicableItems.get(0).price().multiply(new BigDecimal(numberFree * applicableItems.size() / threshold));
        }

    }
}
