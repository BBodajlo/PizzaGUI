package com.example.project4;

/**
 * Template for creating different types of pizza.
 * @author Blake Bodajlo, Stanley Jiang
 */
public interface PizzaFactory {
    Pizza createDeluxe();
    Pizza createMeatzza();
    Pizza createBBQChicken();
    Pizza createBuildYourOwn();
}