package npc.list;

import consts.ConstNpc;
import consts.ConstTask;
import npc.Npc;
import player.Player;
import services.*;
import shop.ShopService;

public class Berry extends Npc {

    public Berry(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (TaskService.gI().getIdTask(player) == ConstTask.TASK_31_5) {
                this.createOtherMenu(player, ConstNpc.BASE_MENU, "Bạn sẽ mang tôi về Bardock thật sao", "OK");
            } else if (mapId == 5) {
                createOtherMenu(player, ConstNpc.BASE_MENU,
                        "Trông ngươi thật là mạnh, ngươi muốn mua gì\n"
                                            + "Ngươi đang có " + player.event.getVnd() + " VNĐ",
                        "Cửa Hàng", "Từ chối");
            } else {
                super.openBaseMenu(player); // Gọi phương thức của lớp cha nếu không phải TASK_31_5
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (player.iDMark.isBaseMenu()) {
                switch (this.mapId) {
                    case 5 -> {
                        if (select == 0) {
                            ShopService.gI().opendShop(player, "BERRY", true);
                            break;
                        }
                    }
                    default -> {
                        switch (select) {
                            case 0 ->
                                TaskService.gI().checkDoneTask31(player);
                            default -> {
                            }
                        }
                    }
                }
            }
        }
    }
}
