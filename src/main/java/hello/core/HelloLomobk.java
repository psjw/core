package hello.core;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLomobk {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLomobk helloLomobk=new HelloLomobk();
        helloLomobk.setName("aaa");
        String name =helloLomobk.getName();
        System.out.println("name = "+name);
        System.out.println("helloLomobk = " + helloLomobk);
    }

}
