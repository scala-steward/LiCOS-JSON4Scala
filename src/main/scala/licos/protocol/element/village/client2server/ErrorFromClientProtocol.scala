package licos.protocol.element.village.client2server

import licos.entity.{VillageInfo, VillageInfoFactory, VillageInfoFromLobby}
import licos.json.element.village.JsonError
import licos.knowledge.{Data2Knowledge, Severity}
import licos.protocol.element.village.part.NameProtocol
import play.api.libs.json.{JsValue, Json}

final case class ErrorFromClientProtocol(
    village:  VillageInfo,
    content:  NameProtocol,
    severity: Severity,
    source:   String
) extends Client2ServerVillageMessageProtocol {

  private val json: Option[JsonError] = {
    server2logger.ErrorFromClientProtocol(village, content, severity, source, Nil).json
  }

  override def toJsonOpt: Option[JsValue] = json.map { j =>
    Json.toJson(j)
  }
}

object ErrorFromClientProtocol {

  def read(json: JsonError, villageInfoFromLobby: VillageInfoFromLobby): Option[ErrorFromClientProtocol] = {
    VillageInfoFactory
      .create(villageInfoFromLobby, json.base)
      .flatMap { village: VillageInfo =>
        Data2Knowledge
          .severityOpt(json.severity)
          .map { severity: Severity =>
            ErrorFromClientProtocol(
              village,
              Data2Knowledge.name(json.content),
              severity,
              json.source
            )
          }
      }
  }

}
