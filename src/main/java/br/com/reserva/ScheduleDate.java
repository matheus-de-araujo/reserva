package br.com.reserva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.reserva.reserve.service.ReserveService;
import br.com.reserva.reserve.domain.Reserve;

@Component
public class ScheduleDate {
    @Autowired
    ReserveService reserveService;

    @Scheduled(fixedDelay = 10000)
    public void executar() {

        Iterable<Reserve> reserves = reserveService.findAll();

        reserves.forEach((reserve) -> reserveService.scheduleTime(reserve));
    }
}