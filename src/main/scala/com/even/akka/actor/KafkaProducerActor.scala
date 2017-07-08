package com.even.akka.actor

import akka.actor.Actor
import akka.event.Logging
import com.even.akka.api.MsgDto
import com.even.akka.util.KafkaProducerUtil

/**
  * Created by yiwenxiang on 2017/7/8.
  */
class KafkaProducerActor extends Actor{
  val logger = Logging(context.system, this)

  override def receive: Receive = {
    case MsgDto(topic,content) => KafkaProducerUtil.send(MsgDto(topic,content))
    case _ =>logger.error("the msg is not match the msgDto")
  }
}
