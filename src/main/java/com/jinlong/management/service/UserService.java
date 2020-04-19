package com.jinlong.management.service;


import com.jinlong.management.domain.User;
import com.jinlong.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  public List<String> getAllCellphoneNumbers() {
    return userRepository.findAllCellphoneNumbers();
  }

  public Page<User> getUserList(String cellphoneNumber, Pageable pageable) {
    if (cellphoneNumber != null && cellphoneNumber != "") {
      return userRepository.findUserByCellphoneNumber(cellphoneNumber, pageable);
    }
    return userRepository.findAll(pageable);
  }

  public User createOrUpdateUser(User user) {
    return userRepository.save(user);
  }

  public void createUserByBatch(List<User> users){
    users.forEach(user -> userRepository.save(user));
  }

  public void deleteUserById(Long id){
    userRepository.deleteById(id);
  }
}
