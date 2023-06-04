package com.example.demo

import org.springframework.stereotype.Service

@Service
class EntityService(private val entityRepository: EntityRepository) {

    fun create(entity: Entity): Entity {
        return entityRepository.save(entity)
    }

    fun readAll(): List<Entity> {
        return entityRepository.findAll()
    }

    fun read(id: String): Entity {
        return entityRepository.findById(id).orElseThrow { Exception(id) }
    }

    fun update(entity: Entity): Entity {
        return entityRepository.save(entity)
    }

    fun delete(id: String) {
        entityRepository.deleteById(id)
    }
}
