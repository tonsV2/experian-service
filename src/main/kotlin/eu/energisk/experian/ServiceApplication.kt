package eu.energisk.experian

import com.sun.xml.internal.ws.client.BindingProviderProperties
import dk.rki.webservices.firma.Firma
import dk.rki.webservices.firma.FirmaSoap
import dk.rki.webservices.person.Person
import dk.rki.webservices.person.PersonSoap
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import javax.xml.ws.BindingProvider

@SpringBootApplication
class ServiceApplication {
    @Bean
    fun personClient(): PersonSoap {
        val client = Person().personSoap

        configureClientTimeOut(client)

        return client
    }

    @Bean
    fun firmaClient(): FirmaSoap {
        val client = Firma().firmaSoap

        configureClientTimeOut(client)

        return client
    }

    private fun configureClientTimeOut(client: Any) {
        if (client is BindingProvider) {
            val requestContext = client.requestContext

            requestContext[BindingProviderProperties.CONNECT_TIMEOUT] = 10000
            requestContext["javax.xml.ws.client.connectionTimeout"] = 10000

            requestContext[BindingProviderProperties.REQUEST_TIMEOUT] = 25000
            requestContext["javax.xml.ws.client.receiveTimeout"] = 25000
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ServiceApplication>(*args)
}
