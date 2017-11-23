package org.cruzcampo.gatling.grpc

import io.gatling.core.action.builder.ActionBuilder
import org.cruzcampo.gatling.grpc.actions.GrpcExecutableAction
import org.cruzcampo.gatling.grpc.grpc.GrpcCheck

case class GrpcProcessBuilder(action: GrpcExecutableAction, checks: List[GrpcCheck] = Nil) extends GrpcCheckSupport {

  def check(grpcCheck: GrpcCheck*) = copy(checks = checks ::: grpcCheck.toList)

  def build(): ActionBuilder = GrpcActionBuilder(action, checks)
}