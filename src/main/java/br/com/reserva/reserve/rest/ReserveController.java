package br.com.reserva.reserve.rest;

import br.com.reserva.reserve.domain.Reserve;
import br.com.reserva.reserve.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequestMapping(path = "reserves")
public class ReserveController {

    private final ReserveService service;

    @Autowired
    public ReserveController(ReserveService reserveService) {
        this.service = reserveService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody ResponseEntity<?> findAllReserve(){
        try {
            Iterable<Reserve> reserves = service.findAll();
            return ResponseEntity.ok(reserves);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity<?> saveReserve(@RequestBody Reserve reserve){
        try {
            return service.save(reserve);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping(path = "/edit/{id}")
    public @ResponseBody ResponseEntity<?> updateDate(@RequestBody Reserve reserve, @PathVariable(name = "id") Long id){
        try {
            return service.updateDate(reserve, id);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        try {
            return service.delete(id);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
