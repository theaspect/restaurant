package io.resto.echo2

import io.micronaut.runtime.Micronaut

class RestaurantApp

fun main(args: Array<String>) {
    Micronaut.build(*args)
        .mainClass(RestaurantApp::class.java)
        .defaultEnvironments("dev")
        .start()
}