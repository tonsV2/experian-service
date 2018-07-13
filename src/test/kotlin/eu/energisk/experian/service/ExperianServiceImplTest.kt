
package eu.energisk.experian.service

import dk.rki.webservices.firma.FirmaRegistreringData
import dk.rki.webservices.person.PersonRegistreringData
import dk.rki.webservices.person.XmlError
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class ExperianServiceImplTest {
    @Test
    fun `No payment remarks found`() {
        val cpr = "666"
        val client = mockk<ExperianClient>()
        val experianService = ExperianServiceImpl(client)

        val personRegistreringData = mockk<PersonRegistreringData>()
        every {
            personRegistreringData.error.code
        } returns 0

        every {
            personRegistreringData.registreringer.registreringsListe.registrering.isEmpty()
        } returns true

        every {
            client.searchCreditHistoryByCpr(cpr)
        } returns personRegistreringData

        val personPaymentRemarks = experianService.queryPersonPaymentRemarks(cpr)

        assertFalse(personPaymentRemarks)

        verify(exactly = 1) {
            client.searchCreditHistoryByCpr(cpr)
        }
    }

    @Test
    fun `Payment remarks found`() {
        val cpr = "666"
        val client = mockk<ExperianClient>()
        val experianService = ExperianServiceImpl(client)

        val personRegistreringData = mockk<PersonRegistreringData>()
        every {
            personRegistreringData.error.code
        } returns 0

        every {
            personRegistreringData.registreringer.registreringsListe.registrering.isEmpty()
        } returns false

        every {
            client.searchCreditHistoryByCpr(cpr)
        } returns personRegistreringData

        val personPaymentRemarks = experianService.queryPersonPaymentRemarks(cpr)

        assertTrue(personPaymentRemarks)

        verify(exactly = 1) {
            client.searchCreditHistoryByCpr(cpr)
        }
    }

    @Test
    fun `ExperianException thrown and populated on external error`() {
        val cpr = "666"
        val client = mockk<ExperianClient>()
        val experianService = ExperianServiceImpl(client)

        val xmlError = XmlError()
        xmlError.code = -1
        xmlError.text = "error text"
        xmlError.detailed = "error detailed"

        val personRegistreringData = mockk<PersonRegistreringData>(relaxed = true)
        every {
            personRegistreringData.error
        } returns xmlError

        every {
            client.searchCreditHistoryByCpr(cpr)
        } returns personRegistreringData


        val experianException = assertThrows(ExperianException::class.java) {
            experianService.queryPersonPaymentRemarks(cpr)
        }

        assertEquals(xmlError.code, experianException.code)
        assertEquals(xmlError.text, experianException.text)
        assertEquals(xmlError.detailed, experianException.detailed)

        verify(exactly = 1) {
            client.searchCreditHistoryByCpr(cpr)
        }
    }

    @Test
    fun `No company payment remarks found`() {
        val cvr = "666"
        val client = mockk<ExperianClient>()
        val experianService = ExperianServiceImpl(client)

        val firmaRegistreringData = mockk<FirmaRegistreringData>()
        every {
            firmaRegistreringData.error.code
        } returns 0

        every {
            firmaRegistreringData.registreringer.registreringsListe.registrering.isEmpty()
        } returns true

        every {
            client.searchCreditHistoryByCvr(cvr)
        } returns firmaRegistreringData

        val paymentRemarks = experianService.queryCompanyPaymentRemarks(cvr)

        assertFalse(paymentRemarks)

        verify(exactly = 1) {
            client.searchCreditHistoryByCvr(cvr)
        }
    }

    @Test
    fun `Company payment remarks found`() {
        val cvr = "666"
        val client = mockk<ExperianClient>()
        val experianService = ExperianServiceImpl(client)

        val firmaRegistreringData = mockk<FirmaRegistreringData>()
        every {
            firmaRegistreringData.error.code
        } returns 0

        every {
            firmaRegistreringData.registreringer.registreringsListe.registrering.isEmpty()
        } returns false

        every {
            client.searchCreditHistoryByCvr(cvr)
        } returns firmaRegistreringData

        val paymentRemarks = experianService.queryCompanyPaymentRemarks(cvr)

        assertTrue(paymentRemarks)

        verify(exactly = 1) {
            client.searchCreditHistoryByCvr(cvr)
        }
    }

    @Test
    fun `ExperianException thrown and populated on external error for CVR search`() {
        val cvr = "666"
        val client = mockk<ExperianClient>()
        val experianService = ExperianServiceImpl(client)

        val xmlError = dk.rki.webservices.firma.XmlError()
        xmlError.code = -1
        xmlError.text = "error text"
        xmlError.detailed = "error detailed"

        val registreringData = mockk<FirmaRegistreringData>(relaxed = true)
        every {
            registreringData.error
        } returns xmlError

        every {
            client.searchCreditHistoryByCvr(cvr)
        } returns registreringData


        val experianException = assertThrows(ExperianException::class.java) {
            experianService.queryCompanyPaymentRemarks(cvr)
        }

        assertEquals(xmlError.code, experianException.code)
        assertEquals(xmlError.text, experianException.text)
        assertEquals(xmlError.detailed, experianException.detailed)

        verify(exactly = 1) {
            client.searchCreditHistoryByCvr(cvr)
        }
    }
}
