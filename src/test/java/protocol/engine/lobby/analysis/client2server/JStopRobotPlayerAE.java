package protocol.engine.lobby.analysis.client2server;

import static protocol.engine.lobby.example.client2server.StopRobotPlayer$.MODULE$;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.client2server.StopRobotPlayerProtocol;
import licos.protocol.engine.analysis.lobby.client2server.StopRobotPlayerAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JStopRobotPlayerAE implements StopRobotPlayerAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, StopRobotPlayerProtocol stopRobotPlayerProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(MODULE$.type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
