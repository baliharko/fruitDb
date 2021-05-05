package com.example.fruitsdbdemo.repositories;

import com.example.fruitsdbdemo.models.Fruit;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface FruitRepository extends CrudRepository<Fruit, Long> {

    List<Fruit> findByName(String name);
    List<Fruit> findByNameAndColor(String name, String color);
    List<Fruit> findByColor(String color);
    boolean existsByNameAndColor(String name, String color);

    @Transactional
    List<Fruit> deleteByColor(String color);

    @Transactional
    List<Fruit> deleteByName(String name);

    @Transactional
    List<Fruit> deleteByNameAndColor(String name, String color);
}
