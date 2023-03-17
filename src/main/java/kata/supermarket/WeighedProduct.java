package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct extends BaseProduct {

    private final BigDecimal pricePerKilo;

    public WeighedProduct(final String sku, final String type, final BigDecimal pricePerKilo) {
        super(sku, type);
        this.pricePerKilo = pricePerKilo;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }

    @Override
    public String unit() {
        return "kilos";
    }
}
