package com.hafiz.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Desc:
 * Created by hafiz.zhang on 2017/9/6.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
