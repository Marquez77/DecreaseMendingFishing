package me.marquez.dmfm;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

@Mod(DecreaseMendingFishingMod.MODID)
public class DecreaseMendingFishingMod
{
    public static final String MODID = "decreasemendingfishing";
    private static final Logger LOGGER = LogUtils.getLogger();
    public DecreaseMendingFishingMod()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerFishing(ItemFishedEvent event) {
        event.getDrops().forEach((item) -> {
            if (item.equals(Items.ENCHANTED_BOOK) &&
                    item.getOrCreateTag().get("StoredEnchantments") != null &&
                    !item.getOrCreateTag().get("StoredEnchantments").getAsString().isEmpty() &&
                    item.getTag().get("StoredEnchantments").getAsString().contains("minecraft:mending")) {
                if ((double) ThreadLocalRandom.current().nextInt(10) < 9.0) {
                    LOGGER.info("[DecraseMendingFishing] " + event.getEntity().getDisplayName().getString() + "님이 수선을 얻으려고 했으나 실패했습니다.");
                    event.setCanceled(true);
                } else {
                    LOGGER.info("[DecraseMendingFishing] " + event.getEntity().getDisplayName().getString() + "님께서 수선을 얻으셨습니다.");
                }
            }

        });
    }
}
