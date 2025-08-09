package boss.list.NamekGinyuForce;

/*
 *
 *
 * @author YourSoulMatee
 */

import boss.Boss;
import boss.BossID;
import boss.BossStatus;
import boss.BossesData;
import item.Item;
import java.util.List;
import map.ItemMap;
import player.Player;
import services.ItemService;
import services.Service;
import utils.Util;

public class TDT_NM extends Boss {

    private long st;

    public TDT_NM() throws Exception {
        super(BossID.TIEU_DOI_TRUONG_NM, false, true, BossesData.TIEU_DOI_TRUONG_NM);
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
        Service.gI().dropItemMap(this.zone, new ItemMap(zone, 77, Util.nextInt(1, 2), this.location.x + Util.nextInt(-50, 50), this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id));
        for (int i = 0; i < Util.nextInt(2); i++) {
            Service.gI().dropItemMap(this.zone, new ItemMap(zone, 77, Util.nextInt(1, 3), this.location.x + i * Util.nextInt(-50, 50), this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id));
        }
        for (int i = 0; i < Util.nextInt(3, 4); i++) {
            Service.gI().dropItemMap(this.zone, new ItemMap(zone, 77, Util.nextInt(1, 4), this.location.x + i * 10, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id));
        }
        for (int i = 1; i < Util.nextInt(3, 3) + 1; i++) {
            Service.gI().dropItemMap(this.zone, new ItemMap(zone, 77, Util.nextInt(1, 5), this.location.x - i * 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id));
        }
        short itTemp = 429;
        short nr6s = 19;
        short nr7s = 20;
        ItemMap it = new ItemMap(zone, itTemp, 1, this.location.x + Util.nextInt(-50, 50), this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
        ItemMap it1 = new ItemMap(zone, nr6s, 1, this.location.x + Util.nextInt(-50, 50), this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
        ItemMap it2 = new ItemMap(zone, nr7s, 1, this.location.x + Util.nextInt(-50, 50), this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);

        List<Item.ItemOption> ops = ItemService.gI().getListOptionItemShop(itTemp);
        if (!ops.isEmpty()) {
            it.options = ops;
        }
        Service.gI().dropItemMap(this.zone, it);
        Service.gI().dropItemMap(this.zone, it1);
        Service.gI().dropItemMap(this.zone, it2);
            int diem = 20;
    plKill.event.setEventPoint(diem);
    Service.gI().sendThongBao(plKill, "+20 Point");
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
    public void doneChatS() {
        this.changeStatus(BossStatus.AFK);
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
}
