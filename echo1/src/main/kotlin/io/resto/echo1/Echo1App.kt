package io.resto.echo1

import io.micronaut.runtime.Micronaut

class Echo1App

fun main(args: Array<String>) {
    Micronaut.build(*args)
        .mainClass(Echo1App::class.java)
        .defaultEnvironments("dev")
        .start()
}
