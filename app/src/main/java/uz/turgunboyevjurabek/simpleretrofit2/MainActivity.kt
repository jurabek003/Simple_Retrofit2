@file:Suppress("CAST_NEVER_SUCCEEDS")

package uz.turgunboyevjurabek.simpleretrofit2

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.LocaleDisplayNames.UiListItem
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Alignment
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
import uz.turgunboyevjurabek.simpleretrofit2.madels.Data
import uz.turgunboyevjurabek.simpleretrofit2.network.ApiClient
import uz.turgunboyevjurabek.simpleretrofit2.ui.theme.SimpleRetrofit2Theme
import java.io.IOException

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

@SuppressLint("MutableCollectionMutableState")
@Composable
fun Greeting() {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var data by remember { mutableStateOf(CatsFacts()) }
    LaunchedEffect(key1 = true){
        try {
            scope.launch(Dispatchers.IO ) {
                data = ApiClient.api.getAllItem()
            }
        }catch (e:Exception){
            Toast.makeText(context, "vay ${e.message}", Toast.LENGTH_SHORT).show()
        }

    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val list=ArrayList<CatsFacts>()
        list.addAll(listOf(data))
        LazyColumn{
            data.data?.let {it->
                items(it.size){
                    Text(text = data.data?.get(it)?.fact.toString())
                    Divider(modifier = Modifier.padding(vertical = 10.dp))
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

@Composable
fun List(catsFacts: CatsFacts) {

    Toast.makeText(LocalContext.current, "qani ${catsFacts.data}", Toast.LENGTH_SHORT).show()
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)){
            catsFacts.data?.let { it ->
                items(it.size){
                    UiListItem(catsFacts.data[it],it)
            }
        }
    }



}

@Composable
fun UiListItem(catsFacts:Data,position:Int) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(horizontal = 15.dp)){
    }

}