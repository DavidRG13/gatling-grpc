# How to use it

```scala
import io.gatling.commons.validation.{Failure, Validation}
import io.gatling.core.check.{Check, CheckResult}
import io.gatling.core.session.Session

import scala.collection.mutable

/**
  * Simple match class, checking if response message (GeneratedMessage) satisfy checker function.
  * It is possible to write more complex checkers in case they are needed.
  * @param func
  */
case class GrpcCustomCheck(func: String => Boolean) extends Check[String] {
  override def check(response: String, session: Session)(implicit cache: mutable.Map[Any, Any]): Validation[CheckResult] = {
    func(response) match {
      case true => CheckResult.NoopCheckResultSuccess
      case _ => Failure("Grpc check failed")
    }
  }
}
```

And
```scala
import org.cruzcampo.gatling.grpc.actions.GrpcExecutableAction

object GrpcSyncCallAction {
  /**
    *
    * Constructor that needs couple of params in order to create valid gRPC connection
    * @param name           - function name
    * @param host           - server host
    * @param port           - server port
    * @param toBeSend       - message to be send as request
    * @return               - GrpcSyncCallAction
    */
  def apply(name: String, host: String, port: Int, toBeSend: GetEmployeeByIdRequest): GrpcSyncCallAction = new GrpcSyncCallAction(name, host, port, toBeSend)
}

class GrpcSyncCallAction(val name: String, host: String, port: Int, toBeSend: GetEmployeeByIdRequest) extends GrpcExecutableAction {

  var channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build
  val blockingCall = EmployeesServiceGrpc.newBlockingStub(channel)

  override def executeSync = {
    try
      Some(blockingCall.getEmployeeById(toBeSend))
    catch {
      case e: StatusRuntimeException =>
        System.out.println("RPC failed: " + e.getStatus)
        null
    }
  }
}

```