package com.adv.adv.model;

import jakarta.persistence.*;
import java.util.Objects;


@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private double price;


  public OrderDetails() {
  }

  public OrderDetails(Long id, Order order, Product product, int quantity, double price) {
    this.id = id;
    this.order = order;
    this.product = product;
    this.quantity = quantity;
    this.price = price;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Order getOrder() {
    return this.order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Product getProduct() {
    return this.product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public OrderDetails id(Long id) {
    setId(id);
    return this;
  }

  public OrderDetails order(Order order) {
    setOrder(order);
    return this;
  }

  public OrderDetails product(Product product) {
    setProduct(product);
    return this;
  }

  public OrderDetails quantity(int quantity) {
    setQuantity(quantity);
    return this;
  }

  public OrderDetails price(double price) {
    setPrice(price);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderDetails)) {
            return false;
        }
        OrderDetails orderDetails = (OrderDetails) o;
        return Objects.equals(id, orderDetails.id) && Objects.equals(order, orderDetails.order) && Objects.equals(product, orderDetails.product) && quantity == orderDetails.quantity && price == orderDetails.price;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, order, product, quantity, price);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", order='" + getOrder() + "'" +
      ", product='" + getProduct() + "'" +
      ", quantity='" + getQuantity() + "'" +
      ", price='" + getPrice() + "'" +
      "}";
  }
  
}

