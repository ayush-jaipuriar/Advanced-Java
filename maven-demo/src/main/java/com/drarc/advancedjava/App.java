package com.drarc.advancedjava;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * This class demonstrates various Java concepts such as custom annotations, reflection, and property file reading.
 */

// Custom annotation to mark a class as configurable
@Configurable(config = "Advanced Java")
class MyClass {
    int score = 0; // Instance variable

    // Custom annotation to describe a field
    @Field(description = "Name")
    static String name = "Ayush Jaipuriar"; // Static variable

    // Private method to demonstrate reflection
    private void print() {
        System.out.println("printed something from private method : print");
    }

    // Public method to demonstrate reflection
    public int sum(int a, int b) {
        return a + b;
    }

    // Public static method to demonstrate reflection
    public static double multiply(double a, double b) {
        return a * b;
    }
}

public class App {
    public static void main(String[] args) {
        // Load properties from a file
        Properties properties = new Properties();
        String propertiesConfigPath = "/config.properties";
        try {
            properties.load(App.class.getResourceAsStream(propertiesConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name = properties.getProperty("name");
        System.out.println("Name : " + name);

        // Get system property
        String env = System.getProperty("env");
        System.out.println(env);

        // Demonstrate reflection API use cases
        try {
            // Reflect the MyClass class
            Class<?> reflectedClass = Class.forName("com.drarc.advancedjava.MyClass");
            Object reflectedClassObj = reflectedClass.getDeclaredConstructor().newInstance();

            // Access and invoke a private method
            Method printMethod = reflectedClass.getDeclaredMethod("print");
            printMethod.setAccessible(true);
            printMethod.invoke(reflectedClassObj);

            // Access and invoke a public method
            Method sumMethod = reflectedClass.getDeclaredMethod("sum", int.class, int.class);
            int sum = (int) sumMethod.invoke(reflectedClassObj, 1, 2);
            System.out.println(sum);

            // Access and invoke a public static method
            Method multiplyMethod = reflectedClass.getDeclaredMethod("multiply", double.class, double.class);
            double product = (double) multiplyMethod.invoke(null, 6.0, 9.0);
            System.out.println(product);

            // Access and read a custom annotation on a field
            java.lang.reflect.Field field = reflectedClass.getDeclaredField("name");
            Field fieldAnnotation = field.getAnnotation(Field.class);
            if (fieldAnnotation != null) {
                System.out.println("Field Annotation Description = " + fieldAnnotation.description());
            }
            
            // Access and read a custom annotation on a class
            Configurable annotation = reflectedClass.getAnnotation(Configurable.class);
            if (annotation != null) {
                System.out.println("Class annotation : " + annotation.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}