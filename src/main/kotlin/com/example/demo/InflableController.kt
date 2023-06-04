package com.example.demo

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/inflables")
class InflableController(private val repository: InflableRepository) {
    @GetMapping
    fun findAll(): List<Inflable> = repository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) = repository.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody inflable: Inflable) = repository.save(inflable)

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody inflable: Inflable): Inflable {
        assert(inflable.id == id)
        return repository.save(inflable)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String) = repository.deleteById(id)
}
