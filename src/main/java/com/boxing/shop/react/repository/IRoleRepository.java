package com.boxing.shop.react.repository;

import com.boxing.shop.react.entity.ApplicationUser;
import com.boxing.shop.react.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByAuthority(String authority);
}
