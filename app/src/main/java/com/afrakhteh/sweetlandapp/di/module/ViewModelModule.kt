package com.afrakhteh.sweetlandapp.di.module

import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.di.ViewModelKey
import com.afrakhteh.sweetlandapp.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FaveViewModel::class)
    fun bindFaveViewModel(viewModel: FaveViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleDetailViewModel::class)
    fun bindArticleDetailViewModel(viewModel: ArticleDetailViewModel): ViewModel
}