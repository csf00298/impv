package com.test;

import akka.actor.ActorSystem;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2018/5/8.
 */
public class AkkademyDbTest {
    ActorSystem system = ActorSystem.create();

   /* @Test
    public void itShouldPlaceKeyValueFromSetMessageIntoMap(){
        TestActorRef<AkkademyDb> actorRef = TestActorRef.create(system, Props.create(AkkademyDb.class));
        actorRef.tell(new SetRequest("key","value"), ActorRef.noSender());

        AkkademyDb akkademyDb = actorRef.underlyingActor();
        assertEquals(akkademyDb.map.get("key"), "value");

    }*/
}
