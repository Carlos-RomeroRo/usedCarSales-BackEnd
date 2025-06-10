package com.example.usedCarSales.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column(nullable = false)
    private String name_user;

    @Column(nullable = false)
    private String email_user;

    @Column(nullable = false)
    private String password_user;
    
    
        @ManyToOne
        @JoinColumn(name = "id_role")
        private Role role_user;
    
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Car> Cars;

    @OneToOne (mappedBy = "user")
    private ShoppingCart shoppingCart;



}
