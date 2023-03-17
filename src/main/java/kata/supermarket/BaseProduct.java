package kata.supermarket;

public abstract class BaseProduct {

    private String sku;
    private String type;

    protected BaseProduct(String sku, String type) {
        this.sku = sku;
        this.type = type;
    }


    public String sku() {
        return sku;
    }

    public String type() {
        return type;
    }

    public abstract String unit();
}
