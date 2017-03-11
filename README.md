# lunch-bot 

[![Build Status](https://travis-ci.org/chechtalks/lunch-bot.svg?branch=develop)](https://travis-ci.org/chechtalks/lunch-bot)

Slack bot for handling food reservations at the office.

It's built on top of [JBot](https://github.com/ramswaroop/jbot) and is written in [Kotlin](https://kotlinlang.org/).

## How to run it

Beforehand [create a Slack bot](https://my.slack.com/services/new/bot) in your team and get your token.

### Deploy to Heroku

1. [![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)
2. Make sure you set a *Heroku configuration variable* with the key `slackBotToken` and your token as value.

### Run locally

1. Clone this repo.
2. Execute
```bash
$ ./gradlew bootRun -DslackBotToken=[your-token-here]
```

> You can also put your tocken in [application.properties](/jbot-example/src/main/resources/application.properties) and avoid passing it through command line. However, be aware that if you push it to Github your token will be disabled for security reasons.