package world.estaria.grpc.kit.stub.config

import world.avionik.configkit.ConfigLoader
import world.avionik.configkit.format.YamlFileFormatter
import java.io.File

/**
 * @author Niklas Nieberler
 */

class ManagedChannelConfigLoader(
    path: String
) : ConfigLoader<ManagedChannelConfig>(
    File(path),
    YamlFileFormatter(
        ManagedChannelConfig.serializer()
    ),
    { ManagedChannelConfig.Default.get() }
)