package exercise;

public class SkalaClass {
    public static void main(String[] args) {
        System.out.print("스칼라에 오신 것을 ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("환영합니다");
    }

}
