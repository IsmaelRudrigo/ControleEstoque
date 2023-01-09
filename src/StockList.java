import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StockList {

    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new HashMap<>();
    }

    public int addStock(StockItem item){

        if(item != null){
            StockItem inStock = list.getOrDefault(item.getName(), item);

            if(inStock != item){
                inStock.adjustStock(inStock.getQuantityInStock());
            }

            list.put(item.getName(), inStock);
            return inStock.getQuantityInStock();
        }
        return 0;
    }

    public int sellStock(String item, int quantity){
        StockItem inStock = list.getOrDefault(item, null);

        if((inStock != null) && (quantity > 0)){
            return inStock.finaliseStock(quantity);
        }
        return 0;
    }

    public int reserve(String item, int quantity){
        StockItem inStock = list.getOrDefault(item, null);

        if((inStock != null) && (quantity > 0)){
            inStock.reserve(quantity);
            return quantity;
        }
        return 0;
    }

    public int unreserve(String item, int quantity){
        StockItem inStock = list.getOrDefault(item, null);

        if(inStock != null){
            inStock.unreserve(quantity);
            return quantity;
        }
        return 0;
    }

    public StockItem get(String key){
        return list.get(key);
    }

    public Map<String, StockItem> items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for(Map.Entry<String, StockItem> item : items().entrySet()) {
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.getQuantityInStock();

            s = s + stockItem + ". There are " + stockItem.getQuantityInStock() + " in stock. Value of items: ";
            s = s + String.format("%.2f",itemValue) + "\n";
            totalCost += itemValue;
        }
        return s + "Total stock value " + totalCost;
    }
}
