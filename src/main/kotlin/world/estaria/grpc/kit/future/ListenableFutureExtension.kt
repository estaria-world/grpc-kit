package world.estaria.grpc.kit.future

import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.CompletableFuture

/**
 * @author theSimpleCloud
 * https://github.com/theSimpleCloud/simplecloud-controller/tree/main/controller-shared/src/main/kotlin/app/simplecloud/controller/shared/future
 */

fun <T> ListenableFuture<T>.toCompletable(): CompletableFuture<T> {
    return ListenableFutureAdapter.toCompletable(this)
}