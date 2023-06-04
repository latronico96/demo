package com.example.demo

class InflableService(private val inflableRepository: InflableRepository) {

    fun create(inflable: Inflable): Inflable {
        return inflableRepository.save(inflable)
    }

    fun readAll(): List<Inflable> {
        return inflableRepository.findAll()
    }

    fun read(id: String): Inflable {
        return inflableRepository.findById(id).orElseThrow { Exception(id) }
    }

    fun update(inflable: Inflable): Inflable {
        return inflableRepository.save(inflable)
    }

    fun delete(id: String) {
        inflableRepository.deleteById(id)
    }
}
