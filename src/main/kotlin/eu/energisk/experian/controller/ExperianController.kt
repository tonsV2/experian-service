package eu.energisk.experian.controller

import dk.rki.webservices.firma.Firma
import dk.rki.webservices.firma.FirmaRegistreringData
import dk.rki.webservices.person.Person
import dk.rki.webservices.person.PersonAdresseData
import eu.energisk.experian.configuration.ExperianClientConfiguration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ExperianController(private val experianClientConfiguration: ExperianClientConfiguration) {
    @GetMapping("/check")
    fun getCheckPerson(@RequestParam cpr: String): PersonAdresseData? {
        val client = Person().personSoap
        return client.soegPersonCpr(experianClientConfiguration.username, experianClientConfiguration.password, cpr)
    }

    @GetMapping("/check-company")
    fun getCheckCompany(@RequestParam cvr: String): FirmaRegistreringData? {
        val client = Firma().firmaSoap
        return client.soegFirmaRegistreringCvr(experianClientConfiguration.username, experianClientConfiguration.password, cvr)
    }
}
