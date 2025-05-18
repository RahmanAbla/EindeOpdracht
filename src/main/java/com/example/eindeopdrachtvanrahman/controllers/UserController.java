package com.example.eindeopdrachtvanrahman.controllers;


import com.example.eindeopdrachtvanrahman.Services.UserServise;
import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.dto.UserDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {
    private final UserServise userServise;

    public UserController(UserServise userServise) {
        this.userServise = userServise;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO>userDTOS=userServise.getUsers();
        return ResponseEntity.ok().body(userDTOS);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("username")String username){
        UserDTO optionalUser=userServise.getUser(username);
        return ResponseEntity.ok().body(optionalUser);
    }

    @PostMapping(value = "klant")
    public ResponseEntity<UserDTO> createKlant(@RequestBody UserDTO dto){;
        // Let op: het password van een nieuwe gebruiker wordt in deze code nog niet encrypted opgeslagen.
        // Je kan dus (nog) niet inloggen met een nieuwe user.
        String newUserName=userServise.createUser(dto);
        userServise.addAuthority(newUserName,"ROLE_CLIENT");
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUserName).toUri();
        return ResponseEntity.created(location).build();
    }


    @PostMapping(value = "admin")
    public ResponseEntity<UserDTO> createKMechanic(@RequestBody UserDTO dto){;
        // Let op: het password van een nieuwe gebruiker wordt in deze code nog niet encrypted opgeslagen.
        // Je kan dus (nog) niet inloggen met een nieuwe user.
        String newUserName=userServise.createUser(dto);
        userServise.addAuthority(newUserName,"ROLE_MECHANIC");
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUserName).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping(value = "/{username}")
    public ResponseEntity<UserDTO> updateKlant(@PathVariable("username")String username,@RequestBody UserDTO dto) throws RecordNotFoundException {
        userServise.updateUser(username,dto);
        return ResponseEntity.noContent().build();
    }

   @DeleteMapping(value = "/{username}")
   public ResponseEntity<Object> deleteKlant(@PathVariable("username")String username){
        userServise.deleteUser(username);
        return ResponseEntity.noContent().build();
   }

@GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username")String username){
        return ResponseEntity.ok().body(userServise.getAuthorities(username));
}

@PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username")String username, @RequestBody Map<String,Object>fields) throws BadRequestException {
   try {
       String authorityName=(String) fields.get("authority");
       userServise.addAuthority(username,authorityName);
       return ResponseEntity.noContent().build();
   } catch (Exception ex){
       throw new BadRequestException();
   }
}

@DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username")String username,@PathVariable("authority")String authority){
        userServise.removeAuthority(username,authority);
        return ResponseEntity.noContent().build();
}

}





