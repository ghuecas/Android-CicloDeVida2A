package es.upm.ging.ciclodevida2A

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class SecondActivity : ComponentActivity() {

    private val tag: String? = SecondActivity::class.simpleName
    private lateinit var lifecycleLog: MutableState<String>

    private fun log(msg: String) {
        lifecycleLog.value = lifecycleLog.value + "\n" + msg
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleLog = mutableStateOf("onCreate")
        Log.i(tag, "ejecutamos onCreate")

        setContent {
            SecondScreen(
                message = lifecycleLog.value,
                onBack = { finish() }
            )
        }
    }

    override fun onStart() {
        super.onStart(); log("onStart"); Log.i(tag, "ejecutamos onStart")
    }

    override fun onResume() {
        super.onResume(); log("onResume"); Log.i(tag, "ejecutamos onResume")
    }

    override fun onPause() {
        super.onPause(); log("onPause"); Log.i(tag, "ejecutamos onPause")
    }

    override fun onStop() {
        super.onStop(); log("onStop"); Log.i(tag, "ejecutamos onStop")
    }

    override fun onDestroy() {
        super.onDestroy(); log("onDestroy"); Log.i(tag, "ejecutamos onDestroy")
    }
}

@Composable
fun SecondScreen(message: String, onBack: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {

        Spacer(Modifier.height(40.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = onBack, modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)) {
                Text("Volver")
            }
            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text("OK")
            }
        }

        Spacer(Modifier.height(20.dp))

        Text("Eventos:\n" + message, style = MaterialTheme.typography.bodyLarge)

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Todo listo") },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cerrar")
                    }
                }
            )
        }
    }
}
