================================================================================
SERVICIO NACIONAL DE APRENDIZAJE - SENA
PROGRAMA: TECNOLOGÍA EN ANÁLISIS Y DESARROLLO DE SOFTWARE (ADSO)
EVIDENCIA: GA8-220501096-AA2-EV02 - PRODUCTO: APK
APLICATIVO MÓVIL: SHIELDTUBE (v1.0.0)
================================================================================

CONTENIDO DE ESTA CARPETA:
1. ShieldTube_v1.0.0_debug.apk
   - Archivo ejecutable empaquetado y firmado para instalación en dispositivos Android
     (físicos o emuladores AVD).
   - Package Name: com.shieldtube.app
   - Min SDK: 26 (Android 8.0 Oreo) | Target SDK: 35 (Android 15)
   - Arquitectura: Clean Architecture + MVVM + Jetpack Compose + VpnService

--------------------------------------------------------------------------------
MÉTODOS DE INSTALACIÓN Y EJECUCIÓN:
--------------------------------------------------------------------------------

MÉTODO 1: INSTALACIÓN MEDIANTE ADB (Android Debug Bridge)
1. Conecta tu teléfono Android al computador mediante cable USB y habilita la
   "Depuración por USB" en las Opciones de Desarrollador.
2. Abre una terminal de comandos en esta carpeta y ejecuta:
   adb install -r ShieldTube_v1.0.0_debug.apk
3. El aplicativo se instalará instantáneamente en el dispositivo.

MÉTODO 2: INSTALACIÓN DIRECTA EN EL DISPOSITIVO
1. Copia el archivo "ShieldTube_v1.0.0_debug.apk" a la memoria interna de tu teléfono
   (mediante cable USB, Google Drive, WhatsApp o Telegram).
2. En el teléfono, abre el Explorador de Archivos y pulsa sobre el archivo .apk.
3. Si el sistema lo solicita, autoriza la opción "Instalar aplicaciones de fuentes desconocidas".
4. Presiona "Instalar" y luego "Abrir".

MÉTODO 3: EJECUCIÓN EN EMULADOR DE ANDROID STUDIO
1. Abre Android Studio y arranca un dispositivo virtual (AVD) con API 26 a 35.
2. Arrastra el archivo "ShieldTube_v1.0.0_debug.apk" directamente sobre la ventana
   del emulador (Drag & Drop). El emulador lo instalará automáticamente.

--------------------------------------------------------------------------------
COMPILACIÓN DESDE EL CÓDIGO FUENTE (EN ANDROID STUDIO):
--------------------------------------------------------------------------------
Para compilar un nuevo APK desde el código fuente incluido en la carpeta
"3_Codigo_Fuente_Android":
1. Abre Android Studio y selecciona "Open an Existing Project".
2. Selecciona la carpeta raíz del proyecto.
3. Espera a que Gradle sincronice las dependencias.
4. En el menú superior, selecciona:
   Build > Build Bundle(s) / APK(s) > Build APK(s)
5. Al finalizar, Android Studio generará el APK en:
   app/build/outputs/apk/debug/app-debug.apk
================================================================================
