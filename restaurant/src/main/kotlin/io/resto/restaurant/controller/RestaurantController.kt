package io.resto.restaurant.controller

import io.micronaut.http.annotation.*
import io.resto.restaurant.domain.Restaurant
import io.resto.restaurant.domain.RestaurantId
import io.resto.restaurant.respository.RestaurantRepo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/")
class RestaurantController(
    private val restaurantRepo: RestaurantRepo,
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Get("/")
    fun findAll(): Iterable<Restaurant> {
        log.debug("Find all restaurants")
        return restaurantRepo.findAll()
    }

    @Get("/{restaurantId}")
    fun getById(
        @PathVariable restaurantId: RestaurantId
    ): Optional<Restaurant> {
        log.info("Find restaurant $restaurantId")
        return restaurantRepo.findById(restaurantId)
    }

    @Post("/")
    fun create(
        @QueryValue name: String,

        @QueryValue city: String,
        @QueryValue street: String,
        @QueryValue house: String,

        @QueryValue latitude: Double,
        @QueryValue longitude: Double,
    ): Restaurant {
        log.info("Create new restaurant $name")

        return restaurantRepo.save(
            Restaurant(
                id = null,
                name = name,
                city = city,
                street = street,
                house = house,
                latitude = latitude,
                longitude = longitude,
            )
        )
    }

    @Patch("/")
    fun update(
        @Body restaurant: Restaurant
    ): Restaurant {
        val dbRestaurant = restaurantRepo.findById(restaurant.id!!)
            .orElseThrow { IllegalArgumentException("Can't find restaurant ${restaurant.id}") }

        require(restaurant.version == dbRestaurant.version)
        {
            "Optimistic lock exception for restaurant id ${restaurant.id}: " +
                    "db version ${dbRestaurant.version} != patch version ${restaurant.version}"
        }

        return restaurantRepo.update(dbRestaurant)
    }

}