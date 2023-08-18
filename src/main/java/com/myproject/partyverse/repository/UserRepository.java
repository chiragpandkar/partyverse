package com.myproject.partyverse.repository;

import com.myproject.partyverse.dbos.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDbo, Long> {

  UserDbo findByEmailOrUsername(String email, String username);
}
