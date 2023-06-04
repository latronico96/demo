package com.example.demo

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/alquileres")
class AlquilerController(private val repository: AlquilerRepository) {
    @GetMapping
    fun findAll(): List<Alquiler> = repository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) = repository.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody alquiler: Alquiler): Alquiler {
        println(alquiler)
        return repository.save(alquiler)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody alquiler: Alquiler): Alquiler {
        assert(alquiler.id == id)
        return repository.save(alquiler)

    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String) = repository.deleteById(id)
}

