package com.shieldtube.app.feature.vpn

import android.content.Intent
import android.net.VpnService

/**
 * Servicio VPN para filtrado DNS local.
 * Implementación completa de túnel y parser en la Fase 3.
 * Configurado con foregroundServiceType="systemExempted" para Android 14+ (API 34/35).
 */
class ShieldTubeVpnService : VpnService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
