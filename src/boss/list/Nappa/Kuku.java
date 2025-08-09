package boss.list.Nappa;

/*
 *
 *
 * @author YourSoulMatee
 */
import boss.Boss;
import boss.BossID;
import boss.BossStatus;
import boss.BossesData;
import clan.Clan;
import map.ItemMap;
import player.Player;
import services.Service;
import utils.Util;

public class Kuku extends Boss {

    private long st;

    public Kuku() throws Exception {
        super(BossID.KUKU, true, true, BossesData.KUKU);
    }
  @Override
    public void joinMap() {
        super.joinMap();
        st = System.currentTimeMillis();
    }
    @Override
    public void reward(Player plKill) {
        super.reward(plKill);
        if (this.currentLevel == 1) {
            return;
        }
    }
    @Override
    public void autoLeaveMap() {
        if (Util.canDoWithTime(st, 900000)) {
            this.changeStatus(BossStatus.LEAVE_MAP);
        }
//        if (this.zone != null && this.zone.getNumOfPlayers() > 0) {
//            st = System.currentTimeMillis();
//        }
    }
}
