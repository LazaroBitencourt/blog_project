package blog_project.domain.controller;

import blog_project.domain.model.User;
import blog_project.domain.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

    @RestController
    @RequestMapping("/users")
    public class UserRestController {

        private final UserService userService;
        public UserRestController(UserService userService){
            this.userService = userService;
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> findById(@PathVariable Long id) {
            User user = userService.findById(id);
            return ResponseEntity.ok(user);
        }
        @GetMapping
        public ResponseEntity<Iterable<User>> findAll() {
            return ResponseEntity.ok(userService.findAll());
        }
        @PostMapping
        public ResponseEntity<User> createUser(@RequestBody User userToCreate) {
            var userCreated = userService.createUser(userToCreate);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userCreated.getId()).toUri();
            return ResponseEntity.created(location).body(userCreated);
        }
        @PutMapping("/{id}")
        public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User userUpdate){
            userService.updateUser(id, userUpdate);
        return ResponseEntity.ok(userUpdate);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
}
