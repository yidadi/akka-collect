package com.even.kafka

import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer

/**
  * Created by yidadi on 17-7-10.
  */
object KafkaConsumerTest {
  def main(args: Array[String]): Unit = {
    val props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("group.id", "test");
    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    val  consumer = new KafkaConsumer[String,String](props)
    consumer.subscribe(util.Arrays.asList("test"));
    while (true) {
      val it = consumer.poll(100).iterator()
      while(it.hasNext)
      println(it.next().value())
      }
  }
}
