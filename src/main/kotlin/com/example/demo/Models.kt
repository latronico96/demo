package com.example.demo
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
data class Entity(
    @Id
    var id: String? = null,
    var name: String? = null
)

@Document
data class Contacto(
    @Id
    val id: String? = null,
    val name: String,
    val description: String
)

@Document
data class Inflable(
    @Id
    val id: String? = null,
    val name: String
)

@Document
data class Alquiler(
    @Id
    val id: String? = null,
    @DBRef
    val contacto: Contacto,
    @DBRef
    val inflable: Inflable
)
