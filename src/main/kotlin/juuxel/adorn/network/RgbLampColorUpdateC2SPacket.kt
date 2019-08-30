package juuxel.adorn.network

import juuxel.adorn.util.colorFromComponents
import net.minecraft.util.PacketByteBuf
import net.minecraft.util.math.BlockPos

data class RgbLampColorUpdateC2SPacket(
    val syncId: Int, val pos: BlockPos,
    val red: Int, val green: Int, val blue: Int
) : AdornPacket() {
    override fun write(buf: PacketByteBuf): PacketByteBuf {
        buf.writeInt(syncId)
        buf.writeBlockPos(pos)
        buf.writeInt(colorFromComponents(red, green, blue))
        return buf
    }

    companion object {
        fun read(buf: PacketByteBuf): RgbLampColorUpdateC2SPacket {
            val syncId = buf.readInt()
            val pos = buf.readBlockPos()
            val combinedColor = buf.readInt()
            val red = (combinedColor shr 16) and 0xFF
            val green = (combinedColor shr 8) and 0xFF
            val blue = combinedColor and 0xFF

            return RgbLampColorUpdateC2SPacket(syncId, pos, red, green, blue)
        }
    }
}
