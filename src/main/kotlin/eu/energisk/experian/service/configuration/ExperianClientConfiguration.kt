package eu.energisk.experian.service.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class ExperianClientConfiguration(
    @Value("\${experian.client.username}") val username: String,
    @Value("\${experian.client.password}") val password: String
)
