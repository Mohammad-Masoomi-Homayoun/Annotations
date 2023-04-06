import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

//        Reflection And RMI

        // showing dynamic loading
//        dynamicLoadingExample();

        Car quick = new Car("Quick");
        Car samand = new Car("Samand");

        // showing facilities of class object
//        loadClassObject(quick, samand);

        // Changing objects attributes from outside
//        manipulatingAttributes(quick, samand);

//------------------------------------------------
//        Annotations

        // show what is annotations
        definingAnnotations();

        //  Custom annotations
        Car mazda = new Car();

        mazda.start();

//------------------------------------------------
//        AOP Cross Cutting Concern

    }

    private static void definingAnnotations() {

        //  start with @ then <name> (inputs)
        // @interface Name used like @Name

        //  Standard annotations by java used in code
        //     - override
        //     - suppress warnings
        //     - deprecated

        //  Standard annotations by java used in annotations
        //     - Target
        //     - Retention
        //     - Inherited
        //     - Documented

        // Custom annotations

        @SuppressWarnings("Not usable car")
        Car pride = new Car();

        Annotation[] annotations = pride.getClass().getAnnotations();
        System.out.println(annotations.length);

        for(Field field : pride.getClass().getDeclaredFields()) {
            for(Annotation annotation : field.getAnnotations()) {
                System.out.println(annotation);
            }
        }

        for(Method method : pride.getClass().getDeclaredMethods()) {
            if(method.isAnnotationPresent(LogStarting.class)) {
                for(int i = method.getAnnotation(LogStarting.class).timeToStart(); i>0 ; i--) {
                    System.out.println(i);
                }
                System.out.println("Logging the method call for : " + method.getName() + " at " + method.getAnnotation(LogStarting.class).logTime());
            }
        }

    }


    private static void manipulatingAttributes(Car quick, Car samand) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {

        Class carClass = Car.class;
        Field height = carClass.getField("height"); // field should be public => encapsulation
        Field weightDeclared = carClass.getDeclaredField("weight"); // bypass the encapsulation
        System.out.println(height.getType()); // simple name
        height.set(quick, 180);
        System.out.println(quick.getName() + " height is : " + quick.getHeight() + " cm");
        System.out.println(samand.getName() + " height is : " + samand.getHeight() + " cm");

        Method carStart = carClass.getMethod("start", null);
        carStart.invoke(quick);

        Method carCheck = carClass.getMethod("checkInitials", null); // solved problem in java 17
        carCheck.setAccessible(true);
    }












    private static void loadClassObject(Car quick, Car samand) throws ClassNotFoundException {

        Class carClassByClass = Car.class;
        Class carClassByName = Class.forName("Car");
        Class carClassByInstance = quick.getClass();

        if(carClassByName.equals(carClassByInstance)) { // instance of
            System.out.println("Same class object");
        }

        if(samand.getClass().equals(carClassByInstance)) {
            System.out.println("Same class object");
        }

        Field[] carFields = carClassByClass.getFields();
        Field[] carDeclaredFields = carClassByClass.getDeclaredFields();
        Method[] carMethods = carClassByClass.getMethods();
        Constructor[] carConstructors = carClassByInstance.getConstructors();
        Annotation[] carAnnotations = carClassByInstance.getAnnotations();

        System.out.println(carAnnotations.length);
        System.out.println(carFields[0].getAnnotations().length);
        System.out.println();
    }











    private static void dynamicLoadingExample() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        boolean test = false; // at RUN-TIME
//        var x = test ? "Okay" : 10;

        System.out.println("Write the name of method you want to run: ");
        Arrays.stream(Car.class.getMethods()).filter(m -> m.getName().startsWith("m")).map(m -> m.getName()).forEach(System.out::println);
        Scanner in = new Scanner(System.in);
        String methodName = in.nextLine();
        Method method = Car.class.getMethod(methodName);
        method.invoke(Car.class.newInstance());
    }
}
