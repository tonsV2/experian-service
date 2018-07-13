package eu.energisk.experian.service

import eu.energisk.experian.ExperianClient
import org.springframework.stereotype.Service

@Service
class ExperianServiceImpl(private val experianClient: ExperianClient) : ExperianService {
    override fun queryPersonPaymentRemarks(cpr: String): Boolean {
        val personData = experianClient.searchCreditHistoryByCpr(cpr)
        val error = personData.error
        if (error.code != 0) {
            throw ExperianException(error.code, error.text, error.detailed)
        }
        return personData.registreringer.registreringsListe.registrering.isNotEmpty()
    }

    override fun queryCompanyPaymentRemarks(cvr: String): Boolean {
        val companyData = experianClient.searchCreditHistoryByCvr(cvr)
        val error = companyData.error
        if (error.code != 0) {
            throw ExperianException(error.code, error.text, error.detailed)
        }
        return companyData.registreringer.registreringsListe.registrering.isNotEmpty()
    }
}
