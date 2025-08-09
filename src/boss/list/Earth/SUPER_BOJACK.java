package boss.list.Earth;

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

public class SUPER_BOJACK extends Boss {

    private long st;

    public SUPER_BOJACK() throws Exception {
        super(BossID.SUPER_BOJACK, false, true, BossesData.SUPER_BOJACK_2);
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
        Service.gI().dropItemMap(this.zone,
                new ItemMap(zone,
                        77,
                        Util.nextInt(10, 40),
                        this.location.x,
                        this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24),
                        plKill.id));
        short itTemp = 428;
        ItemMap it = new ItemMap(zone, itTemp, 1, this.location.x + Util.nextInt(-50, 50), this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
        List<Item.ItemOption> ops = ItemService.gI().getListOptionItemShop(itTemp);
        if (!ops.isEmpty()) {
            it.options = ops;
        }
        Service.gI().dropItemMap(this.zone, it);
        short goldItemId = 190;
        int goldQuantity = Util.nextInt(10_000_000, 20_000_000); // Từ 10 triệu đến 20 triệu.
        Service.gI().dropItemMap(this.zone, new ItemMap(
                zone,
                goldItemId,
                goldQuantity,
                this.location.x + Util.nextInt(-30, 30),
                this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24),
                plKill.id
        ));
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
}
