package ru.egorbauer.cfttpapp

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.egorbauer.cfttpapp.data.*
import ru.egorbauer.cfttpapp.domain.IUserRepo
import ru.egorbauer.cfttpapp.ui.MainActivityViewModel
import ru.egorbauer.cfttpapp.ui.SecondActivityViewModel

val networkModule = module {
    single<INetwork> { Network() }
    single { provideSource(get()) }
    single { CftDatabase.getDatabase(get()) }
}

val remoteModule = module {
    single { Source(get()) }
}

val repositoryModule = module {
    single<IUserRepo> { UserRepo(get(), get()) }
}

val viewModelModule = module {
    viewModel { MainActivityViewModel(get()) }
    viewModel { SecondActivityViewModel(get()) }
}

fun getModules(): List<Module> {
    return listOf(networkModule, viewModelModule, repositoryModule, remoteModule)
}