package eu.energisk.experian

import dk.rki.webservices.firma.Firma
import dk.rki.webservices.firma.FirmaRegistreringData
import dk.rki.webservices.firma.FirmaSoap
import dk.rki.webservices.person.Person
import dk.rki.webservices.person.PersonRegistreringData
import dk.rki.webservices.person.PersonSoap
import eu.energisk.experian.configuration.ExperianClientConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

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

@Service
class ExperianClient(private val configuration: ExperianClientConfiguration, val person: PersonSoap, val firma: FirmaSoap) {
    fun searchCreditHistoryByCpr(cpr: String): PersonRegistreringData = person.soegPersonRegistreringCpr(configuration.username, configuration.password, cpr)
    fun searchCreditHistoryByCvr(cvr: String): FirmaRegistreringData = firma.soegFirmaRegistreringCvr(configuration.username, configuration.password, cvr)
}

fun main(args: Array<String>) {
    runApplication<ServiceApplication>(*args)
}
