package dulkirmod.mixins;


import dulkirmod.DulkirMod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin({EntityLivingBase.class})
public abstract class MixinEntityLivingBase extends MixinEntity {

    @Shadow
    public abstract boolean isPotionActive(Potion potionIn);

    @Shadow
    public abstract PotionEffect getActivePotionEffect(Potion potionIn);

    @Shadow
    public abstract ItemStack getHeldItem();

    @Inject(method = "getArmSwingAnimationEnd()I", at = @At("HEAD"), cancellable = true)
    public void adjustSwingLength(CallbackInfoReturnable<Integer> cir) {
        if (!DulkirMod.Companion.getConfig().getCustomAnimations()) return;
        boolean ignoreHaste = DulkirMod.Companion.getConfig().getIgnoreHaste();
        float customSpeed = DulkirMod.Companion.getConfig().getCustomSpeed();
        if (DulkirMod.Companion.getConfig().getWhitelistEnabled()) {
            ItemStack stack = this.getHeldItem();
            if (stack == null) return;
            if (!stack.hasTagCompound()) return;
            NBTTagCompound tag = stack.getTagCompound();
            if (!tag.hasKey("ExtraAttributes", 10) || !tag.hasKey("display", 10)) return;
            NBTTagCompound ea = tag.getCompoundTag("ExtraAttributes");
            if (!ea.hasKey("id", 8)) return;
            String id = ea.getString("id");
            if (Arrays.asList(DulkirMod.Companion.getConfig().getCustomItemIds().toUpperCase().split(",")).contains(id)) {
                ignoreHaste = DulkirMod.Companion.getConfig().getIgnoreOverrideHaste();
                customSpeed = DulkirMod.Companion.getConfig().getCustomOverrideSpeed();
            }
        }
        int length = ignoreHaste ? 6 : this.isPotionActive(Potion.digSpeed) ?
                6 - (1 + this.getActivePotionEffect(Potion.digSpeed).getAmplifier()) :
                (this.isPotionActive(Potion.digSlowdown) ?
                        6 + (1 + this.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2 :
                        6);
        cir.setReturnValue(Math.max((int) (length * Math.exp(-customSpeed)), 1));
    }
}
