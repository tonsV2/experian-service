package eu.energisk.experian.service

import dk.rki.webservices.firma.FirmaRegistreringData

interface ExperianService {
    fun queryPaymentRemarksPerson(cpr: String): Boolean
    fun queryCompany(cvr: String): FirmaRegistreringData?
}
