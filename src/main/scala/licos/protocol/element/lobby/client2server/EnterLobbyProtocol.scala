package licos.protocol.element.lobby.client2server

import java.util.UUID

import licos.json.element.lobby.client2server.JsonEnterLobby
import licos.knowledge.{Data2Knowledge, Lobby}
import play.api.libs.json.{JsValue, Json}

final case class EnterLobbyProtocol(token: UUID, lobby: Lobby, page: Int) extends Client2ServerLobbyMessageProtocol {

  override def hashCode(): Int = 521014

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: EnterLobbyProtocol =>
        protocol.token == token &&
          protocol.lobby == lobby &&
          protocol.page == page
      case _ => false
    }
  }

  private lazy val json: Option[JsonEnterLobby] = {
    Some(
      new JsonEnterLobby(
        token.toString,
        lobby.label,
        page
      )
    )
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object EnterLobbyProtocol {

  def read(json: JsonEnterLobby): Option[EnterLobbyProtocol] = {
    Data2Knowledge
      .lobbyOpt(json.lobby)
      .map { lobby: Lobby =>
        EnterLobbyProtocol(
          UUID.fromString(json.token),
          lobby,
          json.page
        )
      }
  }

}
