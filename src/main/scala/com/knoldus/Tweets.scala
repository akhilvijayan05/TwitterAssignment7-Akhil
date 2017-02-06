package com.knoldus

import twitter4j._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by akhil on 4/2/17.
  */
class Tweets {

  val log = Logger.getLogger(this.getClass)
  val count=100

  def getTweets(twitter: Twitter,query: Query):  Future[Int] = {
    /**
      * retrieving tweets
      */
      query.setCount(count)
    query.setSince("2016-02-5")
    val result = twitter.search(query)
    val iter = result.getTweets.iterator
    log.info("Tweets Count")
      Future {

    def iterate(sum: Int): Int = {

    if (iter.hasNext()) {

      val status = iter.next
      iterate(sum + 1)
    }
     else {
      sum
     }
    }

        iterate(0)
  }
  }

  def averageRetweets(twitter: Twitter,query: Query):  Future[Int]={
    /**
      * average retweets count
      */
    query.setCount(count)
    query.setSince("2017-02-5")
    val result = twitter.search(query)
    val iter = result.getTweets.iterator
    log.info("Average Retweets Count")
    Future {

      def iterate(sum: Int): Int = {

        if (iter.hasNext()) {

          val status = iter.next
          iterate(sum + status.getRetweetCount)
        }
        else
        {
          sum
        }
      }
      iterate(0)/count
    }
  }

  def averageLikes(twitter: Twitter,query: Query):  Future[Int]={
    /**
      * average likes count
      */
    query.setCount(count)
    query.setSince("2017-02-5")
    val result = twitter.search(query)
    val iter = result.getTweets.iterator
    log.info("Average Likes Count")
    Future {

      def iterate(sum: Int): Int = {

        if (iter.hasNext()) {

          val status = iter.next
          iterate(sum + status.getFavoriteCount)
        }
        else
        {
          sum
        }
      }

      iterate(0)/count
    }
  }
}
