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
import player.Player;
import utils.Util;

public class Rambo extends Boss {

    private long st;

    public Rambo() throws Exception {
        super(BossID.RAMBO, true, true, BossesData.RAMBO);
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
