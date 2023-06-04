package com.example.demo

import org.springframework.stereotype.Service

@Service
class ContactoService(private val contactoRepository: ContactoRepository) {

    fun create(contacto: Contacto): Contacto {
        return contactoRepository.save(contacto)
    }

    fun readAll(): List<Contacto> {
        return contactoRepository.findAll()
    }

    fun read(id: String): Contacto {
        return contactoRepository.findById(id).orElseThrow { Exception(id) }
    }

    fun update(contacto: Contacto): Contacto {
        return contactoRepository.save(contacto)
    }

    fun delete(id: String) {
        contactoRepository.deleteById(id)
    }
}
