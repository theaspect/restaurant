package io.resto.echo1.controller

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Inject

@MicronautTest
class EchoControllerTest(
    @Inject @Client("/") val client: HttpClient,
) : StringSpec({
    "echo should return the same value" {
        val expected = "Hello World"
        val uri = UriBuilder.of("/echo").queryParam("value", expected).build()

        val actual: String = client.toBlocking().retrieve(HttpRequest.GET<String>(uri))

        actual shouldBe expected
    }
})