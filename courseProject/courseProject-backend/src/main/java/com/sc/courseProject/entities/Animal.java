package com.sc.courseProject.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    public Animal(){
    }

    public Animal(Long id, String name, String type, Integer serial_n) {
        Id = id;
        this.name = name;
        this.type = type;
        this.serial_n = serial_n;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "serial_n")
    private Integer serial_n;

    @ManyToOne
    @JoinColumn(name = "zoo_id")
    private Zoo zoo;

    public Zoo getZoo() {
        return zoo;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "animal_characteristics",
              joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id"))

    private Set<Characteristics> characteristics = new HashSet<>();

    public Set<Characteristics> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Set<Characteristics> characteristics) {
        this.characteristics = characteristics;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSerial_n() {
        return serial_n;
    }

    public void setSerial_n(Integer serial_n) {
        this.serial_n = serial_n;
    }
}
