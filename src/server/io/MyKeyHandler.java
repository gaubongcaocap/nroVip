package server.io;

/*
 *
 *
 * @author YourSoulMatee
 */


import data.DataGame;
import network.inetwork.ISession;
import network.KeyHandler;
public class MyKeyHandler extends KeyHandler {

    @Override
    public void sendKey(ISession session) {
        super.sendKey(session);
        DataGame.sendDataImageVersion((MySession) session);
        DataGame.sendVersionRes((MySession) session);
    }

}
