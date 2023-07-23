package net.planic.productdatabasebackend.persistence;

import jakarta.persistence.*;

@Entity(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;



    @Column(nullable = false)
    private Float price;

    public ProductEntity() {

    }
    public ProductEntity(String name, Float price){
        this.name = name;
        this.price = price;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }


}
