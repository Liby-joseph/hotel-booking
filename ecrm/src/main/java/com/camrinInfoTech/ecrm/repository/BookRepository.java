package com.camrinInfoTech.ecrm.repository;

import com.camrinInfoTech.ecrm.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
