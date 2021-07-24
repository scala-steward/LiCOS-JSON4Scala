package licos.protocol.element.lobby.part

import java.util.UUID

import licos.json.element.lobby.server2client.JsonPingResult
import licos.knowledge.{Data2Knowledge, PingStatus}

final case class PingResultProtocol(token: UUID, ping: String, status: PingStatus) {

  override def hashCode(): Int = 522004

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: PingResultProtocol =>
        protocol.token == token &&
          protocol.ping == ping &&
          protocol.status == status
      case _ => false
    }
  }

  lazy val json: Option[JsonPingResult] = {
    Some(
      JsonPingResult(
        token.toString,
        ping,
        status.label
      )
    )
  }

}

object PingResultProtocol {

  def read(json: JsonPingResult): Option[PingResultProtocol] = {
    Data2Knowledge
      .pingStatusOpt(json.status)
      .map { status: PingStatus =>
        PingResultProtocol(
          UUID.fromString(json.token),
          json.ping,
          status
        )
      }
  }

}
