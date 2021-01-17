package com.sc.courseProject.repositories;

import com.sc.courseProject.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("SELECT a FROM Animal a WHERE lower(a.name) = :name")
    Optional<Animal> findAnimalByName(String name);

    @Query("SELECT a FROM Animal a WHERE a.serial_n = :serial_n")
    Optional<Animal> findAnimalBySerialN(Integer serial_n);

    @Query("SELECT a FROM Animal a WHERE a.Id = :id")
    Animal findAnimalById(Long id);

    @Query("SELECT a FROM Animal a WHERE a.type = :type")
    Optional<Animal> findAnimalByType(String type);

    @Query("SELECT a FROM Animal a "+
            "WHERE lower(a.name) "+
            "LIKE :#{#name==null || #name.isEmpty()? '%' : '%'+#name+'%'} ")
    Page<Animal> findPageAnimals(Pageable pageable, String name);
}