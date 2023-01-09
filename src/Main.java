import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();
    public static void main(String[] args) {

        StockItem temp = new StockItem("cup", 0.99, 10);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.95, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 5.50, 7);
        stockList.addStock(temp);

        temp = new StockItem("bread", 0.99, 40);
        stockList.addStock(temp);

        temp = new StockItem("pencil", 1.00, 24);
        stockList.addStock(temp);

        System.out.println(stockList);
        for(String s : stockList.items().keySet()){
            System.out.println(s);
        }

        Basket basket = new Basket("Basket");

        sellItem(basket, "pencil", 12);
        System.out.println(basket);
        sellItem(basket, "pencil", 5);
        System.out.println(basket);
        removeItem(basket, "pencil", 3);
        System.out.println(basket);
        checkOut(basket);
        System.out.println(basket);
        System.out.println(stockList);

    }

    public static void sellItem(Basket basket, String item, int quantity){

        StockItem inBasket = stockList.get(item);

        if(inBasket == null){
            System.out.println("We don't sell " + item);
        }
        if(stockList.reserve(item, quantity) != 0){
            basket.addToBasket(inBasket, quantity);
        }
    }

    public static void removeItem(Basket basket, String item, int quantity){

        StockItem inBasket = stockList.get(item);

        if(inBasket == null){
            System.out.println(item + " is not in the basket");
        }
        if(quantity > 0){
            basket.removeFromBasket(inBasket, quantity);
            stockList.unreserve(item, quantity);
        }
    }

    public static void checkOut(Basket basket){

        for(Map.Entry<StockItem, Integer> item : basket.items().entrySet()){
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}