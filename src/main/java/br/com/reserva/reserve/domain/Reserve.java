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
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Reserve implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
  
    private LocalDate booking_date;
    private LocalTime booking_hour;
    private boolean retired;
    private boolean expired;

    @JsonIgnore
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToOne (cascade = CascadeType.ALL)
    private Equipment equipment;

    @OneToOne (cascade = CascadeType.ALL)
    private Material material;
    private Long qty_material;

    @OneToOne (cascade = CascadeType.ALL)
    private Room room;


    public Room getRoom() {
        return room;
    }

    public Long getQty_material() {
        return qty_material;
    }

    public void setQty_material(Long qty_material) {
        this.qty_material = qty_material;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public LocalTime getBooking_hour() {
        return booking_hour;
    }

    public void setBooking_hour(LocalTime booking_hour) {
        this.booking_hour = booking_hour;
    }

    public LocalDate getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(LocalDate booking_date) {
        this.booking_date = booking_date;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}