package blog_project.service.Implementation;

import blog_project.model.User;
import blog_project.repository.UserRepository;
import blog_project.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAll() {
        List<User> users = userRepository.findAll();
        if(users == null || users.isEmpty()){
            throw new EntityNotFoundException("No users were found.");
        }
        return users;
    }

    @Override
    public User findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user ID.");
        }
        return userRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("This user was not found. User ID: " + id));
    }

    @Override
    public User createUser(User user) {
        if(user.getId() != null && userRepository.existsById(user.getId()))
        {
            throw new EntityExistsException("This user already exists.");
        }
        return userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user ID.");
        }
        User foundUser = userRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("This user was not found. User ID: " + id));
        BeanUtils.copyProperties(user, foundUser, "id");
        userRepository.save(foundUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user ID.");
        }
        userRepository.deleteById(id);
    }
}
