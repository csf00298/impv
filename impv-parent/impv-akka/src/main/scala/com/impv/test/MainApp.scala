package com.impv.test

import akka.actor.{ActorSystem, Props}
import com.impv.test.service.AkkademyDbS

object MainApp extends App{
  val system = ActorSystem("akkadem")

  import akka.actor.Props
  system.actorOf(Props[AkkademyDbS], name = "akkademy-db")
}
