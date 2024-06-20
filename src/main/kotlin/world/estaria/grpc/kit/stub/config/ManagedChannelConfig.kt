package world.estaria.grpc.kit.stub.config

import kotlinx.serialization.Serializable

/**
 * @author Niklas Nieberler
 */

@Serializable
class ManagedChannelConfig(
    val host: String,
    val port: Int
) {

    object Default {
        fun get() = ManagedChannelConfig("127.0.0.1", 3000)
    }

}