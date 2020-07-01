package com.lambdaexpr;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Product{  
    int id;  
    String name;  
    float price;  
    public Product(int id, String name, float price) {  
        super();  
        this.id = id;  
        this.name = name;  
        this.price = price;  
    }  
}  

@FunctionalInterface // It is optional
interface SayableNoParam {
	public String say();
}

@FunctionalInterface // It is optional
interface SayableOneParam {
	public String say(String name);
}


@FunctionalInterface // It is optional
interface SayableMultiParam {
	int add(int a, int b);
}

public class LambdaExprDemo {

	public static void main(String[] args) {

//		Lambda expression with no parameter	
		SayableNoParam s = () -> {
			return "I have nothing to say.";
		};
		System.out.println(s.say());

//		Lambda expression with single parameter
		SayableOneParam s1 = (name) -> {
			return "Hello " + name;
		};
		System.out.println(s1.say("Doe"));

//		Lambda expression with multiple parameters and no return value
		SayableMultiParam s2 = (int a, int b) -> (a + b);
		System.out.println(s2.add(11, 21));		

//		Lambda expression with multiple parameters and return value
		SayableMultiParam s3 = (int a, int b) -> {
			return (a + b);
		};
		System.out.println(s3.add(101, 201));		

//		Thread Example without lambda  
        Runnable r1=new Runnable(){  
            public void run(){  
                System.out.println("Thread1 is running...");  
            }  
        };  
        Thread t1=new Thread(r1);  
        t1.start();  
        
//        Thread Example with lambda  
        Runnable r2=()->{  
                System.out.println("Thread2 is running...");  
        };  
        Thread t2=new Thread(r2);  
        t2.start();          

//		Without lambda - create Runnable and pass it to a Thread
        Thread t11 = new Thread (new Runnable(){        
            @Override
            public void run() {                
                System.out.println("Thread11 is running");
            }
        });
        t11.start();

//        Lambda syntax - without a Runnable
        Thread t22 = new Thread(() -> System.out.println("Thread22 is running"));
        t22.start();

        /*
         * ------------------
         * FILTER
         * ------------------
         */
        List<Product> list=new ArrayList<Product>();  
        list.add(new Product(1,"Samsung A5",17000f));  
        list.add(new Product(3,"Iphone 6S",65000f));  
        list.add(new Product(2,"Sony Xperia",25000f));  
        list.add(new Product(4,"Nokia Lumia",15000f));  
        list.add(new Product(5,"Redmi4 ",26000f));  
        list.add(new Product(6,"Lenevo Vibe",19000f));  
        
        // using lambda to filter data  
        Stream<Product> filtered_data = list.stream().filter(p -> p.price > 20000);  
          
        // using lambda to iterate through collection  
        filtered_data.forEach(  
                product -> System.out.println(product.name+": "+product.price)  
        );  
		
	}
}
