package com.knoldus

/**
  * Created by akhil on 4/2/17.
  */
import java.io.File

import com.typesafe.config.ConfigFactory
import org.apache.log4j.Logger
import twitter4j.{Twitter, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

class TwitterConfiguration {

  val parsedConfig = ConfigFactory.parseFile(new File("/home/akhil/IdeaProjects/Twitter/src/main/resources/settings.conf"))
  val conf = ConfigFactory.load(parsedConfig)
  val consumerKey = conf.getString("add.consumerKey")
  val consumerSecretKey = conf.getString("add.consumerSecret")
  val accessToken = conf.getString("add.accessToken")
  val accessTokenSecret = conf.getString("add.accessTokenSecret")

  def getTwitterInstance: Twitter = {

    val cb = new ConfigurationBuilder()
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecretKey)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret)
    new TwitterFactory(cb.build()).getInstance
  }
}

