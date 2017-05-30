package entity;

import javax.persistence.*;

/**
 * Created by Illko on 26.05.2017.
 */

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String pName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    public Product() {

    }

    public Product(String pName, User user) {
        this.pName = pName;
        this.user = user;
    }

    public Product(String pName) {
        this.pName = pName;
    }

    public User setUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", pName='" + pName + '\'' +
                '}';
    }
}
