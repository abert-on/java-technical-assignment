package kata.supermarket;

import java.math.BigDecimal;

public interface Item {
    BigDecimal price();

    String sku();

    String type();

    String unit();

    BigDecimal amount();
}
