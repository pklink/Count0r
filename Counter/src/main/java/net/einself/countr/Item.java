package net.einself.countr;

/**
 * @author Pierre Klink <pierre@einself.net>
 */
public class Item {

    private String name = "New Counter";

    private Long count = 0l;


    public Item() {
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Long getCount() {
        return count;
    }


    public void setCount(Long count) {
        this.count = count;
    }


    public void increment() {
        count++;
    }


    public void decrement() {
        count--;
    }

}
