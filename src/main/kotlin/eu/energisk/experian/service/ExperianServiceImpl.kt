package eu.energisk.experian.service

import dk.rki.webservices.firma.Firma
import dk.rki.webservices.person.Person
import eu.energisk.experian.configuration.ExperianClientConfiguration
import org.springframework.stereotype.Service

@Service
class ExperianServiceImpl(private val configuration: ExperianClientConfiguration) : ExperianService {
    override fun queryPersonPaymentRemarks(cpr: String): Boolean {
        val personData = Person().personSoap.soegPersonRegistreringCpr(configuration.username, configuration.password, cpr)
        val error = personData.error
        if (error.code != 0) {
            throw ExperianException(error.code, error.text, error.detailed)
        }
        return personData.registreringer.registreringsListe.registrering.isNotEmpty()
    }

    override fun queryCompanyPaymentRemarks(cvr: String): Boolean {
        val companyData = Firma().firmaSoap.soegFirmaRegistreringCvr(configuration.username, configuration.password, cvr)
        val error = companyData.error
        if (error.code != 0) {
            throw ExperianException(error.code, error.text, error.detailed)
        }
        return companyData.registreringer.registreringsListe.registrering.isNotEmpty()
    }
}
