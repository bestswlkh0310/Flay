package com.bestswlkh0310.flay.ui

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bestswlkh0310.flay.ui.theme.main130
import com.bestswlkh0310.flay.ui.theme.main150
import com.bestswlkh0310.flay.ui.theme.main30
import com.bestswlkh0310.flay.ui.theme.noToSansKr

@Composable
fun FlayBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = contentColorFor(backgroundColor),
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        modifier = modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceAround,
            content = content
        )
    }
}

@Composable
fun FlayText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = main150,
    fontSize: TextUnit = 15.sp,
) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontFamily = noToSansKr,
        fontWeight = FontWeight.Normal,
        modifier = modifier
    )
}

@Composable
fun FlayBottomButton(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        content = content,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = main130
        ),
        shape = RoundedCornerShape(20.dp)
    )
}

@SuppressLint("ModifierParameter")
@Composable
fun FlayTitle(
    text: String,
    marginTop: Dp = 80.dp,
    marginBottom: Dp = 30.dp,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.height(marginTop))
    FlayText(
        text = text,
        fontSize = 24.sp,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(marginBottom))
}

@Composable
fun FlayIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    contentDescription: String,
    containerColor: Color = Color.Transparent,
    size: Dp = 20.dp,
    onClick: () -> Unit
) {
    Box {
        FlayBottomButton(
            onClick = onClick,
            modifier = modifier
                .size(size.times(2))
                .clip(CircleShape),
            containerColor = containerColor
        ) {

        }
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            tint = main150,
            modifier = Modifier
                .size(size)
                .offset(x = (size.div(2)), y = size.div(2))
        )
    }
}

@Composable
fun FlayLazyColumn(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    horizontalAlignment: Alignment.Horizontal,
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 9.dp)
            .height(500.dp)
            .fillMaxWidth(),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(9.dp),
        horizontalAlignment = horizontalAlignment,
        content = content,
    )
}

@Composable
fun FlayLazyColumnItem(
    modifier: Modifier = Modifier,
    height: Dp = 120.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .drawColoredShadow(Color.Black, 0.04f, 15.dp, 4.dp, 1.5.dp, 0.0.dp)
            .background(main30, shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(height)
            .padding(horizontal = 9.dp)
    ) {
        content()
    }
}