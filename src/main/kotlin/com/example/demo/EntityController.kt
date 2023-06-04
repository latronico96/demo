package com.example.demo

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/entities")
class EntityController(private val entityService: EntityService) {
    @PostMapping
    fun create(@RequestBody entity: Entity): ResponseEntity<Entity> {
        val createdEntity = entityService.create(entity)
        return ResponseEntity(createdEntity, HttpStatus.CREATED)
    }

    @GetMapping
    fun readAll(): ResponseEntity<List<Entity>> {
        print(entityService);
        val entities = entityService.readAll()
        return ResponseEntity(entities, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun read(@PathVariable id: String): ResponseEntity<Entity> {
        val entity = entityService.read(id)
        return ResponseEntity(entity, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody entity: Entity): ResponseEntity<Entity> {
        if (entity.id != id) {
            throw Exception()
        }
        val updatedEntity = entityService.update(entity)
        return ResponseEntity(updatedEntity, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        entityService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
