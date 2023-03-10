package kata.supermarket.promotions;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.List;

public interface Promotion {

    BigDecimal discount(List<Item> items);
}
