package com.camrinInfoTech.ecrm.repository;

import com.camrinInfoTech.ecrm.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
