package eu.energisk.experian.service

import dk.rki.webservices.firma.Firma
import dk.rki.webservices.firma.FirmaRegistreringData
import dk.rki.webservices.person.Person
import eu.energisk.experian.configuration.ExperianClientConfiguration
import org.springframework.stereotype.Service

@Service
class ExperianServiceImpl(private val configuration: ExperianClientConfiguration) : ExperianService {
    override fun queryPaymentRemarksPerson(cpr: String): Boolean {
        val personData = Person().personSoap.soegPersonRegistreringCpr(configuration.username, configuration.password, cpr)
        val error = personData.error
        if (error.code != 0) {
            throw ExperianException(error.code, error.text, error.detailed)
        }
        return personData.registreringer.registreringsListe.registrering.isNotEmpty()
    }

    override fun queryCompany(cvr: String): FirmaRegistreringData? {
        return Firma().firmaSoap.soegFirmaRegistreringCvr(configuration.username, configuration.password, cvr)
    }
}
