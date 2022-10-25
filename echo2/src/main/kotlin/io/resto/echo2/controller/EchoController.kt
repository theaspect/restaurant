package io.resto.echo2.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("/echo")
class EchoController {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Get("/")
    fun echo(
        @QueryValue value: String,
    ): String {
        log.info("Receiving echo request $value")
        return value
    }
}