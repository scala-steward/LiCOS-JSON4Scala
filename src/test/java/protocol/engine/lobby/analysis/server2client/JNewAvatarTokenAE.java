package protocol.engine.lobby.analysis.server2client;

import static protocol.engine.lobby.example.server2client.NewAvatarToken$.MODULE$;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.server2client.NewAvatarTokenProtocol;
import licos.protocol.engine.analysis.lobby.server2client.NewAvatarTokenAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JNewAvatarTokenAE implements NewAvatarTokenAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, NewAvatarTokenProtocol newAvatarTokenProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(MODULE$.type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
