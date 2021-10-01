package br.com.reserva.reserve.domain;

import javax.persistence.*;

import br.com.reserva.user.domain.User;
import lombok.*;
import lombok.NoArgsConstructor;
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
    @ManyToOne
    private User user;

    public LocalDate getBooking_date() {
        return booking_date;
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
}