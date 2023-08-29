package com.picpaysimplifiedproject.picpaysimplifiedproject.services;

import com.picpaysimplifiedproject.picpaysimplifiedproject.domain.user.User;
import com.picpaysimplifiedproject.picpaysimplifiedproject.domain.user.UserType;
import com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.input.UserInputDTO;
import com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.output.UserOutputDTO;
import com.picpaysimplifiedproject.picpaysimplifiedproject.exception.BusinessException;
import com.picpaysimplifiedproject.picpaysimplifiedproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws BusinessException {
        if(sender.getUserType() == UserType.SELLER) {
            throw new BusinessException("Invalid user type");
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            throw new BusinessException("Insufficient funds");
        }
    }

    public User findUserById(Long id) throws BusinessException {
        return this.repository.findUserById(id).orElseThrow(() ->  new BusinessException("User not found"));
    }

    public void save(User user) {
        this.repository.save(user);
    }

    public UserOutputDTO createNewUser(UserInputDTO userData) {
        User newUser = User.of(userData);
        repository.save(newUser);
        return getOutputUser(newUser);
    }

    private static UserOutputDTO getOutputUser(User newUser) {
        return new UserOutputDTO(
                newUser.getId(),
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getDocument(),
                newUser.getEmail(),
                newUser.getBalance());
    }

    public List<UserOutputDTO> getAll() {
        return repository.findAll().stream()
                .map(UserService::getOutputUser)
                .toList();
    }

}
