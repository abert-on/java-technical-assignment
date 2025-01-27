package kata.supermarket;

import kata.supermarket.promotions.Promotion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {
    private final List<Item> items;
    private final List<Promotion> promotions;

    public Basket(List<Promotion> promotions) {
        this.promotions = promotions;
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    List<Promotion> promotions() {
        return Collections.unmodifiableList(promotions);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;
        private final List<Promotion> promotions;

        TotalCalculator() {
            this.items = items();
            this.promotions = promotions();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of
         *  the discount calculations.
         *  It is not likely to be the best place to do those calculations.
         *  Think about how Basket could interact with something
         *  which provides that functionality.
         */
        private BigDecimal discounts() {
            return promotions.stream().map(promotion -> promotion.discount(items)).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
