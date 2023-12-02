package blog_project.domain.service;

import blog_project.domain.model.User;

public interface UserService{

    Iterable<User> findAll();

    User findById(Long id);

    User createUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long id);
}
