package com.jinlong.management.repository;


import com.jinlong.management.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

  @Query("select distinct cellphoneNumber from User")
  List<String> findAllCellphoneNumbers();

  Page<User> findUserByCellphoneNumber(String cellphoneNumber, Pageable pageable);
}
