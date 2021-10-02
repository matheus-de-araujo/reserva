package br.com.reserva.material.domain;

import javax.persistence.*;

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

    public String getName() {
        return name;
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