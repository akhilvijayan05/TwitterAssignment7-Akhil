package com.knoldus

import org.scalatest.FunSuite
import twitter4j.Query

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by akhil on 4/2/17.
  */
class TwitterMain extends FunSuite{

  val query = new Query("#Scala")
  val twitter=new TwitterConfiguration
  val tweets=new Tweets
  val twitterObject=twitter.getTwitterInstance

   test("Number of Tweets"){

     assert(Await.result(tweets.getTweets(twitterObject,query),10.second)==100)
   }
  test("Number of Reweets"){
    assert(Await.result(tweets.averageRetweets(twitterObject,query),10.second)==2)
  }
  test("Number of Likes"){
    assert(Await.result(tweets.averageLikes(twitterObject,query),10.second)==0)
  }
}
