package br.com.reserva.reserve.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reserva.user.domain.User;
import br.com.reserva.equipment.domain.Equipment;
import br.com.reserva.material.domain.Material;
import br.com.reserva.room.domain.Room;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Reserve implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate booking_date;

    @JsonIgnore
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToOne (cascade = CascadeType.ALL)
    private Equipment equipment;

    @OneToOne (cascade = CascadeType.ALL)
    private Material material;

    @OneToOne (cascade = CascadeType.ALL)
    private Room room;

    public LocalDate getBooking_date() {
        return booking_date;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBooking_date(LocalDate booking_date) {
        this.booking_date = booking_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}