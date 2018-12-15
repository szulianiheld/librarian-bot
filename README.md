# librarian-bot 

[![Kotlin 1.3.11](https://img.shields.io/badge/Kotlin-1.3.11-blue.svg)](http://kotlinlang.org)
[![Build Status](https://travis-ci.org/szulianiheld/librarian-bot.svg?branch=master)](https://travis-ci.org/szulianiheld/librarian-bot)

Bot to manage the request and inventory of books in Tandil Office.

It's written in [Kotlin](https://kotlinlang.org/) and consumes Slack API through [JBot](https://github.com/ramswaroop/jbot) and [JSlack](https://github.com/seratch/jslack).

## How to run it

Beforehand [create a Slack bot](https://my.slack.com/services/new/bot) in your team and get your token.

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