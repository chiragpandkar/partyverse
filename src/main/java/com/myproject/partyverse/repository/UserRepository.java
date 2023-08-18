package com.myproject.partyverse.repository;

import com.myproject.partyverse.dbos.UserDbo;
import com.myproject.partyverse.dos.UserDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDbo, Long> {

}
