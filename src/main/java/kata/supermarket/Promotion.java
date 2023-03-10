package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public interface Promotion {

    BigDecimal discount(List<Item> items);
}
