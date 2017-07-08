package com.even.akka

import java.util.Properties

import akka.actor.{ActorSystem, Props}
import com.even.akka.actor.KafkaProducerActor
import com.even.akka.util.KafkaProducerUtil
import com.typesafe.config.ConfigFactory

/**
  * Created by yiwenxiang on 2017/7/8.
  */
object Application {
  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()
    //init kafka
    val props = new Properties()
    props.put("bootstrap.servers", config.getString("bootstrap.servers"));
    props.put("acks", "all");
    props.put("retries", config.getString("retries"));
    props.put("batch.size", config.getString("batch.size"));
    props.put("buffer.memory", config.getString("buffer.memory"));
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    KafkaProducerUtil.create(props)

    //init akka
    val actorSystem = ActorSystem("KafkaProducerActor")
    val KafkaProducerActor = actorSystem.actorOf(Props[KafkaProducerActor], name="KafkaProducerActor")
    //init api


  }
}
