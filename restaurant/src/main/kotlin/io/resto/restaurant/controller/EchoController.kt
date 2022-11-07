package io.resto.echo2.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject
import jakarta.inject.Named
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import javax.sql.DataSource

@Controller("/")
class EchoController(
    @Inject @Named("default") private val dataSource: DataSource,
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Get("/echo")
    fun echo(
        @QueryValue value: String,
    ): String {
        log.info("Receiving echo request $value")
        return value
    }

    @Get("/dbTime")
    fun dbTime(): LocalDateTime {
        log.info("Querying DB")
        return dataSource.connection.use { connection ->
            connection.prepareStatement("SELECT NOW()").use { preparedStatement ->
                preparedStatement.executeQuery().use { resultSet ->
                    resultSet.next()
                    // Started with 1
                    resultSet.getTimestamp(1).toLocalDateTime()
                }
            }
        }
    }
}