package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_purchase_order_lines")
public class OrderLine extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "item_fk")
    public Item item;

    @Column(nullable = false)
    public int quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_order_fk")
    public PurchaseOrder purchaseOrder;

    @Column(name="created_date", nullable = false)
    public Instant createdDate = Instant.now();
}
