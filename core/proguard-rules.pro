-keep class com.dicoding.core.** { *; }
-keepclassmembers class com.dicoding.core.** { *; }

# 1️⃣ Keep all Kotlin metadata annotations (important for reflection)
-keepattributes *Annotation*

# 2️⃣ Keep Kotlin metadata class (required for reflection)
-keep class kotlin.Metadata { *; }

# 3️⃣ Keep all Kotlin reflection-related classes
-keep class kotlin.reflect.** { *; }
-keep class kotlin.reflect.full.** { *; }
-keep class kotlin.reflect.jvm.** { *; }

# 4️⃣ Prevent ProGuard from stripping reflection-based calls
-keepclassmembers class * {
    kotlin.Metadata *;
    kotlin.reflect.KClass * ;
    kotlin.reflect.KFunction *;
    kotlin.reflect.KProperty * ;
    kotlin.reflect.KMutableProperty * ;
}

# 5️⃣ Keep all properties and their getters/setters in your package
-keepclassmembers class com.dicoding.core.** {
    *;
}


# Prevent removal of classes used by Kotlin Reflection and Java 8+ features
-keep class kotlin.jvm.internal.** { *; }

# Keep Java 8+ StringConcatFactory (used for optimized string concatenation)
#-keep class java.lang.invoke.StringConcatFactory { *; }
-dontwarn java.lang.invoke.StringConcatFactory

# Keep all classes from `java.lang.invoke` (may be required for Kotlin and Java 8+ compatibility)
-keep class java.lang.invoke.** { *; }

# Prevent stripping of Java 8 internal method handles (important for reflection and lambdas)
-keep class java.lang.invoke.MethodHandles { *; }