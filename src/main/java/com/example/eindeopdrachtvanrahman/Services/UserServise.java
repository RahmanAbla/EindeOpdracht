package com.example.eindeopdrachtvanrahman.Services;
import com.example.eindeopdrachtvanrahman.dto.RecordNotFoundException;
import com.example.eindeopdrachtvanrahman.dto.UserDTO;
import com.example.eindeopdrachtvanrahman.models.Authority;
import com.example.eindeopdrachtvanrahman.models.User;
import com.example.eindeopdrachtvanrahman.repository.UserRepository;
import com.example.eindeopdrachtvanrahman.utils.RandomStringGenerator;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServise {
    private final UserRepository userRepository;

    public <UserDto> UserServise(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<UserDTO>getUsers(){
        List<UserDTO>collection=new ArrayList<>();
        List<User>list=userRepository.findAll();
        for (User user:list){
            collection.add(fromUser(user));
        }
        return collection;
    }
    public UserDTO getUser(String username){
        UserDTO dto=new UserDTO();
        Optional<User>user=userRepository.findById(String.valueOf(username));
        if (user.isPresent()){
            dto=fromUser(user.get());
        }else { throw new UsernameNotFoundException(username);
        }
        return dto;
    }
    public boolean userExists(String username){
        return userRepository.existsById(String.valueOf(username));
    }
public String createUser(UserDTO userDTO){
        String randomString= RandomStringGenerator.generateAlphaNumeric(20);
        userDTO.setApikey(randomString);
        User newUser=userRepository.save(toUser(userDTO));
        return newUser.getUsername();
}
public void deleteUser(String username){
        userRepository.deleteById(String.valueOf(username));
}
public void updateUser(String username, UserDTO newUser) throws RecordNotFoundException {
        if (!userRepository.existsById(String.valueOf(username)) )throw new RecordNotFoundException();
        User user=userRepository.findById(String.valueOf(username)).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }
    public Set<Authority>getAuthorities(String username){
        if (!userRepository.existsById(String.valueOf(username)))throw new UsernameNotFoundException(username);
        User user=userRepository.findById(String.valueOf(username)).get();
        UserDTO userDTO=fromUser(user);
       return userDTO.getAuthorities();
    }
    public void addAuthority(String username,String authority){
        if (!userRepository.existsById(String.valueOf(username)))throw new UsernameNotFoundException(username);
        User user=userRepository.findById(String.valueOf(username)).get();
        user.addAuthority((new Authority(username,authority)));
        userRepository.save(user);
    }
    public void removeAuthority(String username, String authority ){
        if (!userRepository.existsById(String.valueOf(username)))throw new UsernameNotFoundException(username);
        User user=userRepository.findById(String.valueOf(username)).get();
        Authority authorityToRemove=user.getAuthorities().stream().filter((a)->
                a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }
    public static UserDTO fromUser(User user){
        var dto=new UserDTO();
        dto.username=user.getUsername();
        dto.password=user.getPassword();
        dto.enabled=user.isEnabled();
        dto.apikey=user.getApikey();
        dto.email=user.getEmail();
        dto.authorities=user.getAuthorities();
        return dto;
    }
    public User toUser(UserDTO userDTO){
        var user=new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEnabled(userDTO.getEnabled());
        user.setApikey(userDTO.getApikey());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}

















//@Service
//public class UserServise {
//    private final UserRepository userRepository;
//
//    public UserServise(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//public UserDTO addUser(UserDTO dto) {
//        User user=transferToUser(dto);
//        userRepository.save(user);
//    return  transferToDTO(user);
//}
//    public List<UserDTO> getAllUsers(){
//        List<User>userList=userRepository.findAll();
//        List<UserDTO>userDTOList=new ArrayList<>();
//        for (User user: userList){
//            UserDTO dto=transferToDTO(user);
//            userDTOList.add(dto);
//        }
//        return userDTOList;
//    }
//    public UserDTO getUserById(Long id) throws Exception {
//            Optional<User>userOptional =userRepository.findById(id);
//        if (userOptional.isPresent()){
//
//            User user1 = userOptional.get();
//            return transferToDTO(user1);
//        } else {
//            throw new Exception("no user found");
//        }
//    }
//
//    public User transferToUser(UserDTO dto) {
//        var user = new User();
//        user.setId(dto.getId());
//        user.setAge(dto.getAge());
////        user.setCarMechanic(dto.getCarMechanic());
////        user.setClient(dto.getClient());
//        user.setEmail(dto.getEmail());
//        user.setName(dto.getName());
////        user.setGarageReseptionist(dto.getGarageReceptionist());
//
//        return user;
//    }
//
//    public UserDTO transferToDTO(User user) {
//        UserDTO dto = new UserDTO();
//        dto.setId(user.getId());
//        dto.setAge(user.getAge());
////        dto.setCarMechanic(user.getCarMechanic());
////        dto.setClient(user.getClient());
////        dto.setClient(user.getClient());
//        dto.setName(user.getName());
////        dto.setGarageReceptionist(user.getGarageReseptionist());
//        return dto;
//    }
//
//    public UserDTO getUser(Long id) {
//        return null;
//    }
//
//    public UserDTO updateuser(Long id, UserDTO userDTO) throws RecordNotFoundException {
//
//        if (userRepository.findById(id).isPresent()) {
//
//            User user = userRepository.findById(id).get();
//
//            User user1 = transferToUser(userDTO);
//            user1.setId(user.getId());
//
//            userRepository.save(user1);
//
//            return transferToDTO(user1);
//
//        } else {
//
//            throw new RecordNotFoundException(" No found User");
//
//        }

//    }
//
//    public void deleteUser(@RequestBody Long id) {
//
//        userRepository.deleteById(id);
//
//    }
//}