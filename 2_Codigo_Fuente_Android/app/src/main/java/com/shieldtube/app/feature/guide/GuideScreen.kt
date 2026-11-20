package com.shieldtube.app.feature.guide

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shieldtube.app.core.ui.theme.AccentOrange
import com.shieldtube.app.core.ui.theme.RedPrimary

/**
 * Pantalla de Guía con Expectativas Honestas.
 */
@Composable
fun GuideScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Guía y Expectativas Honestas",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Card 1: Modo DNS y la App Oficial
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "1. ¿Qué bloquea el modo VPN-DNS?",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = AccentOrange
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "• El bloqueador DNS filtra rastreadores y publicidad externa en tu dispositivo.\n" +
                            "• NO PUEDE eliminar los anuncios de video dentro de la aplicación oficial de YouTube: YouTube transmite sus videos y anuncios desde la misma infraestructura y dominios (googlevideo.com). Si un DNS bloqueara ese dominio, los videos dejarían de reproducirse.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card 2: Modo YouTube en Navegador (WebView)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "2. Modo YouTube en Navegador integrado",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = RedPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "• Este modo inyecta reglas DOM para ocultar banners, pulsar 'Omitir' automáticamente y acelerar/silenciar anuncios no omitibles.\n" +
                            "• Fragilidad: YouTube actualiza frecuentemente sus selectores web. Por eso los selectores son dinámicos y actualizables vía JSON.\n" +
                            "• Inicio de sesión: Google suele bloquear el inicio de sesión en WebViews embebidas (pantalla de navegador no seguro). ShieldTube está diseñado para funcionar fluidamente como invitado sin requerir login.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card 3: Configuración crítica de Android y Chrome
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "3. Ajuste imprescindible en tu teléfono",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Para que el bloqueador funcione correctamente:\n" +
                            "1. Desactiva el 'DNS Privado' de Android (Ajustes > Redes e Internet > DNS Privado > Desactivado). Si está activo en DoT/DoH, el sistema omitirá la VPN local.\n" +
                            "2. En Chrome, desactiva 'Usar DNS seguro' (Ajustes > Privacidad y seguridad > Usar DNS seguro), o configurará su propio túnel cifrado evitando el filtro.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
