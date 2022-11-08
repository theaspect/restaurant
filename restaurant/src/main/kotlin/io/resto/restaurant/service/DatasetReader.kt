package io.resto.restaurant.service

import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import io.resto.restaurant.domain.Restaurant
import java.io.InputStream
import java.io.InputStreamReader

object DatasetReader {
    fun read(inputStream: InputStream): List<Restaurant> =
        CSVReaderBuilder(InputStreamReader(inputStream))
            .withSkipLines(1)
            .withCSVParser(
                CSVParserBuilder()
                    .withSeparator(',')
                    .withIgnoreQuotations(true)
                    .build()
            )
            .build().use { csvReader ->
                csvReader.map { row ->
                    Restaurant(
                        id = row[0].toInt(),
                        name = row[1],
                        city = row[2],
                        street = row[3],
                        house = row[4],
                        latitude = row[5].toDouble(),
                        longitude = row[6].toDouble(),
                        version = 0,
                    )
                }
            }
}