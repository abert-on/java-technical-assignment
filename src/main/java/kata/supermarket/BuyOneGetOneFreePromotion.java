package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BuyOneGetOneFreePromotion implements Promotion {

    private final String applicableSku;

    public BuyOneGetOneFreePromotion(String applicableSku) {
        this.applicableSku = applicableSku;
    }

    @Override
    public BigDecimal discount(List<Item> items) {
        List<Item> applicableItems = items.stream()
                .filter(item -> item.sku().equals(applicableSku))
                .collect(Collectors.toList());

        return IntStream.range(0, applicableItems.size())
                .filter(n -> n % 2 ==1)
                .mapToObj(i -> applicableItems.get(i).price())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
