package world.estaria.grpc.kit.stub

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

/**
 * @author Niklas Nieberler
 */

class ManagedChannelCreator(
    private val configuration: ManagedChannelConfiguration
) {

    /**
     * Creates a new [ManagedChannel] with configuration arguments of [ManagedChannelConfiguration]
     * @return new instance if [ManagedChannel]
     */
    fun build(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(this.configuration.host, this.configuration.port)
            .usePlaintext()
            .build()
    }

}