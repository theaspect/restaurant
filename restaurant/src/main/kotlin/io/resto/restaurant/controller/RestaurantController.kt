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
        @Body restaurant: Restaurant,
    ): Restaurant {
        log.info("Create new restaurant $restaurant")

        require(restaurant.id == null) { "Id should be null" }

        // Force version to be 0
        restaurant.version = 0

        return restaurantRepo.save(restaurant)
    }

    @Patch("/{restaurantId}")
    fun update(
        @PathVariable restaurantId: RestaurantId,
        @QueryValue name: String,
    ): Restaurant {
        val dbRestaurant = restaurantRepo.findById(restaurantId)
            .orElseThrow { IllegalArgumentException("Can't find restaurant $restaurantId") }

        return restaurantRepo.update(dbRestaurant.apply {
            this.name = name
        })
    }

    @Delete("/{restaurantId}")
    fun delete(
        @PathVariable restaurantId: RestaurantId
    ) {
        restaurantRepo.deleteById(restaurantId)
    }

}