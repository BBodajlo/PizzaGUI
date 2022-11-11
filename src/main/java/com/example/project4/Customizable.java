package com.example.project4;

/**
 * Template for customizing an object that implements it based on its instance.
 * @param <E> The datatype that is being customized; for example toppings being added to a list
 * @author Blake Bodajlo, Stanley Jiang
 */
public interface Customizable<E> {
    boolean add(Object obj);
    boolean remove(Object obj);
}