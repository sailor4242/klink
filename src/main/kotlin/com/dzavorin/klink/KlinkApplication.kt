package com.dzavorin.klink

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KlinkApplication

fun main(args: Array<String>) {
    SpringApplication.run(KlinkApplication::class.java, *args)
}
