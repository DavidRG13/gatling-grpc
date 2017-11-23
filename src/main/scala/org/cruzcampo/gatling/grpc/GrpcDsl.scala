package org.cruzcampo.gatling.grpc

import io.gatling.core.action.builder.ActionBuilder

trait GrpcDsl extends GrpcCheckSupport{

  val GRPC = GrpcProtocolBuilder

  def grpcCall = GrpcProcessBuilder

  implicit def grpcProtocolBuilder2grpcProtocol(builder: GrpcProtocolBuilder): GrpcProtocol = builder.build()
  implicit def grpcProcessBuilder2ActionBuilder(builder: GrpcProcessBuilder): ActionBuilder = builder.build()

}
