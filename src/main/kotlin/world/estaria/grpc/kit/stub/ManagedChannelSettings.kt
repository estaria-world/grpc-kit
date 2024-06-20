package world.estaria.grpc.kit.stub

import world.estaria.grpc.kit.stub.config.ManagedChannelConfigLoader

/**
 * @author Niklas Nieberler
 */

object ManagedChannelSettings {

    /**
     * Gets the managed channel configuration from a system environment
     * @param address the environment to create the configuration
     * @param port the environment to create the configuration
     * @return configuration from the environment
     */
    fun fromEnv(
        host: String,
        port: String
    ): ManagedChannelConfiguration {
        return ManagedChannelConfiguration(
            System.getenv(host),
            System.getenv(port).toInt()
        )
    }

    /**
     * Gets the managed channel configuration from the config
     * @param path directory path of the config
     * @return configuration from the config
     */
    fun fromConfig(path: String): ManagedChannelConfiguration {
        val nettyServerConfig = ManagedChannelConfigLoader(path).load()
        return ManagedChannelConfiguration(
            nettyServerConfig.host,
            nettyServerConfig.port,
        )
    }

}