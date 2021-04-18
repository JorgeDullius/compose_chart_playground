package com.jorge.androidplaygroung.presentation

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jorge.androidplaygroung.R
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsStory()
        }
    }

}

@Composable
fun NewsStory() {
    MaterialTheme {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                Modifier.padding(16.dp)
            ) {
                Text("800.0", modifier = Modifier.paddingFromBaseline(41.dp), color = Color.White)
                Text("700.0", modifier = Modifier.paddingFromBaseline(41.dp), color = Color.White)
                Text("600.0", modifier = Modifier.paddingFromBaseline(41.dp), color = Color.White)
                Text("500.0", modifier = Modifier.paddingFromBaseline(41.dp), color = Color.White)
                Text("400.0", modifier = Modifier.paddingFromBaseline(41.dp), color = Color.White)
                Text("300.0", modifier = Modifier.paddingFromBaseline(41.dp), color = Color.White)
                Text("200.0", modifier = Modifier.paddingFromBaseline(41.dp), color = Color.White)
                Text("100.0", modifier = Modifier.paddingFromBaseline(41.dp), color = Color.White)
                Text("0.0", modifier = Modifier.paddingFromBaseline(41.dp), color = Color.White)
            }
            DrawCanvas()
        }
    }
}

@Composable
fun DrawCanvas() {
    var width: Dp
    var height: Dp
    with(LocalDensity.current) {
        width = Resources.getSystem().displayMetrics.widthPixels.toDp()
        height = Resources.getSystem().displayMetrics.heightPixels.toDp()
    }
    return Canvas(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .size(
                width = width,
                height = height
            )
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val chartWidth = (canvasWidth - 550f) / 4
        var chartXOffset = 60f

        drawBarChart(
            drawScope = this,
            height = canvasHeight - 400f,
            size = Size(chartWidth, 400f),
            canvasHeight = canvasHeight
        )
        chartXOffset += 60f + chartWidth
        drawBarChart(
            drawScope = this,
            height = canvasHeight - 400f,
            size = Size(chartWidth, 400f),
            chartXOffset,
            canvasHeight = canvasHeight,
            brush = Brush.linearGradient(
                listOf(
                    Color(0xFF37BB8C),
                    Color(0xFF317D76),
                    Color(0xFF2A3B5F)
                ),
                start = Offset(chartXOffset, canvasHeight - 390f),
                end = Offset(chartXOffset, canvasHeight - 11),
                tileMode = TileMode.Clamp
            )
        )
        chartXOffset += 150f + chartWidth
        drawBarChart(
            drawScope = this,
            height = canvasHeight - 600f,
            size = Size(chartWidth, 600f),
            chartXOffset,
            canvasHeight = canvasHeight
        )
        chartXOffset += 60f + chartWidth
        drawBarChart(
            drawScope = this,
            height = canvasHeight - 600f,
            size = Size(chartWidth, 600f),
            chartXOffset,
            canvasHeight = canvasHeight
        )
        chartXOffset += 60f + chartWidth
        drawBarChart(
            drawScope = this,
            height = canvasHeight - 600f,
            size = Size(chartWidth, 600f),
            chartXOffset,
            canvasHeight = canvasHeight
        )
    }
}

fun drawBarChart(drawScope: DrawScope,
                 height: Float,
                 size: Size,
                 chartXOffset: Float = 40f,
                 canvasHeight: Float,
                 brush: Brush = Brush.verticalGradient(
                     colors = listOf(
                         Color(0xFF4BEEFE),
                         Color(0xFF357592),
                         Color(0xFF2A3B5F)
                     ),
                     startY = height,
                     endY = canvasHeight,
                     tileMode = TileMode.Clamp
                 )
){
    drawScope.let{
        it.drawPath(
            Path().apply {
                val rect = RoundRect(
                    Rect(
                        offset = Offset(chartXOffset, height),
                        size = size
                    ),
                    CornerRadius(35f,35f),
                    CornerRadius(35f,35f)
                )

                this.addRoundRect(rect)
            },
            brush = brush
        )
    }
}

@Preview
@Composable
fun Preview() {
    NewsStory()
}