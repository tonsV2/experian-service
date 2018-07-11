package eu.energisk.experian.service

interface ExperianService {
    fun queryPersonPaymentRemarks(cpr: String): Boolean
    fun queryCompanyPaymentRemarks(cvr: String): Boolean
}
