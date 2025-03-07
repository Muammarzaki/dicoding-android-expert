# Keep Hilt-related classes
-keep class dagger.hilt.** { *; }
-keep class androidx.hilt.** { *; }
-keep class com.dicoding.core.di.** { *; }

# Keep generated Hilt classes
-keep class com.dicoding.core.di.FavoriteModuleDependencies { *; }

# Prevent Proguard from obfuscating classes annotated with Hilt
-keep @dagger.hilt.android.internal.managers.** class *
-keep @dagger.hilt.internal.** class *
-keep @dagger.Module class *

# Keep Kotlin metadata
-keep class kotlin.Metadata { *; }

-keep class com.dicoding.core.data.AICRepository { *; }
-keep class com.dicoding.core.di.CoreModule { *; }
-keep class com.dicoding.core.di.CoreBinding { *; }

-keep class java.lang.invoke.** { *; }
-dontwarn java.lang.invoke.StringConcatFactory

-keep class com.dicoding.core.domain.** {*;}