package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;

    ItemByUnit(final Product product) {
        this.product = product;
    }

    public BigDecimal price() {
        return product.pricePerUnit();
    }

    public String sku() {
       return product.sku();
    }

    @Override
    public String type() {
        return product.type();
    }

    @Override
    public String unit() {
        return product.unit();
    }

    @Override
    public BigDecimal amount() {
        return BigDecimal.ONE;
    }
}
