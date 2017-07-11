package com.even.akka

import akka.actor.{ActorSystem, Props}
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.even.akka.actor.KafkaProducerActor
import com.even.akka.service.HttpService
import com.even.akka.util.{Config, KafkaProducerUtil}

import scala.concurrent.ExecutionContext

/**
  * Created by yidadi on 17-7-11.
  */
object Main extends App with Config {
  implicit val actorSystem = ActorSystem("kafka")
  implicit val executor: ExecutionContext = actorSystem.dispatcher
  implicit val log: LoggingAdapter = Logging(actorSystem, getClass)
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  val kafkaProducerUtil = KafkaProducerUtil.create(props)
  val kafkaProducerActor = actorSystem.actorOf(Props[KafkaProducerActor], name="KafkaProducerActor")
  val httpService = new HttpService(kafkaProducerActor)

  Http().bindAndHandle(httpService.routes, httpHost, httpPort)
}
