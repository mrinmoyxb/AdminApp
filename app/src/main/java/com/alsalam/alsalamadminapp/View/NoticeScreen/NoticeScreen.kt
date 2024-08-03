package com.alsalam.alsalamadminapp.View.NoticeScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsalam.alsalamadminapp.R
import com.alsalam.alsalamadminapp.View.Components.CustomTopBar
import com.alsalam.alsalamadminapp.View.Components.SaveUploadButton
import com.alsalam.alsalamadminapp.ViewModel.PdfUploadAndRetrieveViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun NoticeScreen(){

    val uploadNoticesViewModel: PdfUploadAndRetrieveViewModel = viewModel()
    val context = LocalContext.current

    val fileName by uploadNoticesViewModel.fileName.collectAsState(initial = "")
    val file by uploadNoticesViewModel.setFileName.collectAsState(initial = "")

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uploadNoticesViewModel.selectPdf(uri)
    }

    Scaffold(
        topBar = { CustomTopBar(text = "Notice") }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.secondary_gray))
                .padding(all = 10.dp)
        ){

            item {
                // pdf picker
                Spacer(modifier = Modifier.height(85.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clickable {
                            launcher.launch("application/pdf")
                            Toast
                                .makeText(context, "Notice selected", Toast.LENGTH_SHORT)
                                .show()
                        },
                    colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
                )
                {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "",
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            tint = Color.White
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            "Add Notice",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White
                        )
                    }

                }

                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = file,
                    onValueChange = { uploadNoticesViewModel.setFileName.value = it },
                    label = { Text("Set Notice Name", fontSize = 15.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorResource(id = R.color.secondary_gray1)
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))

                // upload pdf
                SaveUploadButton(title = "Upload", onClick = {
                    uploadNoticesViewModel.uploadNoticePdf()
                    Toast.makeText(context, "Notice Uploaded", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }


}