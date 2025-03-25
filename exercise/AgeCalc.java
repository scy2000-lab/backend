/*?? ???? ???? */
package exercise;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
public class AgeCalc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = "";
        int birthyear = 0;
        int birthmonth = 0;
        int birthday = 0;
        LocalDate now = LocalDate.now();

        System.out.println("이름을 입력하세요");    
        name = sc.nextLine();
        System.out.println();

        System.out.println("생년을 입력하세요");
        birthyear = sc.nextInt();
    

        System.out.println("생월을 입력하세요");
        birthmonth = sc.nextInt();

        System.out.println("생일을 입력하세요");
        birthday = sc.nextInt();

        System.out.println(name);

        if(birthmonth > now.getMonthValue() || (birthmonth == now.getMonthValue() && birthday > now.getDayOfMonth())) {
            System.out.println(name+"씨의 만나이는 " + (now.getYear() - birthyear - 1) + " 입니다.");
        } else {
            System.out.println(name+"씨의 만나이는 " + (now.getYear() - birthyear) + " 입니다.");
        }
    }
}