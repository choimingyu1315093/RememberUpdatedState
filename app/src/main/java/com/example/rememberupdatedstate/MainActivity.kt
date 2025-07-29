package com.example.rememberupdatedstate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rememberupdatedstate.ui.theme.RememberUpdatedStateTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var userName by remember { mutableStateOf("최대리") }

            Column (
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
//                CountingCard()

//                AlarmTest(userName)
//                Button(
//                    onClick = {
//                        userName = "김대리"
//                    }
//                ) {
//                    Text("이름바꾸기")
//                }

                InefficientFilterExample()
            }
        }
    }
}

@Composable
fun CountingCard(){
    var count by remember { mutableStateOf(0) }

    Text(
        text = "$count"
    )
    Button(
        onClick = {
            count++
        }
    ) {
        Text(
            text = "+"
        )
    }
}

@Composable
fun AlarmTest(name: String){
    val context = LocalContext.current
    val newName by rememberUpdatedState(name)

    LaunchedEffect(Unit) {
        delay(5000)
        Toast.makeText(context, "${newName}님 일어나세요", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun InefficientFilterExample() {
    val names = remember {
        List(10000) { "Item #$it" }
    }

    var query by remember { mutableStateOf("") }

//    val filtered = names.filter {
//        println("🔁 filtering: $it") // filter가 얼마나 자주 실행되는지 로그 찍음
//        it.contains(query, ignoreCase = true)
//    }

    val filtered by remember {
        derivedStateOf {
            names.filter {
                println("🔁 filtering: $it") // filter가 얼마나 자주 실행되는지 로그 찍음
                it.contains(query, ignoreCase = true)
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(value = query, onValueChange = { query = it })
        Text("결과 수: ${filtered.size}")
    }
}