package com.example.demo

class AlquilerService(private val alquilerRepository: AlquilerRepository) {

    fun create(alquiler: Alquiler): Alquiler {
        return alquilerRepository.save(alquiler)
    }

    fun readAll(): List<Alquiler> {
        return alquilerRepository.findAll()
    }

    fun read(id: String): Alquiler {
        return alquilerRepository.findById(id).orElseThrow { Exception(id) }
    }

    fun update(alquiler: Alquiler): Alquiler {
        return alquilerRepository.save(alquiler)
    }

    fun delete(id: String) {
        alquilerRepository.deleteById(id)
    }
}
