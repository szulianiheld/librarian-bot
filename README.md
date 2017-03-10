# lunch-bot

Slack bot for handling food reservations at the office.

It's built on top of [JBot](https://github.com/ramswaroop/jbot) and is written in [Kotlin](https://kotlinlang.org/).

## How to run it
   
1. [Create a slack bot](https://my.slack.com/services/new/bot) and get your slack token.  
2. Paste the token in [application.properties](/jbot-example/src/main/resources/application.properties) file.  
3. Run the example application by running `JBotApplication` in your IDE or via commandline: 
```
$ mvn spring-boot:run
```
