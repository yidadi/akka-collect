package com.even.akka.route

import akka.actor.ActorRef
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives
import com.even.akka.util.KafkaProducerUtil
import spray.json.DefaultJsonProtocol

import scala.concurrent.ExecutionContext

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val itemFormat = jsonFormat2(MsgDto)
}

case class MsgDto(topic : String,content : String)
/**
  * Created by yidadi on 17-7-11.
  */
class KafkaRoute(kafkaProducerActor: ActorRef)(implicit executionContext: ExecutionContext) extends Directives with JsonSupport  {

  val route =
    path("msg") {
      post {
        entity(as[MsgDto]) { msgDto =>
          println(s"msgDto $msgDto")
          kafkaProducerActor ! msgDto
          complete("success")
        }
      }
    }
}


