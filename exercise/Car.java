package exercise;

public class Car implements vehicle{
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

    public void Stop() {
        System.out.println("차가 멈춥니다.");
    }
    public void Speedup() {
        this.speed += 10;
        System.out.println("차가 속도를 올립니다. 현재 속도는 "+speed+"KM/h 입니다.");
    }

    
}
