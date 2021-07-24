package licos.protocol.element.lobby.client2server

import licos.json.element.lobby.client2server.JsonChangeUserPassword
import play.api.libs.json.{JsValue, Json}

final case class ChangeUserPasswordProtocol(userPassword: String) extends Client2ServerLobbyMessageProtocol {

  override def hashCode(): Int = 521008

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: ChangeUserPasswordProtocol =>
        protocol.userPassword == userPassword
      case _ => false
    }
  }

  private lazy val json: Option[JsonChangeUserPassword] = {
    Some(
      new JsonChangeUserPassword(
        userPassword
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object ChangeUserPasswordProtocol {

  def read(json: JsonChangeUserPassword): Option[ChangeUserPasswordProtocol] = {
    Some(
      ChangeUserPasswordProtocol(
        json.userPassword
      )
    )
  }

}
