package eu.energisk.experian.service

import dk.rki.webservices.firma.FirmaSoap
import dk.rki.webservices.person.PersonSoap
import eu.energisk.experian.configuration.ExperianClientConfiguration
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class ExperianClientImplTest {
    @Test
    fun searchCreditHistoryByCpr() {
        val configuration = mockk<ExperianClientConfiguration>()
        every { configuration.username } returns "username"
        every { configuration.password } returns "password"
        val personSoap = mockk<PersonSoap>(relaxed = true)
        val firmaSoap = mockk<FirmaSoap>()

        val cpr = "666"
        val client = ExperianClientImpl(configuration, personSoap, firmaSoap)

        client.searchCreditHistoryByCpr(cpr)

        verify(exactly = 1) {
            personSoap.soegPersonRegistreringCpr("username", "password", cpr)
        }
    }

    @Test
    fun searchCreditHistoryByCvr() {
        val configuration = mockk<ExperianClientConfiguration>()
        every { configuration.username } returns "username"
        every { configuration.password } returns "password"
        val personSoap = mockk<PersonSoap>()
        val firmaSoap = mockk<FirmaSoap>(relaxed = true)

        val cvr = "666"
        val client = ExperianClientImpl(configuration, personSoap, firmaSoap)

        client.searchCreditHistoryByCvr(cvr)

        verify(exactly = 1) {
            firmaSoap.soegFirmaRegistreringCvr("username", "password", cvr)
        }
    }
}
