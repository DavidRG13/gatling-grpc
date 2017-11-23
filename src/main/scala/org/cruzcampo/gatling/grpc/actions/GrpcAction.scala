package org.cruzcampo.gatling.grpc.actions

import akka.actor.ActorSystem
import com.google.protobuf.GeneratedMessageV3
import io.gatling.core.action.{Action, ExitableActorDelegatingAction}
import io.gatling.core.check.Check
import io.gatling.core.stats.StatsEngine
import io.gatling.core.util.NameGen
import org.cruzcampo.gatling.grpc.GrpcProtocol

/**
  * Action that will create ActionActor and pass action class that will be triggered during the test
  */
object GrpcAction extends NameGen {
  /**
    * @param action      - that which execSync or execAsync method will be called during the test (see GrpcActionActor)
    * @param checks      - what kind of result validations will be executed
    * @param protocol    - gRPC protocol
    * @param system      - actor system
    * @param statsEngine - engine to write results to
    * @param next        - next Action to be executed
    * @return            - ExitableActorDelegatingAction
    */
  def apply[R](action: GrpcExecutableAction, checks: List[Check[_ <: GeneratedMessageV3]], protocol: GrpcProtocol, system: ActorSystem, statsEngine: StatsEngine, next: Action) = {
    val actor = system.actorOf(GrpcActionActor.props(action, checks, protocol, statsEngine, next))
    new ExitableActorDelegatingAction(genName("Grpc"), statsEngine, next, actor)
  }
}
