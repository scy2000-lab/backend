package exercise;

public class CarMain {
    public static void main(String[] args) {
        Car car = new Car();
        Car car2 = new Car("현대");
        Car car3 = new Car("기아", "빨간", 120);
        System.out.println();
        car.Drive();
        car2.Drive();
        car3.Drive();
    }
    
}
