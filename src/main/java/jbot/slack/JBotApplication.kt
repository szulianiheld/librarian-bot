package lunch.jbot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = arrayOf("me.ramswaroop.jbot", "jbot"))
open class JBotApplication {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(JBotApplication::class.java, *args)
        }
    }

}
