package uz.turgunboyevjurabek.simpleretrofit2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.turgunboyevjurabek.simpleretrofit2.network.ApiClient
import uz.turgunboyevjurabek.simpleretrofit2.ui.theme.SimpleRetrofit2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleRetrofit2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val scope= rememberCoroutineScope()
    val context= LocalContext.current
    LaunchedEffect(key1 = true){
        scope.launch(Dispatchers.IO){
            try {
                val  data=ApiClient.api.getAllItem()

                withContext(Dispatchers.Main){
                    Toast.makeText(context, "joningdan  $data", Toast.LENGTH_SHORT).show()
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Log.d("Iye", e.message.toString())
                    Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    SimpleRetrofit2Theme {
        Greeting()
    }
}