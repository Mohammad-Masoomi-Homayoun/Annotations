import javax.annotation.PostConstruct;
import javax.xml.crypto.Data;

public class Car extends Vehicle {

    @Clean
    @IsRequired
    private String name;

    @IsRequired
    private Integer weight;

    @SuppressWarnings("unsafe")
    public int height;

    @LogStarting(logTime = "${new Date()}")
    public boolean start() {
        System.out.println(this.name + " started");
        if(checkInitials())
            return true;
        return false;
    }

    private boolean checkInitials() {
        if(this.weight != null)
            return true;

        return false;
    }

    public Car() {

    }

    public void m1() {
        System.out.println("Method M1 is running");
    }

    public void m2() {
        System.out.println("Method M2 is running");
    }

    @Deprecated
    public void m3() {
        System.out.println("Method M3 is running");
    }

    @Override
    public void m4() {
        System.out.println("Method M4 is running");
    }

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @PostConstruct
    public void setUp() {
        this.weight = 1200;
    }
}
