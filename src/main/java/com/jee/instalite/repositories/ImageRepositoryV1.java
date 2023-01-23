package com.jee.instalite.repositories;

import com.jee.instalite.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepositoryV1 extends JpaRepository<Image,Long> {

    Optional<Image> findByName(String fileName);

   /* List<Image> findByUserId(Long userId);*/
}
