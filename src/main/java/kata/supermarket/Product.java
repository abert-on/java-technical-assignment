package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final String sku;

    private final BigDecimal pricePerUnit;

    public Product(final String sku, final BigDecimal pricePerUnit) {
        this.sku = sku;
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    String sku() {
        return sku;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
