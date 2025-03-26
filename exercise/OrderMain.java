package exercise;

class Product{
    String productName = "";
    int Price = 0;
    int quantity = 0;

    int calculateTotalPrice(){
        return Price * quantity;
    }
    public String toString(){
        return productName + " " + Price + " " + quantity;
    }
}

class ElectronicProduct extends Product{
    ElectronicProduct(){
        this.productName = "LG Gram";
        this.Price = 2000000;
        this.quantity = 1;
    }
    int warrantyPeriod = 0;
    int extendWarranty(){
        return warrantyPeriod + 1;
    }
}

class Clothing extends Product{
    int size = 95;
    String material = "";

    int applyDiscount(){
        return Price * 90 / 100;
    }

}

class Order {
    Product[] productlist = new Product[100];
    int totalPrice = 0;
    int totalQuantity = 0;
   
    Product addProduct(Product product){
        for(int i = 0; i < productlist.length; i++){
            if(productlist[i] == null){
                productlist[i] = product;
                totalPrice += product.calculateTotalPrice();
                totalQuantity += product.quantity;
                return product;
            }
        }
        return null;
    }
    int getTotalPrice(){
        return totalPrice;
    }
    void printOrder(){
        System.out.println("주문 내역");
        for(int i = 0; i < productlist.length; i++){
            if(productlist[i] != null){
                System.out.println(productlist[i].productName + " " + productlist[i].Price + " " + productlist[i].quantity);
            }
        }
    }
}
class Laptop extends ElectronicProduct{
    String cpu = "i7";
    int ram = 0;
    void upgradeRam(){
         ram += 4;
    }
}
class TShirts extends Clothing{
    String color = "";
    String pattern = "";
    int changeSize(){
        return size + 5;
    }
}

public class OrderMain {
    public static void main(String[] args) {
        Order order = new Order();
        Laptop laptop = new Laptop();
        laptop.productName = "삼성";
        laptop.upgradeRam();

       Clothing tshirts = new TShirts();
        tshirts.productName = "티셔츠";
        tshirts.Price = 10000;
        tshirts.quantity = 2;
        order.addProduct(laptop);
        order.addProduct(tshirts);

        order.printOrder();
        System.out.println("총 가격은 " + order.getTotalPrice() + " 입니다.");
       
    }
}