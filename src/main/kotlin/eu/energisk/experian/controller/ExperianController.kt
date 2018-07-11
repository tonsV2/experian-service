package eu.energisk.experian.controller

import dk.rki.webservices.firma.FirmaRegistreringData
import dk.rki.webservices.person.PersonAdresseData
import eu.energisk.experian.service.ExperianService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ExperianController(private val experianService: ExperianService) {
    @GetMapping("/check")
    fun getCheckPerson(@RequestParam cpr: String): PersonAdresseData? {
        return experianService.queryPerson(cpr)
    }

    @GetMapping("/check-company")
    fun getCheckCompany(@RequestParam cvr: String): FirmaRegistreringData? {
        return experianService.queryCompany(cvr)
    }
}
