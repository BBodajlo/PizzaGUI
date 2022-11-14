package com.example.project4;

public class StoreOrderController {


    private static StoreOrders storedOrders;




    public void initialize()
    {
        storedOrders = new StoreOrders();
    }
    public static StoreOrders getStoredOrders() {
        return storedOrders;
    }

    public static void initializeStoreOrder()
    {
        storedOrders = new StoreOrders();
    }

}
