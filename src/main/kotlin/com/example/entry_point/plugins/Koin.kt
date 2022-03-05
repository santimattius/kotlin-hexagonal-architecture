package com.example.entry_point.plugins


import com.example.entry_point.container.dependencies
import io.ktor.events.EventDefinition
import io.ktor.server.application.*
import io.ktor.util.*
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin

/**
 * koin workaround https://github.com/InsertKoinIO/koin/issues/1257#issuecomment-1000856335
 */
object KoinPlugin : ApplicationPlugin<Application, KoinApplication, Unit> {

    override val key: AttributeKey<Unit>
        get() = AttributeKey("Koin")

    override fun install(
        pipeline: Application,
        configure: KoinApplication.() -> Unit
    ) {
        val monitor = pipeline.environment.monitor
        val koinApplication = startKoin(appDeclaration = configure)
        monitor.raise(EventDefinition(), koinApplication)

        monitor.subscribe(ApplicationStopping) {
            monitor.raise(EventDefinition(), koinApplication)
            stopKoin()
            monitor.raise(EventDefinition(), koinApplication)
        }
    }
}

fun Application.configureKoin() {
    install(KoinPlugin) {
        modules(dependencies)
    }
}