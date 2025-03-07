# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

-keep class com.dicoding.favorite.** { *; }

-keepattributes *Annotation*

-keep class * extends androidx.lifecycle.ViewModel { *; }

-keep class com.dicoding.core.** {*;}

-keep class com.dicoding.core.ExtensionsKt { *; }

-dontwarn com.dicoding.core.ExtensionsKt


# Keep Kotlin Coroutines
-keep class kotlinx.coroutines.** { *; }
-keep class kotlin.coroutines.** { *; }
-keep class kotlin.coroutines.intrinsics.** { *; }
-keep class kotlin.ResultKt { *; }
-keep class kotlin.Result { *; }
-keep class kotlin.Metadata { *; }
-keep class kotlin.jvm.internal.** { *; }
-keep class kotlin.coroutines.jvm.internal.** { *; }

# Keep Compose functions
-keep class androidx.compose.** { *; }
-keep class androidx.lifecycle.** { *; }