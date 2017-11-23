package org.cruzcampo.gatling.grpc

import com.google.protobuf.GeneratedMessageV3
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.check.Check
import org.cruzcampo.gatling.grpc.actions.GrpcExecutableAction
import org.cruzcampo.gatling.grpc.grpc.GrpcCheck

case class GrpcProcessBuilder(action: GrpcExecutableAction, checks: List[Check[R <: GeneratedMessageV3]] = Nil) extends GrpcCheckSupport {

  def check(grpcCheck: Check[R <: GeneratedMessageV3]*) = copy(checks = checks ::: grpcCheck.toList)

  def build(): ActionBuilder = GrpcActionBuilder(action, checks)
}