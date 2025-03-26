package exercise;

class Box<T>{
    private T t;
    public void set(T t){
        this.t = t;
    }
    public T get(){
        return t;
    }
}

public class GenericsExample {
    public static void main(String[] args) {
        Box<String> box = new Box<String>();
        box.set("Hello World");
        System.out.println(box.get());
        
        Box<Integer> box2 = new Box<Integer>();
        box2.set(123);
        System.out.println(box2.get());
    }
}