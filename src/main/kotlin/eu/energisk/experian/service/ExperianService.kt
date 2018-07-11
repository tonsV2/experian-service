package eu.energisk.experian.service

import dk.rki.webservices.firma.FirmaRegistreringData
import dk.rki.webservices.person.PersonAdresseData

interface ExperianService {
    fun queryPerson(cpr: String): PersonAdresseData?
    fun queryCompany(cvr: String): FirmaRegistreringData?
}
