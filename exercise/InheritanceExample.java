package exercise;

class animal {
    void eat(){
        System.out.println("this animal eats food");
    }
    void sleep(){
        System.out.println("this animal is sleeping");
    }
}

class Dog extends animal{
    void bark(){
        System.out.println("this dog barks");
    }
}

public class InheritanceExample {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();
        d.sleep();
        d.bark();
    }
}
