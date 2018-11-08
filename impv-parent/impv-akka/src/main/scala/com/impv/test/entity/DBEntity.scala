package com.impv.test.entity

object DBEntity {
  case class SetRequest(key:String,value:String)
  case class GetResponse(key:String)
  case class KeyNotFoundException(key:String) extends Exception
  case class ClassNotFoundException() extends Exception
}
