package org.cruzcampo.gatling.grpc

import org.cruzcampo.gatling.grpc.clientside.GrpcCustomCheck

trait GrpcCheckSupport {
  def customCheck = GrpcCustomCheck
}
