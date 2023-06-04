package com.example.demo

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EntityRepository : MongoRepository<Entity, String>
@Repository
interface ContactoRepository : MongoRepository<Contacto, String>
@Repository
interface InflableRepository : MongoRepository<Inflable, String>
@Repository
interface AlquilerRepository : MongoRepository<Alquiler, String>
