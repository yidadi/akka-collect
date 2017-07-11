package com.even.akka.util

import java.util.Properties

import com.typesafe.config.ConfigFactory

/**
  * Created by yidadi on 17-7-11.
  */
trait Config {
  private val config = ConfigFactory.load()
  private val httpConfig = config.getConfig("http")
  val httpHost = httpConfig.getString("interface")
  val httpPort = httpConfig.getInt("port")

  val props = new Properties()
  props.put("bootstrap.servers", config.getString("bootstrap.servers"));
  props.put("acks", "all");
  props.put("retries", config.getString("retries"));
  props.put("batch.size", config.getString("batch.size"));
  props.put("buffer.memory", config.getString("buffer.memory"));
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
}
