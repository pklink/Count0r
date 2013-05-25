package net.einself.countr;

/**
 * Created by pierre on 24.05.13.
 */
public class Item {

    private String name = "New Counter";

    private Integer count = 0;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getCount() {
        return count;
    }


    public void setCount(Integer count) {
        this.count = count;
    }


    public void increment() {
        count++;
    }


    public void decrement() {
        count--;
    }

}
