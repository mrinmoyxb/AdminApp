import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsalam.alsalamadminapp.Firebase.FirebaseDatabaseManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random


@Preview
@Composable
fun Test(){
    val viewModel: TestViewModel = viewModel()
    val name by viewModel.name.collectAsState("")
    val pass by viewModel.amount.collectAsState("")
    val output by viewModel.output.collectAsState(emptyList())
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {
        OutlinedTextField(value = name, onValueChange = {viewModel.name.value = it}, label = {Text("Name")})
        OutlinedTextField(value = pass, onValueChange = {viewModel.amount.value = it}, label = {Text("Name")})
        Button(onClick = { viewModel.createRecord()}) {
            Text("ADD")
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
        }

        Button(onClick = { viewModel.fetchDocuments()}) {
            Text("ADD")
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
        }

        output.forEach { s ->
            if(s.check) {
                Text(s.name, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Text(s.amount.toString(), fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

class TestViewModel: ViewModel(){

    var name: MutableStateFlow<String> = MutableStateFlow<String>("")
    var amount: MutableStateFlow<String> = MutableStateFlow<String>("")
    val output: MutableStateFlow<List<FireStoreTest>> = MutableStateFlow<List<FireStoreTest>>(emptyList())

    fun createRecord() {
        val nameValue = name.value
        val amount = amount.value
        val data  = FireStoreTest(nameValue.toString(), amount.toDouble(), false)
        FirebaseDatabaseManager.firestoreRef.collection("Emp").document("developers")
            .collection("DEV_101") // Set document ID
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d("Firestore", "Document written with ID: ${documentReference.toString()}")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }
    }


    fun createRecord1() {
        val nameValue = name.value
        val amount = amount.value
        val data  = FireStoreTest(nameValue.toString(), amount.toDouble(), false)
        FirebaseDatabaseManager.firestoreRef.collection("Emp").document("developers")
            .collection("DEV_102") // Set document ID
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d("Firestore", "Document written with ID: ${documentReference.toString()}")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }
    }

    fun fetchDocuments() {
        FirebaseDatabaseManager.firestoreRef.collection("Emp")
            .document("developers")
            .collection("DEV_101")
            .get()
            .addOnSuccessListener { result ->
                val documents = result.toObjects(FireStoreTest::class.java)
                output.value = documents
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error fetching documents", e)
            }
    }

}

data class FireStoreTest(
    val name: String,
    val amount: Double,
    val check: Boolean
){
    constructor() : this("", 0.0, false)
}
