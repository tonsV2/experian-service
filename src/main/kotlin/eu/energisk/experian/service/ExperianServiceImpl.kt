package eu.energisk.experian.service

import dk.rki.webservices.firma.Firma
import dk.rki.webservices.firma.FirmaRegistreringData
import dk.rki.webservices.person.Person
import dk.rki.webservices.person.PersonAdresseData
import eu.energisk.experian.configuration.ExperianClientConfiguration
import org.springframework.stereotype.Service

@Service
class ExperianServiceImpl(private val configuration: ExperianClientConfiguration) : ExperianService {
    override fun queryPerson(cpr: String): PersonAdresseData? {
        return Person().personSoap.soegPersonCpr(configuration.username, configuration.password, cpr)
    }

    override fun queryCompany(cvr: String): FirmaRegistreringData? {
        return Firma().firmaSoap.soegFirmaRegistreringCvr(configuration.username, configuration.password, cvr)
    }
}
