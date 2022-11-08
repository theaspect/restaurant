package io.resto.restaurant.domain

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Version

@MappedEntity
class Restaurant(
    @field:Id
    @field:GeneratedValue
    var id: Int? = null,

    val name: String,

    val city: String,
    val street: String,
    val house: String,

    val latitude: Double,
    val longitude: Double,

    @field:Version
    var version: Int?,
)