package com.udyata.exoplayer.audio

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun VoiceRecorderButton(
    modifier: Modifier =Modifier,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val rippleAnim = rememberInfiniteTransition(label = "")
    val rippleRadius by rippleAnim.animateFloat(
        initialValue = 0f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),

        ), label = ""
    )

    val selectedBoxSize by animateDpAsState(
        if (isPressed) 70.dp else 40.dp, label = "selectedSize"
    )
    val selectedIconSize by animateDpAsState(
        if (isPressed) 50.dp else 30.dp, label = "selectedSize"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(selectedBoxSize)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        onStartRecording()
                        tryAwaitRelease()
                        isPressed = false
                        onStopRecording()
                    }
                )
            }
    ) {
        if (isPressed) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color.Red.copy(alpha = 0.1f),
                    radius = rippleRadius,
                    center = Offset(size.width / 2, size.height / 2)
                )
            }
        }

        Icon(
            imageVector = Icons.Default.Mic,
            contentDescription = "Record",
            modifier = Modifier.size(selectedIconSize),
            tint = MaterialTheme.colorScheme.background
        )
    }
}


@Composable
fun DraggableVoiceRecorderButton(
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }

    val animatedOffsetX by animateFloatAsState(targetValue = if (isDragging) offsetX else 0f,
        label = ""
    )
    val animatedOffsetY by animateFloatAsState(targetValue = if (isDragging) offsetY else 0f,
        label = ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            //.offset { IntOffset(animatedOffsetX.roundToInt(), animatedOffsetY.roundToInt()) }
            .offset { IntOffset(offsetX.toInt(), offsetY.toInt()) }
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    offsetX = (offsetX + delta).coerceAtLeast(-200f)
                }
            )
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    offsetY = (offsetY + delta).coerceAtLeast(-200f)
                }
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isDragging = true
                        tryAwaitRelease()
                        isDragging = false
                    }
                )
            }
            .padding(8.dp)
    ) {
        VoiceRecorderButton(
            modifier = Modifier,
            onStartRecording = onStartRecording,
            onStopRecording = onStopRecording
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun VoiceRecorderButtonPreview1() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(12.dp))

            )
        }

        Box(modifier = Modifier
            .align(Alignment.BottomEnd)
        ){
            DraggableVoiceRecorderButton(
                onStartRecording = { /* TODO: Start recording logic */ },
                onStopRecording = { /* TODO: Stop recording logic */ }
            )
        }

    }
}