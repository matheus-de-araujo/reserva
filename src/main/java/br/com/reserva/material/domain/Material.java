package br.com.reserva.material.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reserva.reserve.domain.Reserve;
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
    private Long available_qty;
    private Long reserved_qty;
    private Long total_qty;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;

    @JsonIgnore
    @OneToOne (cascade = CascadeType.ALL)
    private Reserve reserve;
    
    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    public String getName() {
        return name;
    }
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getTotal_qty() {
        return total_qty;
    }

    public void setTotal_qty(Long total_qty) {
        this.total_qty = total_qty;
    }

    public Long getReserved_qty() {
        return reserved_qty;
    }

    public void setReserved_qty(Long reserved_qty) {
        this.reserved_qty = reserved_qty;
    }

    public Long getAvailable_qty() {
        return available_qty;
    }

    public void setAvailable_qty(Long available_qty) {
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