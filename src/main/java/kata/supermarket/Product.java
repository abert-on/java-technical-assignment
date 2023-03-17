package kata.supermarket;

import java.math.BigDecimal;

public class Product extends BaseProduct {

    private final BigDecimal pricePerUnit;

    public Product(final String sku, final String type, final BigDecimal pricePerUnit) {
        super(sku, type);
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

    @Override
    public String unit() {
        return "individual";
    }
}
