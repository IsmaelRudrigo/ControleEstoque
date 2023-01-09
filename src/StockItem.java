public class StockItem {
    private final String name;
    private double price;
    private int quantityInStock;
    private int reserved;

    public StockItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantity;
        this.reserved = 0;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock - reserved;
    }

    public int adjustStock(int quantity){
        int newQuantity = this.quantityInStock + quantity;
        if(newQuantity > 0){
            this.quantityInStock = newQuantity;
        }
        return quantity;
    }

    public int reserve(int quantity){
        if(quantity > 0 && quantity <= getQuantityInStock()){
            this.reserved += quantity;
        }
        return quantity;
    }

    public int unreserve(int quantity){
        if(quantity > 0 && quantity <= this.reserved){
            this.reserved -= quantity;
        }
        return quantity;
    }

    public int finaliseStock(int quantity){
        if(quantity <= reserved){
            quantityInStock -= quantity;
            reserved -= quantity;
            return quantity;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if((obj == null)  || (this.getClass() != obj.getClass())){
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 35;
    }

    @Override
    public String toString() {
        return this.name + ". Price: " + this.price + ". Reserved: " + this.reserved;
    }
}
