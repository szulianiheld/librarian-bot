package org.chechtalks.lunchbot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = arrayOf("me.ramswaroop.jbot", "org.chechtalks.lunchbot"))
class JBotApplication

fun main(args: Array<String>) {
    SpringApplication.run(JBotApplication::class.java, *args)
}