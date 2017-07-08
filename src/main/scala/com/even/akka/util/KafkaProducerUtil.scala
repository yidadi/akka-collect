package com.even.akka.util

import java.util.Properties

import com.even.akka.api.MsgDto
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.slf4j.LoggerFactory

/**
  * Created by yiwenxiang on 2017/7/8.
  */
object KafkaProducerUtil {
  val log = LoggerFactory.getLogger(KafkaProducerUtil.getClass)
  var kafkaProducer:KafkaProducer[String,String] = _

  /**
    * 初始化kafkaproducer
    * @param properties
    * @return
    */
  def create(properties:Properties): KafkaProducer[String,String] ={
    log.info("init success")
    kafkaProducer = new KafkaProducer[String,String](properties)
    kafkaProducer
  }

  /**
    *用于向kafka中发送消息
    * @param msgDto
    */
  def send(msgDto: MsgDto): Unit ={
    try kafkaProducer.send(new ProducerRecord[String,String](msgDto.topic,msgDto.content))
    log.info(s"msg send success $msgDto")
    catch {
      case e : Exception => log.error(e.getMessage,e)
    }
  }
}
