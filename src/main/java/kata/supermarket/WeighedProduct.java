package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final String sku;
    private final BigDecimal pricePerKilo;

    public WeighedProduct(final String sku, final BigDecimal pricePerKilo) {
        this.sku = sku;
        this.pricePerKilo = pricePerKilo;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    String sku() {
        return sku;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
