package com.dh.homework.bid.repository;

import com.dh.homework.bid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
