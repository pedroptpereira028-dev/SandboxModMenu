package com.example.sandboxmodmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SandboxTheme { SandboxMenu() } }
    }
}

@Composable
fun SandboxMenu() {
    var infiniteCoins by remember { mutableStateOf(false) }
    var infiniteGems by remember { mutableStateOf(false) }
    var infiniteEnergy by remember { mutableStateOf(false) }
    var maxAttributes by remember { mutableStateOf(false) }
    var trainingMultiplier by remember { mutableStateOf(false) }

    var coins by remember { mutableLongStateOf(100_000L) }
    var gems by remember { mutableIntStateOf(5_000) }
    var energy by remember { mutableIntStateOf(100) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("SANDBOX MOD MENU", fontWeight = FontWeight.Bold) }) }
    ) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp)) {
                Column(Modifier.padding(18.dp)) {
                    Text("MODO SANDBOX", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("Recursos fictícios para testes. Não interage com outros jogos.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            ResourceCard("Moedas", if (infiniteCoins) "∞" else coins.toString(), Icons.Default.MonetizationOn)
            ResourceCard("Gemas", if (infiniteGems) "∞" else gems.toString(), Icons.Default.Diamond)
            ResourceCard("Energia", if (infiniteEnergy) "∞" else energy.toString(), Icons.Default.Bolt)

            SectionTitle("RECURSOS")
            ToggleOption("Moedas infinitas", "Simulação local", infiniteCoins) {
                infiniteCoins = it
                coins = if (it) Long.MAX_VALUE else 100_000L
            }
            ToggleOption("Gemas infinitas", "Simulação local", infiniteGems) {
                infiniteGems = it
                gems = if (it) Int.MAX_VALUE else 5_000
            }
            ToggleOption("Energia infinita", "Mantém energia no máximo", infiniteEnergy) {
                infiniteEnergy = it
                energy = if (it) 100 else energy
            }

            SectionTitle("JOGADOR")
            ToggleOption("Atributos máximos", "Exibe 99 nos atributos simulados",
                maxAttributes) { maxAttributes = it }

            AttributeCard(maxAttributes)

            SectionTitle("TREINO")
            ToggleOption("Multiplicador de treino", "Simulação de ganho aumentado",
                trainingMultiplier) { trainingMultiplier = it }

            Text(
                if (trainingMultiplier) "Multiplicador: 10x" else "Multiplicador: 1x",
                fontSize = 20.sp, fontWeight = FontWeight.Bold
            )

            SectionTitle("CONTROLES")
            Button(
                onClick = {
                    infiniteCoins = false
                    infiniteGems = false
                    infiniteEnergy = false
                    maxAttributes = false
                    trainingMultiplier = false
                    coins = 100_000L
                    gems = 5_000
                    energy = 100
                },
                Modifier.fillMaxWidth()
            ) { Text("RESTAURAR PADRÕES") }

            OutlinedButton(
                onClick = {
                    coins += 10_000
                    gems += 500
                    energy = 100
                },
                Modifier.fillMaxWidth()
            ) { Text("ADICIONAR RECURSOS DE TESTE") }
        }
    }
}

@Composable
fun ResourceCard(title: String, value: String, icon: ImageVector) {
    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, Modifier.size(30.dp))
            Spacer(Modifier.width(14.dp))
            Column {
                Text(title, fontWeight = FontWeight.SemiBold)
                Text(value, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ToggleOption(title: String, description: String, checked: Boolean,
                 onCheckedChange: (Boolean) -> Unit) {
    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp)) {
        Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold)
                Text(description, fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Switch(checked, onCheckedChange)
        }
    }
}

@Composable
fun AttributeCard(max: Boolean) {
    val value = if (max) "99" else "75"
    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.padding(16.dp)) {
            listOf("Ritmo", "Finalização", "Passe", "Drible", "Defesa", "Físico")
                .forEach { name ->
                    Row(Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                        Text(name, Modifier.weight(1f))
                        Text(value, fontWeight = FontWeight.Bold)
                    }
                }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(title, fontSize = 13.sp, fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 6.dp))
}

@Composable
fun SandboxTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF00E676),
            secondary = Color(0xFF00BFA5),
            background = Color(0xFF090B0F),
            surface = Color(0xFF11151B)
        ),
        content = content
    )
}
