package com.example.demo

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contactos")
class ContactoController(private val repository: ContactoRepository) {
    @GetMapping
    fun findAll(): List<Contacto> = repository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) = repository.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody contacto: Contacto) = repository.save(contacto)

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody contacto: Contacto): Contacto {
        assert(contacto.id == id)
        return repository.save(contacto)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String) = repository.deleteById(id)
}