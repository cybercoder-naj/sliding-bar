package me.nishant.sliding_bar

import android.content.res.Resources
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs

@ExperimentalComposeUiApi
@Composable
fun SlidingBar(
    value: Float,
    onValueChanged: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    stepSize: Float = 0.01f,
    colors: SlidingBarColors = SlidingBarDefaults.colors()
) {
    var pressed by remember { mutableStateOf(false) }
    val radius by animateDpAsState(targetValue = if (pressed) 20.dp else 0.dp)
    var canvasSize by remember { mutableStateOf(Size(0f, 0f)) }
    var downX by remember { mutableStateOf(0f) }

    Canvas(
        modifier = modifier
            .pointerInteropFilter {
                val range = valueRange.endInclusive - valueRange.start
                val threshold = canvasSize.width / (range / stepSize)
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (!enabled)
                            return@pointerInteropFilter false

                        val point = Offset(
                            (value - valueRange.start) / (valueRange.endInclusive - valueRange.start) * canvasSize.width,
                            canvasSize.height / 2f
                        )
                        if (it.x in (point.x - toPx(12.dp))..(point.x + toPx(12.dp)) &&
                            it.y in (point.y - toPx(12.dp))..(point.y + toPx(12.dp))
                        ) {
                            pressed = true
                            downX = it.x
                            true
                        } else false
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val dx = it.x - downX
                        if (abs(dx) >= threshold) {
                            val newValue = if (dx > 0) value + stepSize else value - stepSize
                            if (newValue in valueRange) {
                                onValueChanged(newValue)
                                downX = it.x
                            }
                        }
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        downX = 0f
                        pressed = false
                        true
                    }
                    else -> false
                }
            }
    ) {
        canvasSize = size
        val (width) = size
        val point = Offset(
            (value - valueRange.start) / (valueRange.endInclusive - valueRange.start) * width,
            center.y
        )
        drawCircle(
            color = colors.colorPrimary,
            radius = radius.toPx(),
            center = point,
            alpha = 0.2f
        )
        drawLine(
            color = colors.colorTrack,
            start = Offset(0f, center.y),
            end = Offset(width, center.y),
            strokeWidth = 2.dp.toPx(),
            alpha = 0.5f
        )
        drawLine(
            color = colors.colorTrack,
            start = Offset(0f, center.y),
            end = point,
            strokeWidth = 2.dp.toPx()
        )
        drawCircle(
            color = colors.colorPrimary,
            radius = 12.dp.toPx(),
            center = point
        )
    }
}

private fun toPx(dp: Dp) =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.value,
        Resources.getSystem().displayMetrics
    )

