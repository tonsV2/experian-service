package eu.energisk.experian.controller

import com.fasterxml.jackson.annotation.JsonProperty
import dk.rki.webservices.firma.FirmaRegistreringData
import eu.energisk.experian.service.ExperianException
import eu.energisk.experian.service.ExperianService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ExperianController(private val experianService: ExperianService) {
    @GetMapping("/check")
    fun getCheckPerson(@RequestParam cpr: String): ResponseEntity<*> {
        return try {
            val paymentRemarks = experianService.queryPaymentRemarksPerson(cpr)
            ResponseEntity.ok(ExperianResponse("success", paymentRemarks, true))
        } catch (e: ExperianException) {
            ResponseEntity.badRequest().body(e.text)
        }
    }

    @GetMapping("/check-company")
    fun getCheckCompany(@RequestParam cvr: String): FirmaRegistreringData? {
        return experianService.queryCompany(cvr)
    }
}

class ExperianResponse(
        val message: String,
        @JsonProperty("payment-remarks") val paymentRemarks: Boolean,
        @JsonProperty("name-match") val nameMatch: Boolean
)
