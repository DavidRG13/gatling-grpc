package org.cruzcampo.gatling.grpc

case class GrpcComponents(grpcProtocol: GrpcProtocol) extends ProtocolComponents {
  override def onStart: Option[(Session) => Session] = None

  override def onExit: Option[(Session) => Unit] = None
}
