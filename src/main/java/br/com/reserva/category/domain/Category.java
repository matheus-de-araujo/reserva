package br.com.reserva.category.domain;

import javax.persistence.*;

import br.com.reserva.room.domain.Room;
import br.com.reserva.material.domain.Material;
import br.com.reserva.equipment.domain.Equipment;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Room> room;

    @OneToMany(mappedBy = "category")
    private List<Material> material;

    @OneToMany(mappedBy = "category")
    private List<Equipment> equipment;
    
    public Long getId() {
        return id;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public List<Material> getMaterial() {
        return material;
    }

    public void setMaterial(List<Material> material) {
        this.material = material;
    }

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}