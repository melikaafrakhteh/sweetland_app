package com.afrakhteh.sweetlandapp.di.component

import com.afrakhteh.sweetlandapp.di.module.ViewModelModule
import com.afrakhteh.sweetlandapp.di.module.ViewModelProvidersModule
import com.afrakhteh.sweetlandapp.di.scope.ViewModelScope
import com.afrakhteh.sweetlandapp.view.main.fragment.home.HomeFragment
import dagger.Component

@ViewModelScope
@Component(
        modules = [ViewModelProvidersModule::class,
                   ViewModelModule::class],
        dependencies = [
            MainActivityComponent::class,
            UseCaseComponent::class]
)
interface ViewModelComponent {

   fun inject(fragment: HomeFragment)
}