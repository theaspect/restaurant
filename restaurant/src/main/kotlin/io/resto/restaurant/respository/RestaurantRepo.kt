package io.resto.restaurant.respository

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import io.resto.restaurant.domain.Restaurant

@JdbcRepository(dialect = Dialect.MYSQL)
interface RestaurantRepo : CrudRepository<Restaurant, Int>