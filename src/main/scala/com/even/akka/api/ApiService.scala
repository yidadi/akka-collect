package com.even.akka.api

/**
  * Created by yiwenxiang on 2017/7/8.
  */
class ApiService {

}


/**
  * 用于客户端采集系统采集数据发送的格式定义
  * @param topic 发送到kafka topic 主题
  * @param content 发送的消息内容 json格式 采用的json工具 fastjson
  */
case class MsgDto(topic : String,content : String)