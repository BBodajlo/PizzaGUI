package com.example.project4;

import org.junit.Test;

import static org.junit.Assert.*;

// JUnit test class for the price() method in BuildYourOwn Class.
// Ensures the price() method performs properly with different numbers of toppings and different sizes.
// Prices of a build your own pizza are Small: $8.99, Medium: $ 10.99, Large: $12.99;
// Adding $1.59 for each additional topping.
// @author Blake Bodajlo, Stanley Jiang

public class BuildYourOwnTest {

        @Test
        public void price_of_a_medium_build_Your_Own_Pizza_No_Toppings() {
            PizzaFactory pizzaFactory = new NYPizza();
            Pizza custom = pizzaFactory.createBuildYourOwn();
            //Create a medium size build your own pizza without any toppings
            custom.setSize(Size.Medium);
            //Medium size build your own pizza is $10.99
            assertEquals(10.99, custom.price(), 0.1);
        }

        @Test
        public void price_of_a_small_build_Your_Own_Pizza_No_Toppings() {
            PizzaFactory pizzaFactory = new NYPizza();
            Pizza custom = pizzaFactory.createBuildYourOwn();
            //Create a small size build your own pizza without any toppings
            custom.setSize(Size.Medium);
            //Small size build your own pizza is $8.99
            assertEquals(8.99, custom.price(), 0.1);
        }

        @Test
        public void price_of_a_large_build_Your_Own_Pizza_No_Toppings() {
            PizzaFactory pizzaFactory = new NYPizza();
            Pizza custom = pizzaFactory.createBuildYourOwn();
            //Create a large size build your own pizza without any toppings
            custom.setSize(Size.Medium);
            //Large size build your own pizza is $12.99
            assertEquals(12.99, custom.price(), 0.1);
        }

        @Test
        public void price_of_a_medium_build_Your_Own_Pizza_with_3_toppings() {
            PizzaFactory pizzaFactory = new NYPizza();
            Pizza custom = pizzaFactory.createBuildYourOwn();
            //Create a medium size build your own pizza that is $10.99
            custom.setSize(Size.Medium);
            //Each topping is an additional $1.59
            custom.add(Topping.Sausage);
            custom.add(Topping.Beef);
            custom.add(Topping.Ham);

            //Total should be 10.99 + (3*1.59) = $15.76
            assertEquals(15.75, custom.price(), 0.1);
        }

        @Test
        public void price_of_a_large_build_Your_Own_Pizza_with_1_toppings() {
            PizzaFactory pizzaFactory = new NYPizza();
            Pizza custom = pizzaFactory.createBuildYourOwn();
            //Create a Large size build your own pizza that is $12.99
            custom.setSize(Size.Large);
            //Each topping is an additional $1.59
            custom.add(Topping.Sausage);


            //Total should be 12.99 + 1.59 =$14.58
            assertEquals(14.58, custom.price(), 0.1);

        }

        @Test
        public void price_of_a_small_build_Your_Own_Pizza_with_5_toppings() {
            PizzaFactory pizzaFactory = new NYPizza();
            Pizza custom = pizzaFactory.createBuildYourOwn();
            //Create a small size build your own pizza that is $8.99
            custom.setSize(Size.Small);
            //Each topping is an additional $1.59
            custom.add(Topping.Sausage);
            custom.add(Topping.Beef);
            custom.add(Topping.Ham);
            custom.add(Topping.Basil);
            custom.add(Topping.Cheddar);
            //Total should be 8.99 + (5*1.59) = $16.94
            assertEquals(16.94, custom.price(), 0.1);
        }
    }