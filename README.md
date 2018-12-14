# librarian-bot 

[![Kotlin 1.1](https://img.shields.io/badge/Kotlin-1.1.161-blue.svg)](http://kotlinlang.org)
[![Build Status](https://travis-ci.org/chechtalks/lunch-bot.svg?branch=develop)](https://travis-ci.org/chechtalks/lunch-bot)
![Heroku](http://heroku-badge.herokuapp.com/?app=morfi-bot&style=flat&svg=1&root=health)

Bot to manage the request and inventory of books in Tandil Office.

It's written in [Kotlin](https://kotlinlang.org/) and consumes Slack API through [JBot](https://github.com/ramswaroop/jbot) and [JSlack](https://github.com/seratch/jslack).

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

> You can also put your token in [application.properties](/jbot-example/src/main/resources/application.properties) and avoid passing it through command line. However, be aware that if you push it to Github your token will be disabled for security reasons.

### Run with Docker

```bash
$ docker pull szulianiheld/librarian-bot:latest
$ docker run -p 8080:8080 -d -e "slackBotToken=[your-token-here]" szulianiheld/librarian-bot
```