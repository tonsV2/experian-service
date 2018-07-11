package eu.energisk.experian.service

class ExperianException(val code: Int, val text: String, val detailed: String) : RuntimeException()
