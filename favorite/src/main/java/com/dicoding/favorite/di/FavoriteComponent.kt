package com.dicoding.favorite.di

import android.content.Context
import com.dicoding.core.di.FavoriteModuleDependencies
import com.dicoding.favorite.FavoriteActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {
    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(context: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}