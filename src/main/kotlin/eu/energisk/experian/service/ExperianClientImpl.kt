package eu.energisk.experian.service

import dk.rki.webservices.firma.FirmaRegistreringData
import dk.rki.webservices.firma.FirmaSoap
import dk.rki.webservices.person.PersonRegistreringData
import dk.rki.webservices.person.PersonSoap
import eu.energisk.experian.configuration.ExperianClientConfiguration
import org.springframework.stereotype.Service

@Service
class ExperianClientImpl(private val configuration: ExperianClientConfiguration, private val person: PersonSoap, private val firma: FirmaSoap) : ExperianClient {
    override fun searchCreditHistoryByCpr(cpr: String): PersonRegistreringData = person.soegPersonRegistreringCpr(configuration.username, configuration.password, cpr)
    override fun searchCreditHistoryByCvr(cvr: String): FirmaRegistreringData = firma.soegFirmaRegistreringCvr(configuration.username, configuration.password, cvr)
}
