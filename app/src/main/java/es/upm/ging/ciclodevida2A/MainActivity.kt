package es.upm.ging.ciclodevida2A

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    private val tag: String? = MainActivity::class.simpleName
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
            MainScreen(
                message = lifecycleLog.value,
                onNavigate = {
                    startActivity(Intent(this, SecondActivity::class.java))
                }
            )
        }
    }

    override fun onStart() { super.onStart(); log("onStart"); Log.i(tag, "ejecutamos onStart") }
    override fun onResume() { super.onResume(); log("onResume"); Log.i(tag, "ejecutamos onResume") }
    override fun onPause() { super.onPause(); log("onPause"); Log.i(tag, "ejecutamos onPause") }
    override fun onStop() { super.onStop(); log("onStop"); Log.i(tag, "ejecutamos onStop") }
    override fun onDestroy() { super.onDestroy(); log("onDestroy"); Log.i(tag, "ejecutamos onDestroy") }
}

@Composable
fun MainScreen(message: String, onNavigate: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp)
    ) {
        Spacer(Modifier.height(40.dp))

        Button(onClick = onNavigate, modifier = Modifier.fillMaxWidth()) {
            Text("Ir a segunda actividad")
        }

        Spacer(Modifier.height(20.dp))

        Text("Eventos:\n" + message, style = MaterialTheme.typography.bodyLarge)
    }
}
