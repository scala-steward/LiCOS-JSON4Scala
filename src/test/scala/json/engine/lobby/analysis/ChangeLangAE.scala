package json.engine.lobby.analysis

import json.engine.lobby.LobbyBox
import json.engine.lobby.example.ChangeLang
import json.element.JsonTest
import licos.json.element.lobby.client2server.JsonChangeLang
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.ChangeLangAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class ChangeLangAE extends ChangeLangAnalysisEngine {
  override def process(box: BOX, changeLang: JsonChangeLang): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(ChangeLang.`type`)))
      case _ => Left(Json.toJson(changeLang))
    }
  }
}
