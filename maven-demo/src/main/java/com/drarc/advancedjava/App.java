package com.drarc.advancedjava;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;


/**
 * Hello world!
 *
 */

@Configurable(config = "Advanced Java")
class MyClass {
	int score = 0;

	@Field(description = "Name")
	static String name  = "Ayush Jaipuriar";

	private void print() {
		System.out.println("printed something from private method : print");
	}

	public int sum(int a, int b) {
		return a + b;
	}

	public static double multiply(double a, double b) {
		return a * b;
	}
}

public class App {
	public static void main(String[] args) {
		// Getting Data From a Properties File
		Properties properties = new Properties();
		String propertiesConfigPath = "/config.properties";
		try {
			properties.load(App.class.getResourceAsStream(propertiesConfigPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String name = properties.getProperty("name");
		System.out.println("Name : " + name);

		String env = System.getProperty("env");
		System.out.println(env);

		// Reflection API Usecases
		try {
			Class<?> reflectedClass = Class.forName("com.drarc.advancedjava.MyClass");
			Object reflectedClassObj = reflectedClass.getDeclaredConstructor().newInstance();
			// Private method with no parameter and a void return type
			Method printMethod = reflectedClass.getDeclaredMethod("print");
			printMethod.setAccessible(true);
			printMethod.invoke(reflectedClassObj);

			// Public method with 2 parameters and an int return type
			Method sumMethod = reflectedClass.getDeclaredMethod("sum", int.class, int.class);
			int sum = (int) sumMethod.invoke(reflectedClassObj, 1, 2);
			System.out.println(sum);

			// Public static method with 2 double parameters and a double return type
			Method multiplyMethod = reflectedClass.getDeclaredMethod("multiply", double.class, double.class);
			double product = (double) multiplyMethod.invoke(null, 6.0, 9.0);
			System.out.println(product);

			// Using Reflection to get the custom annotation 'Field' property
			java.lang.reflect.Field field = reflectedClass.getDeclaredField("name");
			Field fieldAnnotation = field.getAnnotation(Field.class);
			if (fieldAnnotation != null) {
				System.out.println("Field Annotation Description = " + fieldAnnotation.description());
			}
			
			Configurable annotation = reflectedClass.getAnnotation(Configurable.class);
			if (annotation != null) {
				System.out.println(annotation.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
