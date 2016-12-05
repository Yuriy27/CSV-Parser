package com.github.yuriy27.csvparser.ex;

import com.github.yuriy27.csvparser.annot.Column;
import com.github.yuriy27.csvparser.annot.CsvEntity;

@CsvEntity(resource = "src/main/resources/or.csv")
public class Order {

    @Column(num = 1)
    private int price;

    @Column(num = 2)
    private char ch;

    public Order(int price, char ch) {
        this.price = price;
        this.ch = ch;
    }

    public Order() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (price != order.price) return false;
        return ch == order.ch;

    }

    @Override
    public int hashCode() {
        int result = price;
        result = 31 * result + (int) ch;
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                ", ch=" + ch +
                '}';
    }
}
