package eu.energisk.experian

import dk.rki.webservices.firma.Firma
import dk.rki.webservices.firma.FirmaSoap
import dk.rki.webservices.person.Person
import dk.rki.webservices.person.PersonSoap
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ServiceApplication {
    @Bean
    fun personClient(): PersonSoap {
        return Person().personSoap
    }

    @Bean
    fun firmaClient(): FirmaSoap {
        return Firma().firmaSoap
    }
}

fun main(args: Array<String>) {
    runApplication<ServiceApplication>(*args)
}
