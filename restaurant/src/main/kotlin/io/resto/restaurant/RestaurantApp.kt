package io.resto.restaurant

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.Micronaut
import io.micronaut.runtime.server.event.ServerStartupEvent
import io.resto.restaurant.respository.RestaurantRepo
import io.resto.restaurant.service.DatasetReader
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class RestaurantApp(
    private val restaurantRepo: RestaurantRepo,
) : ApplicationEventListener<ServerStartupEvent> {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun onApplicationEvent(event: ServerStartupEvent?) {
        log.info("Server started")

        if (restaurantRepo.count() == 0L) {
            log.info("No rows in table, filling with test data")

            val testRestaurants = this::class.java.classLoader
                .getResourceAsStream("dataset/restaurant.csv").use { dataset ->
                    if (dataset == null) {
                        log.info("Can't find dataset, skipping")
                        emptyList()
                    } else {
                        DatasetReader.read(dataset)
                    }
                }
            log.info("Will save ${testRestaurants.size} restaurants")

            restaurantRepo.saveAll(testRestaurants)
        }

    }
}

fun main(args: Array<String>) {
    Micronaut.build(*args)
        .mainClass(RestaurantApp::class.java)
        .defaultEnvironments("dev")
        .start()
}
