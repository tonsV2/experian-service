package eu.energisk.experian.service

import dk.rki.webservices.firma.FirmaRegistreringData
import dk.rki.webservices.firma.FirmaSoap
import dk.rki.webservices.person.PersonRegistreringData
import dk.rki.webservices.person.PersonSoap
import eu.energisk.experian.configuration.ExperianClientConfiguration
import org.springframework.stereotype.Service

@Service
class ExperianClient(private val configuration: ExperianClientConfiguration, val person: PersonSoap, val firma: FirmaSoap) {
    fun searchCreditHistoryByCpr(cpr: String): PersonRegistreringData = person.soegPersonRegistreringCpr(configuration.username, configuration.password, cpr)
    fun searchCreditHistoryByCvr(cvr: String): FirmaRegistreringData = firma.soegFirmaRegistreringCvr(configuration.username, configuration.password, cvr)
}