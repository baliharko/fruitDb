package com.example.fruitsdbdemo.controllers;

import com.example.fruitsdbdemo.models.Fruit;
import com.example.fruitsdbdemo.repositories.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fruit")
public class FruitController {

    @Autowired
    FruitRepository fruitRepository;

    @GetMapping("/all")
    public List<Fruit> getAllFruits() {
        return (List<Fruit>) fruitRepository.findAll();
    }

    @GetMapping("/add")
    public String addFruit(@RequestParam String name, @RequestParam String color) {

        if (!fruitRepository.existsByNameAndColor(name, color)) {
            fruitRepository.save(new Fruit(name, color));
            return "Saved " + color + " " + name + " to fruit database";
        }

        return color + " " + name +  " is already in database";
    }

    @GetMapping("/delete/byNameAndColor")
    public String deleteFruitByNameAndColor(@RequestParam String name, @RequestParam String color) {

        if (fruitRepository.existsByNameAndColor(name, color)) {
            fruitRepository.deleteByNameAndColor(name, color);
            return "Removed " + color + " " + name + " from database";
        }

        return color + " " + name + " is not in database";
    }

    @GetMapping("/delete/byColor")
    public String deleteFruitByColor(@RequestParam String color) {
       fruitRepository.deleteByColor(color);
       return "Deleted all " + color + " fruits from database";
    }
}
