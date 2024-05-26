package com.adv.adv.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;


  public Order() {
  }

  public Order(Long id, User user, Date orderDate, List<OrderDetails> orderDetails) {
    this.id = id;
    this.user = user;
    this.orderDate = orderDate;
    this.orderDetails = orderDetails;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getOrderDate() {
    return this.orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public List<OrderDetails> getOrderDetails() {
    return this.orderDetails;
  }

  public void setOrderDetails(List<OrderDetails> orderDetails) {
    this.orderDetails = orderDetails;
  }

  public Order id(Long id) {
    setId(id);
    return this;
  }

  public Order user(User user) {
    setUser(user);
    return this;
  }

  public Order orderDate(Date orderDate) {
    setOrderDate(orderDate);
    return this;
  }

  public Order orderDetails(List<OrderDetails> orderDetails) {
    setOrderDetails(orderDetails);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(user, order.user) && Objects.equals(orderDate, order.orderDate) && Objects.equals(orderDetails, order.orderDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, orderDate, orderDetails);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", user='" + getUser() + "'" +
      ", orderDate='" + getOrderDate() + "'" +
      ", orderDetails='" + getOrderDetails() + "'" +
      "}";
  }
    
}



