package com.boxing.shop.react.repository;

import com.boxing.shop.react.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserAddressRepository extends JpaRepository<UserAddress, String> {

    @Query(value = "SELECT * FROM ADDRESSES WHERE USER_ID=?1", nativeQuery = true)
    List<UserAddress> findAllById(Long userId);
}
