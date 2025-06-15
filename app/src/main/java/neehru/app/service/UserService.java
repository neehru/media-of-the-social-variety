package neehru.app.service;

import neehru.app.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    public List<User> getAllUsers();
    public Optional<User> getUserById(Long id);
    public Optional<User> getUserByUsername(String username);
    public User saveUser(User user);
    public User updateUser(Long id, User updatedUser);
    public void deleteUser(Long id);
}
