package com.example.project4;

import java.util.ArrayList;

/**
 * Creates an order which consists of different pizzas and a unique order number
 * Pizzas can be added and removed from an order
 * Orders can print the contents of itself
 * @author Blake Bodajlo, Stanley Jiang
 */
public class Order implements Customizable{

    private int orderNumber;

    private ArrayList<Pizza> PizzaList;

    private String orderMessage;

    /**
     * Creates an order by initializing the list of pizzas and an order number
     * @param orderNum Comes from the StoreOrder class which creates unique numbers for the next
     *                 order to be stored. There is always be a StoreOrders object instantiated
     *                 as orders will always want to be stored
     */
    public Order(int orderNum)
    {
        PizzaList = new ArrayList<Pizza>();
        orderNumber = orderNum;
    }


    /**
     * Prints the contents of the current order
     * The contents are the pizzas themselves including the type, crust, size, toppings, and price
     * @return String containing the details of the order
     */
    @Override
    public String toString()
    {
        orderMessage = "";
        for(Pizza p : PizzaList)
        {
            orderMessage+= String.format("%s: %s, %s, Toppings: ",p.getClass().getSimpleName(),
                    p.getCrust(),
                    p.getSize());
            for(Topping t : p.getToppings())
            {
                orderMessage += t + ", ";
            }
            orderMessage += "Price: " + p.price() + "\n";
        }
        return orderMessage;
    }

    /**
     * Adds a pizza object to the current order
     * @param obj Any instance of a pizza object
     * @return True if the pizza was added. False if the pizza was not added
     */
    @Override
    public boolean add(Object obj) {
        Pizza pizzaToAdd = (Pizza)obj;

        if(pizzaToAdd != null)
        {
            PizzaList.add(pizzaToAdd);
            return true;


        }


        return false;
    }

    /**
     * Removes a pizza from the current order
     * @param obj Any instance of a pizza object
     * @return True if pizza was removes. False is pizza was not removed
     */
    @Override
    public boolean remove(Object obj) {
        Pizza pizzaToRemove = (Pizza)obj;

        if(pizzaToRemove != null && PizzaList.contains(pizzaToRemove))
        {
            PizzaList.remove(pizzaToRemove);
            return true;
        }

        return false;
    }

    /**
     * Sets the order number
     * Mainly used when store order is altered
     * @param i Number representing the updated order number from StoreOrder
     */
    public void setOrderNumber(int i)
    {
        orderNumber = i;
    }

    /**
     * Gets the current order's number
     * @return Integer representing the current order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }
}
