package dulkirmod.utils

import dulkirmod.config.DulkirConfig

data class ConfigData(
    val size: Float = DulkirConfig.customSize,
    val scaleSwing: Boolean = DulkirConfig.doesScaleSwing,
    val x: Float = DulkirConfig.customX,
    val y: Float = DulkirConfig.customY,
    val z: Float = DulkirConfig.customZ,
    val yaw: Float = DulkirConfig.customYaw,
    val pitch: Float = DulkirConfig.customPitch,
    val roll: Float = DulkirConfig.customRoll,
    val speed: Float = DulkirConfig.customSpeed,
    val ignoreHaste: Boolean = DulkirConfig.ignoreHaste,
    val drinkingFix: Int = DulkirConfig.drinkingSelector,
    val whitelistEnabled: Boolean = DulkirConfig.whitelistEnabled,
    val customItemIds: String = DulkirConfig.customItemIds,
    val overrideSize: Float = DulkirConfig.customOverrideSize,
    val overrideScaleSwing: Boolean = DulkirConfig.doesOverrideScaleSwing,
    val overrideX: Float = DulkirConfig.customOverrideX,
    val overrideY: Float = DulkirConfig.customOverrideY,
    val overrideZ: Float = DulkirConfig.customOverrideZ,
    val overrideYaw: Float = DulkirConfig.customOverrideYaw,
    val overridePitch: Float = DulkirConfig.customOverridePitch,
    val overrideRoll: Float = DulkirConfig.customOverrideRoll,
    val overrideSpeed: Float = DulkirConfig.customOverrideSpeed,
    val ignoreOverrideHaste: Boolean = DulkirConfig.ignoreOverrideHaste,
)
