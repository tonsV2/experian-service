package eu.energisk.experian.controller

import com.fasterxml.jackson.annotation.JsonProperty
import eu.energisk.experian.service.ExperianException
import eu.energisk.experian.service.ExperianService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ExperianController(private val experianService: ExperianService) {
    @PostMapping("/check")
    fun getCheckPerson(@RequestParam(required = false) cpr: String?, @RequestParam(required = false) cvr: String?): ResponseEntity<*> {
        return try {
            val paymentRemarks = when {
                cvr != null -> experianService.queryCompanyPaymentRemarks(cvr)
                cpr != null -> experianService.queryPersonPaymentRemarks(cpr)
                else -> throw ExperianException(-1, "cpr or cvr parameter is required", "")
            }
            ResponseEntity.ok(ExperianResponse("success", paymentRemarks, true))
        } catch (e: ExperianException) {
            ResponseEntity.ok(ExperianResponse(e.text, null, false))
        }
    }
}

class ExperianResponse(
        val message: String,
        @JsonProperty("payment-remarks") val paymentRemarks: Boolean?,
        @JsonProperty("name-match") val nameMatch: Boolean
)
