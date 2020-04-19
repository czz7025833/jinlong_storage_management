package com.jinlong.management.controllers.rest;


import com.jinlong.management.domain.User;
import com.jinlong.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {
  private final int DEFAULT_PAGE_SIZE = 6;

  private final String ASC = "asc";

  @Autowired
  private UserService userService;

  @GetMapping()
  @ResponseBody
  public ResponseEntity<List<User>> getUsers(
          @RequestParam(value = "cellphoneNumber", required = false) String cellphoneNumber,
          @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE + "") int size, @RequestParam(value = "sort", defaultValue = "points") String sortField
          , @RequestParam(value = "sortOrder", defaultValue = ASC) String sortOrder) {
    Sort.Order order = new Sort.Order(sortOrder.equalsIgnoreCase(ASC) ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
    Pageable pageable = PageRequest.of(page, size == 0 ? DEFAULT_PAGE_SIZE : size, Sort.by(order));
    Page<User> users = userService.getUserList(cellphoneNumber, pageable);
    HttpHeaders headers = new HttpHeaders();
    headers.add("pagetotal", String.valueOf(users.getTotalElements()));
    headers.add("pagesize", String.valueOf(size));
    return new ResponseEntity<>(users.getContent(), headers, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @ResponseBody
  public User getUserById(@PathVariable(value = "id") Long id) {
    return userService.getUserById(id).orElseThrow(() -> new RuntimeException("user not exists"));
  }

  @GetMapping("/cellphone-numbers")
  @ResponseBody
  public List<String> getAllCellphoneNumbers() {
    return userService.getAllCellphoneNumbers();
  }

  @PostMapping()
  public void createUserByBatch(@RequestBody List<User> users) {
    userService.createUserByBatch(users);
  }

  @PostMapping("/{id}")
  public void updateUserById(@RequestBody User user) {
    userService.createOrUpdateUser(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUserById(@PathVariable(value = "id") Long id) {
    userService.deleteUserById(id);
  }

}
