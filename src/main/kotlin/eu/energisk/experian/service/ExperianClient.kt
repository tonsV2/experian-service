package eu.energisk.experian.service

import dk.rki.webservices.firma.FirmaRegistreringData
import dk.rki.webservices.person.PersonRegistreringData

interface ExperianClient {
    fun searchCreditHistoryByCpr(cpr: String): PersonRegistreringData
    fun searchCreditHistoryByCvr(cvr: String): FirmaRegistreringData
}
