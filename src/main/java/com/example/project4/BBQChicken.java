package com.example.project4;

/**
 * Subclass of Pizza which creates a template for a BBQChicken pizza object
 * The toppings are filled out by the PizzaFactory class
 * This class initializes the instance variables inherited from pizza and creates a unique
 * price based on its instance
 * @author Blake Bodajlo, Stanley Jiang
 */
public class BBQChicken extends Pizza {

    /**
     * Constructor to initialize a BBQChicken object's size, crust, and toppings instance variables
     * @param c Crust is determined by the PizzaFactory class making the pizza; the parameter
     *          represents how the pizza factory wants to make the pizza's crust
     */
    public BBQChicken(Crust c)
    {
        super();
        this.initializeToppings();
        this.setCrust(c);
    }


    /**
     * Calculates the price of a BBQChicken pizza which has preset prices based on its size only
     * @return Price of the pizza determined by its size
     */
    @Override
    public double price() {
        double finalPrice = 0;

        assert(this.getSize() != null);

        if(getSize() == Size.Small)
        {
            finalPrice = 13.99;
        }
        else if(getSize() == Size.Medium)
        {
            finalPrice = 15.99;
        }
        else if(getSize() == Size.Large)
        {
            finalPrice = 17.99;
        }

        return finalPrice;
    }
}

