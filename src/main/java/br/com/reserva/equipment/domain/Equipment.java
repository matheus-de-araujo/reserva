package br.com.reserva.equipment.domain;

import javax.persistence.*;

import br.com.reserva.category.domain.Category;
import lombok.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Equipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String status;
    private String model;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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