package com.even.akka.service

import akka.actor.ActorRef
import akka.http.scaladsl.server.Directives.pathPrefix
import com.even.akka.route.KafkaRoute

import scala.concurrent.ExecutionContext

/**
  * Created by yidadi on 17-7-11.
  */
class HttpService(kafkaProducerActor: ActorRef)(implicit executionContext: ExecutionContext) {
  val kafkaRoute = new KafkaRoute(kafkaProducerActor)
  val routes = pathPrefix("api") {
      kafkaRoute.route
    }
}
