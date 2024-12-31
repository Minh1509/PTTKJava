package org.example.projectfinal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true) // unique: đảm bảo mỗi user chỉ có một cart
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true) //orphanRemoval: sử dụng để khi xóa 1 sản pẩm thì nó cx xóa luôn trong db
    private List<CartItem> items = new ArrayList<>();


    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
    }


    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
    }


    public void clearCart() {
        items.clear();
    }
}
