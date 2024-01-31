package uz.turgunboyevjurabek.simpleretrofit2

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.LocaleDisplayNames.UiListItem
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.turgunboyevjurabek.simpleretrofit2.madels.CatsFacts
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
                    List()
                }
            }
        }
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
 fun Greeting():ArrayList<CatsFacts> {
    val scope= rememberCoroutineScope()
    val context= LocalContext.current
    val data by remember { mutableStateOf(ArrayList<CatsFacts>()) }

    ApiClient.api.getAllItem().enqueue(object :Callback<CatsFacts>{
        override fun onResponse(call: Call<CatsFacts>, response: Response<CatsFacts>) {
            if (response.isSuccessful){
                var list:CatsFacts = response.body()!!
                data.addAll(listOf(list))
                Toast.makeText(context, "keldi", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<CatsFacts>, t: Throwable) {
            Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
        }
    })

   return data
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    SimpleRetrofit2Theme {
        Greeting()
    }
}

@Composable
fun List() {
    val list= Greeting()
    Toast.makeText(LocalContext.current, "$list", Toast.LENGTH_SHORT).show()

    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(list.size){
            UiListItem(list[it],it)
        }
    }

}

@Composable
fun UiListItem(catsFacts:CatsFacts,position:Int) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 15.dp)){
        Text(text = catsFacts.data[position].fact, fontSize = 18.sp, color = Color.Red)
    }

}