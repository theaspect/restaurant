package io.resto.echo2

import io.micronaut.runtime.Micronaut

class Echo2App

fun main(args: Array<String>) {
    Micronaut.build(*args)
        .mainClass(Echo2App::class.java)
        .defaultEnvironments("dev")
        .start()
}
