package org.chechtalks.lunchbot.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.chechtalks.lunchbot.social.FacebookHelper
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/*
This (re)generates a json with the latest posts from a Facebook page.
The json can be used in tests for mocking information.
FIXME: this ain't no test
*/
@RunWith(SpringRunner::class)
@SpringBootTest
class JsonFeedGenerator {

    @Autowired
    private lateinit var facebook: FacebookHelper

    @Test
    @Ignore("I need to find a proper approach for manually executing this")
    fun generate() {
        println(jacksonObjectMapper().writeValueAsString(facebook.facebook.feedOperations().getPosts("comerbientandil")))
        println(jacksonObjectMapper().writeValueAsString(facebook.facebook.feedOperations().getPosts("CocinaSoho")))
    }
}

