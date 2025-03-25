package exercise;

public class stock {
    private String stockName;
    private int stockPrice;
    
    stock(String stockName, int stockPrice){ 
        this.stockName = stockName;
        this.stockPrice = stockPrice;
    }

    public String toString(){
        return "주식이름: " + stockName + " 주식가격: " + stockPrice;
    }

    public static void main(String[] args) {
        stock stock1 = new stock("삼성전자", 80000);
        stock stock2 = new stock("LG전자", 50000);
        System.out.println(stock1.toString());
        System.out.println(stock2.toString());
    }
}
