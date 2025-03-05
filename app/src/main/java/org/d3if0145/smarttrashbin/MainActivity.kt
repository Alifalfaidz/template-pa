package org.d3if0145.smarttrashbin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if0145.smarttrashbin.ui.theme.SmartTrashBinTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartTrashBinTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val backgroundColor = colorResource(id = R.color.sage_green)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tanggal
            DateCard()

            Spacer(modifier = Modifier.height(12.dp))

            // User Card
            UserCard()

            Spacer(modifier = Modifier.height(24.dp))

            // Logo Placeholder (akan diganti dengan logo asli nanti)
            LogoPlaceholder()

            Spacer(modifier = Modifier.height(32.dp))

            // Status Tempat Sampah
            TrashStatusGrid()
        }
    }
}

@Composable
fun DateCard() {
    val currentDate = remember {
        SimpleDateFormat("d MMMM yyyy", Locale("id", "ID")).format(Date())
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currentDate,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun UserCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Placeholder for user icon (to be replaced)
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.LightGray, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {}

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            Text(
                text = stringResource(id = R.string.user_label),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun LogoPlaceholder() {
    Card(
        modifier = Modifier.size(200.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Placeholder for logo
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.LightGray, RoundedCornerShape(60.dp)),
                contentAlignment = Alignment.Center
            ) {}

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.logo_smart),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.white)
            )

            Text(
                text = stringResource(id = R.string.logo_trash),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.white)
            )

            Text(
                text = stringResource(id = R.string.logo_bin),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.white)
            )
        }
    }
}

@Composable
fun TrashStatusGrid() {
    var organikPercentage by remember { mutableStateOf("0%") }
    var anorganikPercentage by remember { mutableStateOf("0%") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Organik Status
        TrashStatusCard(
            title = stringResource(id = R.string.organik_label),
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        // Percentage Status
        PercentageCard(
            percentage = organikPercentage,
            modifier = Modifier.weight(1f)
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Anorganik Status
        TrashStatusCard(
            title = stringResource(id = R.string.anorganik_label),
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        // Percentage Status
        PercentageCard(
            percentage = anorganikPercentage,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun TrashStatusCard(title: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { /* No action needed */ },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = modifier.height(80.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PercentageCard(percentage: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { /* No action needed */ },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = modifier.height(80.dp)
    ) {
        Text(
            text = percentage,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SmartTrashBinTheme {
        HomeScreen()
    }
}