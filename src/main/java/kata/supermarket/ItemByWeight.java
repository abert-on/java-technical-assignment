package kata.supermarket;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, HALF_UP);
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
        return weightInKilos;
    }
}
