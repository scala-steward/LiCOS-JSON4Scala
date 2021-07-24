package licos.protocol.element.auth.server2robot

import java.util.UUID

import licos.json.element.auth.server2robot.JsonAuthenticationRequestResponse
import licos.protocol.element.auth.AuthMessageProtocol
import play.api.libs.json.{JsValue, Json}

final case class AuthenticationRequestResponseProtocol(accessToken: UUID, response: String)
    extends AuthMessageProtocol {

  override def hashCode(): Int = 513001

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: AuthenticationRequestResponseProtocol =>
        protocol.accessToken == accessToken &&
          protocol.response == response
      case _ => false
    }
  }

  private lazy val json: Option[JsonAuthenticationRequestResponse] = {
    Some(
      new JsonAuthenticationRequestResponse(
        accessToken.toString,
        response
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object AuthenticationRequestResponseProtocol {

  def read(
      json: JsonAuthenticationRequestResponse
  ): Option[AuthenticationRequestResponseProtocol] = {
    Some(
      AuthenticationRequestResponseProtocol(
        UUID.fromString(json.accessToken),
        json.response
      )
    )
  }

}
