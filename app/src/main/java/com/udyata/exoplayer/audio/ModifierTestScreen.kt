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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun ComprehensiveModifierTestScreen(modifier: Modifier = Modifier) {
    var scaleX by remember { mutableFloatStateOf(1f) }
    var scaleY by remember { mutableFloatStateOf(1f) }
    var alpha by remember { mutableFloatStateOf(1f) }
    var rotationX by remember { mutableFloatStateOf(0f) }
    var rotationY by remember { mutableFloatStateOf(0f) }
    var rotationZ by remember { mutableFloatStateOf(0f) }
    var translationX by remember { mutableFloatStateOf(0f) }
    var translationY by remember { mutableFloatStateOf(0f) }
    var shadowElevation by remember { mutableFloatStateOf(0f) }
    var cameraDistance by remember { mutableFloatStateOf(8f) }
    var paddingStart by remember { mutableFloatStateOf(0f) }
    var paddingTop by remember { mutableFloatStateOf(0f) }
    var paddingEnd by remember { mutableFloatStateOf(0f) }
    var paddingBottom by remember { mutableFloatStateOf(0f) }
    var blurRadius by remember { mutableFloatStateOf(0f) }
    var width by remember { mutableFloatStateOf(100f) }
    var height by remember { mutableFloatStateOf(50f) }
    var offset by remember { mutableFloatStateOf(0f) }
    var magnifierScale by remember { mutableFloatStateOf(1f) }

    val animatedScaleX by animateFloatAsState(targetValue = scaleX)
    val animatedScaleY by animateFloatAsState(targetValue = scaleY)
    val animatedAlpha by animateFloatAsState(targetValue = alpha)
    val animatedRotationX by animateFloatAsState(targetValue = rotationX)
    val animatedRotationY by animateFloatAsState(targetValue = rotationY)
    val animatedRotationZ by animateFloatAsState(targetValue = rotationZ)
    val animatedTranslationX by animateFloatAsState(targetValue = translationX)
    val animatedTranslationY by animateFloatAsState(targetValue = translationY)
    val animatedShadowElevation by animateFloatAsState(targetValue = shadowElevation)
    val animatedCameraDistance by animateFloatAsState(targetValue = cameraDistance)
    val animatedOffset by animateFloatAsState(targetValue = offset)

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
        cameraDistance = 8f
        paddingStart = 0f
        paddingTop = 0f
        paddingEnd = 0f
        paddingBottom = 0f
        blurRadius = 0f
        width = 100f
        height = 50f
        offset = 0f
        magnifierScale = 1f
    }

    LazyColumn(modifier = modifier.padding(16.dp)
    ) {
        // Controllers for GraphicsLayer properties
        item {
            SliderWithValue("Scale X", scaleX, 0f, 3f) { scaleX = it }
            SliderWithValue("Scale Y", scaleY, 0f, 3f) { scaleY = it }
            SliderWithValue("Alpha", alpha, 0f, 1f) { alpha = it }
            SliderWithValue("Rotation X", rotationX, -360f, 360f) { rotationX = it }
            SliderWithValue("Rotation Y", rotationY, -360f, 360f) { rotationY = it }
            SliderWithValue("Rotation Z", rotationZ, -360f, 360f) { rotationZ = it }
            SliderWithValue("Translation X", translationX, -200f, 200f) { translationX = it }
            SliderWithValue("Translation Y", translationY, -200f, 200f) { translationY = it }
            SliderWithValue("Shadow Elevation", shadowElevation, 0f, 20f) { shadowElevation = it }
            SliderWithValue("Camera Distance", cameraDistance, 0f, 20f) { cameraDistance = it }

            // Controllers for Padding
            SliderWithValue("Padding Start", paddingStart, 0f, 50f) { paddingStart = it }
            SliderWithValue("Padding Top", paddingTop, 0f, 50f) { paddingTop = it }
            SliderWithValue("Padding End", paddingEnd, 0f, 50f) { paddingEnd = it }
            SliderWithValue("Padding Bottom", paddingBottom, 0f, 50f) { paddingBottom = it }

            // Controllers for Blur
            SliderWithValue("Blur Radius", blurRadius, 0f, 25f) { blurRadius = it }

            // Controllers for Size
            SliderWithValue("Width", width, 50f, 300f) { width = it }
            SliderWithValue("Height", height, 50f, 300f) { height = it }

            // Controllers for Offset
            SliderWithValue("Offset", offset, -100f, 100f) { offset = it }


            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(
                    shape = RectangleShape,
                    modifier = Modifier
                        .graphicsLayer(
                            scaleX = animatedScaleX,
                            scaleY = animatedScaleY,
                            alpha = animatedAlpha,
                            rotationX = animatedRotationX,
                            rotationY = animatedRotationY,
                            rotationZ = animatedRotationZ,
                            translationX = animatedTranslationX,
                            translationY = animatedTranslationY,
                            shadowElevation = animatedShadowElevation,
                            cameraDistance = animatedCameraDistance
                        )
                        .padding(
                            start = paddingStart.dp,
                            top = paddingTop.dp,
                            end = paddingEnd.dp,
                            bottom = paddingBottom.dp
                        )
                        .then(
                            if (blurRadius > 0) Modifier.blur(blurRadius.dp)
                            else Modifier
                        )
                        .size(width.dp, height.dp)
                        .offset(animatedOffset.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = { /* Handle Tap */ },
                                onLongPress = { /* Handle Long Press */ }
                            )
                        }
                        .draggable(
                            state = rememberDraggableState { delta ->
                                translationX += delta
                            },
                            orientation = Orientation.Horizontal
                        )
                        .draggable(
                            state = rememberDraggableState { delta ->
                                translationY += delta
                            },
                            orientation = Orientation.Vertical
                        )
                        .clickable { /* Handle Click */ },
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Test")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    shape = RectangleShape,
                    modifier = Modifier,
                    onClick = resetValues
                ) {
                    Text(text = "Reset")
                }
            }
        }
    }
}

@Composable
fun SliderWithValue(label: String, value: Float, rangeStart: Float, rangeEnd: Float, onValueChange: (Float) -> Unit) {
    Column {
        Text("$label: ${value.format(2)}")
        Slider(value = value, onValueChange = onValueChange, valueRange = rangeStart..rangeEnd)
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ComprehensiveModifierTestScreenPreview() {
    ComprehensiveModifierTestScreen()
}

private fun Float.format(digits: Int) = "%.${digits}f".format(this)



