package br.com.reserva.material.domain;

import javax.persistence.*;

import br.com.reserva.category.domain.Category;
import lombok.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Material implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private float available_qty;
    private float reserved_qty;
    private float total_qty;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;

    public String getName() {
        return name;
    }
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getTotal_qty() {
        return total_qty;
    }

    public void setTotal_qty(float total_qty) {
        this.total_qty = total_qty;
    }

    public float getReserved_qty() {
        return reserved_qty;
    }

    public void setReserved_qty(float reserved_qty) {
        this.reserved_qty = reserved_qty;
    }

    public float getAvailable_qty() {
        return available_qty;
    }

    public void setAvailable_qty(float available_qty) {
        this.available_qty = available_qty;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}