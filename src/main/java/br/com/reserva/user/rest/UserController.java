package br.com.reserva.user.rest;

import br.com.reserva.user.domain.User;
import br.com.reserva.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequestMapping(path = "users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService userService) {
        this.service = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public @ResponseBody ResponseEntity<?> findAllUser(){
        try {
            Iterable<User> users = service.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity<?> saveUser(@RequestBody User user){
        try {
            return service.save(user);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping(path = "/edit/{id}")
    public @ResponseBody ResponseEntity<?> update(@RequestBody User user, @PathVariable(name = "id") Long id){
        try {
            return service.update(user, id);
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
