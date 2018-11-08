package com.impv.test.service

;

import akka.actor.{Actor, ActorSystem, Props, Status}
import akka.event.Logging
import com.impv.test.entity.{DBEntity, SetRequestS}

import scala.collection.mutable

class AkkademyDbS extends Actor {
  val map = new mutable.HashMap[String, String]
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case DBEntity.SetRequest(key, value) =>
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
      sender() ! Status.Success
    case DBEntity.GetResponse(key) =>
      log.info("received GetRequest - key: {}", key)
      val response: Option[String] = map.get(key)
      response match {
        case Some(x) => sender() ! x
        case None => sender() ! Status.Failure(DBEntity.KeyNotFoundException(key))
      }
    case o => Status.Failure(DBEntity.ClassNotFoundException())
  }

}


