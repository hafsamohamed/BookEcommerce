package gov.iti.jets.presentation.dtos;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement
public class OrderDto implements Serializable {
    private int id;
    private double totalPrice;
    private LocalDate orderTime;

    public OrderDto() {
    }

    public OrderDto(int id, double totalPrice, LocalDate orderTime) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDate orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "OrderDto [id=" + id + ", orderTime=" + orderTime + ", totalPrice=" + totalPrice + "]";
    }

}
