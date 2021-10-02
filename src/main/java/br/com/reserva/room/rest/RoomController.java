package br.com.reserva.room.rest;

import br.com.reserva.room.domain.Room;
import br.com.reserva.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequestMapping(path = "rooms")
public class RoomController {

    private final RoomService service;

    @Autowired
    public RoomController(RoomService roomService) {
        this.service = roomService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody ResponseEntity<?> findAllRoom(){
        try {
            Iterable<Room> rooms = service.findAll();
            return ResponseEntity.ok(rooms);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity<?> saveRoom(@RequestBody Room room){
        try {
            return service.save(room);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping(path = "/edit/{id}")
    public @ResponseBody ResponseEntity<?> update(@RequestBody Room room, @PathVariable(name = "id") Long id){
        try {
            return service.update(room, id);
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

    @PutMapping(path = "/{roomId}/categories/{categoryId}")
    public @ResponseBody ResponseEntity<?> AssignCategoryToRoom(
            @PathVariable Long roomId, @PathVariable Long categoryId)
        {
        try {
            return service.assignCategory(roomId, categoryId);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
