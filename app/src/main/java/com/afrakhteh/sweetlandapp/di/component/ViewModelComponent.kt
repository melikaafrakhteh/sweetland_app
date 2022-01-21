package com.afrakhteh.sweetlandapp.di.component

import com.afrakhteh.sweetlandapp.di.module.ViewModelModule
import com.afrakhteh.sweetlandapp.di.module.ViewModelProvidersModule
import com.afrakhteh.sweetlandapp.di.scope.ViewModelScope
import com.afrakhteh.sweetlandapp.view.main.fragment.detail.DetailFragment
import com.afrakhteh.sweetlandapp.view.main.fragment.favorite.FavFragment
import com.afrakhteh.sweetlandapp.view.main.fragment.home.HomeFragment
import com.afrakhteh.sweetlandapp.view.main.fragment.search.SearchFragment
import com.afrakhteh.sweetlandapp.viewmodel.FaveViewModel
import dagger.Component

@ViewModelScope
@Component(
        modules = [ViewModelProvidersModule::class,
                   ViewModelModule::class],
        dependencies = [
            RepositoryComponent::class,
            UseCaseComponent::class]
)
interface ViewModelComponent {
   fun inject(fragment: HomeFragment)
   fun inject(fragment: SearchFragment)
   fun inject(fragment: FavFragment)
   fun inject(fragment: DetailFragment)
}