package org.tino.librarianbot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan

@SpringBootApplication(scanBasePackages = arrayOf("me.ramswaroop.jbot", "org.tino.librarianbot"))
@EntityScan("org.tino.librarianbot.*")
class JBotApplication

fun main(args: Array<String>) {
    SpringApplication.run(JBotApplication::class.java, *args)
}