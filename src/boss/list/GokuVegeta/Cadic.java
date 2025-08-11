package boss.list.GokuVegeta;

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
import services.EffectSkillService;
import utils.Util;

public class Cadic extends Boss {

    private long st;
    private long lastBodyChangeTime;

    public Cadic() throws Exception {
        super(BossID.CADIC_HUYDIET, false, true, BossesData.CADIC_HUYDIET);
    }

    @Override
    public void moveTo(int x, int y) {
        if (this.currentLevel == 1) {
            return;
        }
        super.moveTo(x, y);
    }

    @Override
    public void reward(Player plKill) {
        super.reward(plKill);
        if (this.currentLevel == 1) {
            return;
        }
    }

    @Override
    protected void notifyJoinMap() {
        if (this.currentLevel == 1) {
            return;
        }
        super.notifyJoinMap();
    }

    @Override
    public void joinMap() {
        super.joinMap();
        st = System.currentTimeMillis();
    }

    @Override
    public void autoLeaveMap() {
        if (Util.canDoWithTime(st, 900000)) {
            this.leaveMapNew();
        }
        if (this.zone != null && this.zone.getNumOfPlayers() > 0) {
            st = System.currentTimeMillis();
        }
    }

    @Override
    public void doneChatE() {
        System.out.println(this.parentBoss);
        System.out.println(this.parentBoss.bossAppearTogether);
        if (this.parentBoss == null || this.parentBoss.bossAppearTogether == null
                || this.parentBoss.bossAppearTogether[this.parentBoss.currentLevel] == null) {
            return;
        }
        for (Boss boss : this.parentBoss.bossAppearTogether[this.parentBoss.currentLevel]) {
            if (boss.id == BossID.GOKU_HUYDIET && !boss.isDie()) {
                boss.changeStatus(BossStatus.ACTIVE);
                break;
            }
        }
    }
}
