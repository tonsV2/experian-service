package eu.energisk.experian.controller

import com.fasterxml.jackson.annotation.JsonProperty
import eu.energisk.experian.service.ExperianException
import eu.energisk.experian.service.ExperianService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ExperianController(private val experianService: ExperianService) {
    @PostMapping("/check")
    fun getCheckPerson(@RequestParam cpr: String): ResponseEntity<*> {
        return try {
            val paymentRemarks = experianService.queryPersonPaymentRemarks(cpr)
            ResponseEntity.ok(ExperianResponse("success", paymentRemarks, true))
        } catch (e: ExperianException) {
            ResponseEntity.badRequest().body(e.text)
        }
    }

    @PostMapping("/check-company")
    fun getCheckCompany(@RequestParam cvr: String): ResponseEntity<*> {
        return try {
            val paymentRemarks = experianService.queryCompanyPaymentRemarks(cvr)
            ResponseEntity.ok(ExperianResponse("success", paymentRemarks, true))
        } catch (e: ExperianException) {
            ResponseEntity.badRequest().body(e.text)
        }
    }
}

class ExperianResponse(
        val message: String,
        @JsonProperty("payment-remarks") val paymentRemarks: Boolean,
        @JsonProperty("name-match") val nameMatch: Boolean
)
