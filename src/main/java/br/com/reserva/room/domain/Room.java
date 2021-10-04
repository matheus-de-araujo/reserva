package br.com.reserva.room.domain;

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
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long room_number;
    private long floor;
    private String type;

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

    public long getRoom_number() {
        return room_number;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRoom_number(long room_number) {
        this.room_number = room_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}