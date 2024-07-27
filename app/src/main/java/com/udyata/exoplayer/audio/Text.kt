package com.udyata.exoplayer.audio

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun GraphicsLayerTestScreen(modifier: Modifier = Modifier) {
    var scaleX by remember { mutableFloatStateOf(1f) }
    var scaleY by remember { mutableFloatStateOf(1f) }
    var alpha by remember { mutableFloatStateOf(1f) }
    var rotationX by remember { mutableFloatStateOf(0f) }
    var rotationY by remember { mutableFloatStateOf(0f) }
    var rotationZ by remember { mutableFloatStateOf(0f) }
    var translationX by remember { mutableFloatStateOf(0f) }
    var translationY by remember { mutableFloatStateOf(0f) }
    var shadowElevation by remember { mutableFloatStateOf(0f) }

    val animatedScaleX by animateFloatAsState(targetValue = scaleX, label = "")
    val animatedScaleY by animateFloatAsState(targetValue = scaleY, label = "")
    val animatedAlpha by animateFloatAsState(targetValue = alpha, label = "")
    val animatedRotationX by animateFloatAsState(targetValue = rotationX, label = "")
    val animatedRotationY by animateFloatAsState(targetValue = rotationY, label = "")
    val animatedRotationZ by animateFloatAsState(targetValue = rotationZ, label = "")
    val animatedTranslationX by animateFloatAsState(targetValue = translationX, label = "")
    val animatedTranslationY by animateFloatAsState(targetValue = translationY, label = "")
    val animatedShadowElevation by animateFloatAsState(targetValue = shadowElevation, label = "")

    val resetValues = {
        scaleX = 1f
        scaleY = 1f
        alpha = 1f
        rotationX = 0f
        rotationY = 0f
        rotationZ = 0f
        translationX = 0f
        translationY = 0f
        shadowElevation = 0f
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Scale X: ${scaleX.format(2)}")
        Slider(value = scaleX, onValueChange = { scaleX = it }, valueRange = 0f..3f)

        Text("Scale Y: ${scaleY.format(2)}")
        Slider(value = scaleY, onValueChange = { scaleY = it }, valueRange = 0f..3f)

        Text("Alpha: ${alpha.format(2)}")
        Slider(value = alpha, onValueChange = { alpha = it }, valueRange = 0f..1f)

        Text("Rotation X: ${rotationX.format(2)}")
        Slider(value = rotationX, onValueChange = { rotationX = it }, valueRange = -360f..360f)

        Text("Rotation Y: ${rotationY.format(2)}")
        Slider(value = rotationY, onValueChange = { rotationY = it }, valueRange = -360f..360f)

        Text("Rotation Z: ${rotationZ.format(2)}")
        Slider(value = rotationZ, onValueChange = { rotationZ = it }, valueRange = -360f..360f)

        Text("Translation X: ${translationX.format(2)}")
        Slider(value = translationX, onValueChange = { translationX = it }, valueRange = -200f..200f)

        Text("Translation Y: ${translationY.format(2)}")
        Slider(value = translationY, onValueChange = { translationY = it }, valueRange = -200f..200f)

        Text("Shadow Elevation: ${shadowElevation.format(2)}")
        Slider(value = shadowElevation, onValueChange = { shadowElevation = it }, valueRange = 0f..20f)

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                shape = RectangleShape,
                modifier = Modifier
                    .width(100.dp)
                    .graphicsLayer(
                        scaleX = animatedScaleX,
                        scaleY = animatedScaleY,
                        alpha = animatedAlpha,
                        rotationX = animatedRotationX,
                        rotationY = animatedRotationY,
                        rotationZ = animatedRotationZ,
                        translationX = animatedTranslationX,
                        translationY = animatedTranslationY,
                        shadowElevation = animatedShadowElevation
                    ),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Text")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                shape = RectangleShape,
                modifier = Modifier
                    .width(100.dp),
                onClick = resetValues
            ) {
                Text(text = "Reset")
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun GraphicsLayerTestScreenPreview() {
    GraphicsLayerTestScreen()
}

private fun Float.format(digits: Int) = "%.${digits}f".format(this)



