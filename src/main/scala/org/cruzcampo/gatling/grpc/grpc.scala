package org.cruzcampo.gatling.grpc

import com.google.protobuf.GeneratedMessageV3
import io.gatling.commons.validation.Success
import io.gatling.core.check.{Check, Extender, Preparer}

// This object could be created by the user
object grpc extends grpcB[String]

trait grpcB[R <: GeneratedMessageV3] {

  val GrpcStringExtender: Extender[Check[R], R] = (check: Check[R]) => check

  val GrpcStringPreparer: Preparer[String, String] = (result: String) => Success(result)
}
