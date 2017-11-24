package org.cruzcampo.gatling.grpc

import com.google.protobuf.GeneratedMessageV3
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.check.Check
import org.cruzcampo.gatling.grpc.actions.GrpcExecutableAction

case class GrpcProcessBuilder(action: GrpcExecutableAction, checks: List[Check[_ <: GeneratedMessageV3]] = Nil) {

  def check(grpcCheck: Check[_ <: GeneratedMessageV3]*) = copy(checks = checks ::: grpcCheck.toList)

  def build(): ActionBuilder = GrpcActionBuilder(action, checks)
}