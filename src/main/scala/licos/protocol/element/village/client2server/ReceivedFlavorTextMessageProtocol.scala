package licos.protocol.element.village.client2server

import java.util.UUID

import licos.json.element.village.receipt.JsonReceivedFlavorTextMessage
import licos.knowledge.{Data2Knowledge, Phase}
import play.api.libs.json.{JsValue, Json}

final case class ReceivedFlavorTextMessageProtocol(token: UUID, villageId: Long, phase: Phase, day: Int)
    extends ReceivedMessageProtocol {

  override def hashCode(): Int = 532012

  override def equals(obj: Any): Boolean = {
    obj match {
      case protocol: ReceivedFlavorTextMessageProtocol =>
        protocol.token == token &&
          protocol.villageId == villageId &&
          protocol.phase == phase &&
          protocol.day == day
      case _ => false
    }
  }

  private lazy val json: Option[JsonReceivedFlavorTextMessage] = {
    Some(new JsonReceivedFlavorTextMessage(token.toString, villageId, phase.label, day))
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object ReceivedFlavorTextMessageProtocol {

  def read(json: JsonReceivedFlavorTextMessage): Option[ReceivedFlavorTextMessageProtocol] = {
    Data2Knowledge.phaseOpt(json.phase).map { phase: Phase =>
      ReceivedFlavorTextMessageProtocol(UUID.fromString(json.token), json.villageId, phase, json.day)
    }
  }

}
