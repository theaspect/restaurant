package io.resto.restaurant

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.resto.restaurant.domain.Restaurant

@Suppress("BlockingMethodInNonBlockingContext")
@MicronautTest
class RestaurantAppTest(
    private val application: EmbeddedApplication<*>,
    @Client("/") private val client: HttpClient
) : StringSpec({


    "test the server is running" {
        assert(application.isRunning)
    }

    "select all" {
        val response: List<Restaurant> = client.toBlocking().retrieve(
            HttpRequest.GET<Iterable<Restaurant>>("/"), Argument.listOf(Restaurant::class.java)
        )
        response.size shouldBe 20
    }

    "get one" {
        val response: Restaurant = client.toBlocking().retrieve(
            HttpRequest.GET<Restaurant>("/10"), Argument.of(Restaurant::class.java)
        )

        response.id shouldBe 10
        response.name shouldBe "Тарталетка"
        response.city shouldBe "Кемерово"
        response.street shouldBe "ул 50 лет Октября"
        response.house shouldBe "11"
        response.latitude shouldBe 55.35124
        response.longitude shouldBe 86.083534
    }

    "save" {
        val expectedRestaurant = Restaurant(
            name = "test name",
            city = "test city",
            street = "test street",
            house = "99",
            latitude = 55.0,
            longitude = 86.0,
        )
        val response: HttpResponse<Restaurant> = client.toBlocking().exchange(
            HttpRequest.POST(
                "/", expectedRestaurant
            ),
            Restaurant::class.java
        )

        response.body() shouldNotBe null

        val actualRestaurant = response.body()!!
        actualRestaurant.id shouldBe 21

        actualRestaurant.name shouldBe expectedRestaurant.name
        actualRestaurant.city shouldBe expectedRestaurant.city
        actualRestaurant.street shouldBe expectedRestaurant.street
        actualRestaurant.house shouldBe expectedRestaurant.house
        actualRestaurant.latitude shouldBe expectedRestaurant.latitude
        actualRestaurant.longitude shouldBe expectedRestaurant.longitude

        val actualRequested: Restaurant = client.toBlocking().retrieve(
            HttpRequest.GET<Restaurant>("/21"), Argument.of(Restaurant::class.java)
        )

        actualRequested.id shouldBe 21
        actualRequested.name shouldBe expectedRestaurant.name
        actualRequested.city shouldBe expectedRestaurant.city
        actualRequested.street shouldBe expectedRestaurant.street
        actualRequested.house shouldBe expectedRestaurant.house
        actualRequested.latitude shouldBe expectedRestaurant.latitude
        actualRequested.longitude shouldBe expectedRestaurant.longitude
    }

    "update" {
        val expectedRestaurant: Restaurant = client.toBlocking().retrieve(
            HttpRequest.GET<Restaurant>("/21"),
            Restaurant::class.java
        )
            .apply {
                name = "test test name"
            }

        val response: HttpResponse<Restaurant> = client.toBlocking().exchange(
            HttpRequest.PATCH(
                UriBuilder.of("/21")
                    .queryParam("name", "test test name")
                    .toString(), null
            ),
            Restaurant::class.java
        )

        response.body() shouldNotBe null

        val actualRestaurant = response.body()!!
        actualRestaurant.id shouldBe 21

        actualRestaurant.name shouldBe expectedRestaurant.name
        actualRestaurant.city shouldBe expectedRestaurant.city
        actualRestaurant.street shouldBe expectedRestaurant.street
        actualRestaurant.house shouldBe expectedRestaurant.house
        actualRestaurant.latitude shouldBe expectedRestaurant.latitude
        actualRestaurant.longitude shouldBe expectedRestaurant.longitude

        val actualRequested: Restaurant = client.toBlocking().retrieve(
            HttpRequest.GET<Restaurant>("/21"), Argument.of(Restaurant::class.java)
        )

        actualRequested.id shouldBe 21
        actualRequested.name shouldBe expectedRestaurant.name
        actualRequested.city shouldBe expectedRestaurant.city
        actualRequested.street shouldBe expectedRestaurant.street
        actualRequested.house shouldBe expectedRestaurant.house
        actualRequested.latitude shouldBe expectedRestaurant.latitude
        actualRequested.longitude shouldBe expectedRestaurant.longitude
    }

    "delete" {
        client.toBlocking().exchange(HttpRequest.DELETE("/21", null), String::class.java)

        val responseCount: List<Restaurant> = client.toBlocking().retrieve(
            HttpRequest.GET<Iterable<Restaurant>>("/"), Argument.listOf(Restaurant::class.java)
        )
        responseCount.size shouldBe 20
    }
})
