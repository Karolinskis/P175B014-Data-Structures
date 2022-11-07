package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.Parsable;

public class CustomInt implements Parsable<CustomInt> {

    public Integer value;
    public CustomInt(String value) {
        parse(value);
    }

    public CustomInt(int value) {
        this.value = value;
    }

    @Override
    public void parse(String dataString) {
        value = Integer.parseInt(dataString);
    }

    @Override
    public int compareTo(CustomInt o) {
        return Integer.compare(value, o.value);
    }

    public String toString() {
        return value.toString();
    }
}
