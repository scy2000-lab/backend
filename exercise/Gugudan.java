package exercise;

public class Gugudan {
    public static void main(String[] args) {
        System.out.println("구구단을 출력합니다.");
        for (int i = 1; i < 10; i++) {
            System.out.println(i + "단");
            for (int j = 1; j < 10; j++) {
                System.out.println(i + " * " + j + " = " + i * j);
            }
           
        }
    }
}
