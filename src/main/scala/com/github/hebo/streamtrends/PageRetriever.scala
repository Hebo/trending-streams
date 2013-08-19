package com.github.hebo.streamtrends

import akka.actor.ActorSystem
import scala.concurrent.{Future, ExecutionContext, Promise}

import org.scalatra.FutureSupport

import dispatch._
import org.scalatra._

import scalaz._, Scalaz._
import argonaut._, Argonaut._

import TwitchStream._

object Dispatch {

  def retrievePage()(implicit ctx: ExecutionContext): Future[String] = {
    val prom = Promise[String]()
    dispatch.Http(url("https://api.twitch.tv/kraken/streams") OK as.String) onComplete {
      case r => prom.complete(r)
    }
    prom.future
  }
}

class PageRetriever(system: ActorSystem) extends ScalatraServlet with FutureSupport {

  protected implicit def executor: ExecutionContext = system.dispatcher

  get("/") {
    new AsyncResult { val is =
      Dispatch.retrievePage()
    }
  }

}
