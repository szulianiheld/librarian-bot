# lunch-bot

Slack bot for handling food reservations at the office.

It's built on top of [JBot](https://github.com/ramswaroop/jbot) and is written in [Kotlin](https://kotlinlang.org/).

## How to run it
   
1. [Create a slack bot](https://my.slack.com/services/new/bot) and get your slack token.  
2. Run the example application by running `JBotApplication` in your IDE or via commandline: 
```
$ mvn spring-boot:run -DslackBotTocker=[your-tocken-here]
```

> You can also put your tocken in [application.properties](/jbot-example/src/main/resources/application.properties) and avoid passing it through command line. However, be aware that if you push it to Github your token will be disabled for security reasons.