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

public class LiuLiu extends The23rdMartialArtCongress {

    public LiuLiu(Player player) throws Exception {
        super(PHOBAN, BossID.LIU_LIU, BossesData.LIU_LIU);
        this.playerAtt = player;
    }
}
