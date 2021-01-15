package com.sc.courseProject.controllers;

import com.sc.courseProject.entities.Animal;
import com.sc.courseProject.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("animal")
public class AnimalController {

    @Autowired
    AnimalRepository animalRepository;

    @GetMapping("/all")
    public List<Animal> getAllAnimals(){
        return animalRepository.findAll();
    }

    @PostMapping("/save") //also for editing if we add id key that exists
    public ResponseEntity<?> saveAnimal(@RequestParam(required = false) Long id,
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) String type,
                                        @RequestParam(required = false) Integer serial_n){

        boolean isNew = id == null;

    Animal animal = new Animal(id, name, type, serial_n);
    animal = animalRepository.save(animal);

    Map<String, Object> response = new HashMap<>();
    response.put("generatedId", animal.getId());

    if(isNew){
            response.put("message", "Animal added successfully");
    }else{
            response.put("message", "Animal edited successfully");
    }

    return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save/animal") //also for editing if we add id key that exists
    public ResponseEntity<?> saveAnimal(@RequestParam Animal form ){

        boolean isNew = form.getId() == null;

        Map<String, Object> response = new HashMap<>();
        response.put("animal", animalRepository.save(form));
        if(isNew){
            response.put("message", "Animal added successfully");
        }else{
            response.put("message", "Animal edited successfully");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAnimal(@RequestParam Long id){
        if(!animalRepository.existsById(id)) {
            return ResponseEntity.ok("No animal with such ID");
        }
        animalRepository.deleteById(id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @GetMapping("/searchByName")
    public ResponseEntity<?> getAnimalByName(@RequestParam(required = false) String name){
        if(name == null || name.isBlank()){
            return ResponseEntity.ok().body("No name entered");
        }
        Optional<Animal> result = animalRepository.findAnimalByName(name.toLowerCase());
        return result.isPresent()? ResponseEntity.ok(result.get()) : ResponseEntity.ok().body("No animal with that name");
    }

    @GetMapping("/searchBySerialNumber")
    public ResponseEntity<?> getAnimalBySerialNumber(@RequestParam(required = false) Integer serial_n){
        if(serial_n == null){
            return ResponseEntity.ok().body("No id entered");
        }
        Optional<Animal> result = animalRepository.findAnimalById(serial_n);
        return result.isPresent()? ResponseEntity.ok(result.get()) : ResponseEntity.ok().body("No animal with that serial number");
    }

    @GetMapping("/search/page")
    public void paginateAnimal(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                               @RequestParam(value = "perPage", defaultValue = "5") int perPage,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String type,
                               @RequestParam(required = false) Integer serial_n){



        Pageable pageable = (Pageable) PageRequest.of( currentPage - 1, perPage);
        //Page<Animal> animals = animalRepository.findPageAnimals(pageable,
        //        name.toLowerCase(),
        //        type.toLowerCase(),
        //        serial_n );

       // Map<String, Object> response = new HashMap<>();
       // response.put("animals", animals.getContent());
    }
}
