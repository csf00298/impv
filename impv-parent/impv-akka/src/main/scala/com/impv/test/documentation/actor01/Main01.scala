package com.impv.test.documentation.actor01

import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import language.postfixOps
import scala.concurrent.duration._

/**
  * @Description:官网学习
  */
object Main01 extends App {
  case object Ping
  case object Pong

  val system = ActorSystem("PingPong")
  val pinger = system.actorOf(Props[Pinger],"pinger")
  val ponger = system.actorOf(Props (classOf[Ponger],pinger),"ponger")
  import system.dispatcher
  system.scheduler.scheduleOnce(1000 millis) {
    ponger ! Ping
  }

  class Pinger extends Actor {
    var countDown = 100
    override def receive: Receive = {
      case Pong =>
        println(s"${self.path} received pong, count down $countDown")
        if (countDown > 0) {
          countDown -= 1
          sender() ! Ping
        } else {
          sender() ! PoisonPill
          self ! PoisonPill
        }

    }
  }

  class Ponger(pinger: ActorRef) extends Actor {
    override def receive: Receive = {
      case Ping =>
        println(s"${self.path} received ping")
        pinger ! Pong
    }
  }


}
