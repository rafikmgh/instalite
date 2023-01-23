
package com.jee.instalite.repositories;

import com.jee.instalite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     User findByEmail(String email);

     User findByEmailAndPassword(String email , String password);

     List<User> findAll();

     Optional<User> findById(Long id);

     void deleteById(Long id);



}

