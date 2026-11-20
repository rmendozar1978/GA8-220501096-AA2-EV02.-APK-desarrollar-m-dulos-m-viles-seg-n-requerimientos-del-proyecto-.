# ProGuard / R8 rules for ShieldTube

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# DataStore
-keepclassmembers class * extends androidx.datastore.preferences.core.Preferences { *; }

# Hilt
-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }
