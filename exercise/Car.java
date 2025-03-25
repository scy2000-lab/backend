package exercise;

public class Car {
    String brand;
    String color;
    int speed;
    Car(){
       this("현대", "검정", 250); 
    }
    Car(String brand, String color, int speed) {
        this.brand = brand;
        this.color = color;
        this.speed = speed;
    }
    Car(String brand){
        this(brand, "검정", 250);
    }

    public void Drive() {
        System.out.println(color+"색 "+brand+"차가 "+speed+"KM/h로 달립니다.");
    } 

    
}
