package boss.list.The23rdMartialArtCongress;

/*
 *
 *
 * @author YourSoulMatee
 */

import boss.BossID;
import boss.BossesData;
import static boss.BossType.PHOBAN;
import player.Player;

public class ChaPa extends The23rdMartialArtCongress {

    public ChaPa(Player player) throws Exception {
        super(PHOBAN, BossID.CHA_PA, BossesData.CHA_PA);
        this.playerAtt = player;
    }
}
