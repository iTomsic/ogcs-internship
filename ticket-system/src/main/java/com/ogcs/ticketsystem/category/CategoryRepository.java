package com.ogcs.ticketsystem.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByIdAndActivityStatusTrue(Integer id);

}
