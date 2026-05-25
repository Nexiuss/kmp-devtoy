package com.nexius.devtoy.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object Icons {

    val ArrowBack: ImageVector
        get() {
            if (_arrowBack != null) return _arrowBack!!
            _arrowBack = ImageVector.Builder(
                name = "arrow_back",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(19f, 13f)
                        horizontalLineTo(9.83f)
                        lineToRelative(4.88f, -4.88f)
                        lineToRelative(-1.41f, -1.41f)
                        lineTo(4f, 12f)
                        lineToRelative(9.29f, 9.29f)
                        lineToRelative(1.41f, -1.41f)
                        lineTo(9.83f, 13f)
                        horizontalLineTo(19f)
                        verticalLineToRelative(-2f)
                        close()
                    }
                }
                .build()
            return _arrowBack!!
        }

    private var _arrowBack: ImageVector? = null


    val AddBox: ImageVector
        get() {
            if (_addBox != null) return _addBox!!
            _addBox = ImageVector.Builder(
                name = "add_box",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(11f, 17f)
                        horizontalLineToRelative(2f)
                        verticalLineTo(13f)
                        horizontalLineToRelative(4f)
                        verticalLineTo(11f)
                        horizontalLineTo(13f)
                        verticalLineTo(7f)
                        horizontalLineTo(11f)
                        verticalLineToRelative(4f)
                        horizontalLineTo(7f)
                        verticalLineToRelative(2f)
                        horizontalLineToRelative(4f)
                        verticalLineToRelative(4f)
                        close()
                        moveTo(5f, 21f)
                        quadTo(4.18f, 21f, 3.59f, 20.41f)
                        reflectiveQuadTo(3f, 19f)
                        verticalLineTo(5f)
                        quadTo(3f, 4.17f, 3.59f, 3.59f)
                        reflectiveQuadTo(5f, 3f)
                        horizontalLineTo(19f)
                        quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                        reflectiveQuadTo(21f, 5f)
                        verticalLineTo(19f)
                        quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                        reflectiveQuadTo(19f, 21f)
                        horizontalLineTo(5f)
                        close()
                        moveTo(5f, 19f)
                        horizontalLineTo(19f)
                        verticalLineTo(5f)
                        horizontalLineTo(5f)
                        verticalLineTo(19f)
                        close()
                    }
                }
                .build()
            return _addBox!!
        }

    private var _addBox: ImageVector? = null


    val Article: ImageVector
        get() {
            if (_article != null) return _article!!
            _article = ImageVector.Builder(
                name = "article",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(19f, 5f)
                        verticalLineToRelative(14f)
                        horizontalLineTo(5f)
                        verticalLineTo(5f)
                        horizontalLineTo(19f)
                        moveTo(19f, 3f)
                        verticalLineToRelative(14f)
                        quadToRelative(0f, 1.1f, 0.9f, 2f)
                        horizontalLineToRelative(14f)
                        quadToRelative(1.1f, 0f, 2f, -0.9f)
                        verticalLineTo(5f)
                        quadToRelative(0f, -1.1f, -0.9f, -2f)
                        close()
                        moveTo(7f, 12f)
                        horizontalLineToRelative(10f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(7f)
                        close()
                        moveTo(7f, 16f)
                        horizontalLineToRelative(7f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(7f)
                        close()
                        moveTo(7f, 8f)
                        horizontalLineToRelative(10f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(7f)
                        close()
                    }
                }
                .build()
            return _article!!
        }

    private var _article: ImageVector? = null


    val AttachFile: ImageVector
        get() {
            if (_attachFile != null) return _attachFile!!
            _attachFile = ImageVector.Builder(
                name = "attach_file",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(16.5f, 6f)
                        verticalLineTo(18f)
                        quadToRelative(0f, 2.08f, -1.46f, 3.54f)
                        reflectiveQuadTo(11.5f, 23f)
                        reflectiveQuadToRelative(-3.54f, -1.46f)
                        reflectiveQuadTo(6.5f, 18f)
                        verticalLineTo(6f)
                        quadToRelative(0f, -2.08f, 1.46f, -3.54f)
                        reflectiveQuadTo(11.5f, 1f)
                        reflectiveQuadToRelative(3.54f, 1.46f)
                        reflectiveQuadTo(16.5f, 6f)
                        close()
                        moveTo(11.5f, 21f)
                        quadToRelative(1.46f, 0f, 2.48f, -1.02f)
                        reflectiveQuadTo(15f, 18f)
                        verticalLineTo(6f)
                        quadToRelative(0f, -1.46f, -1.02f, -2.48f)
                        reflectiveQuadTo(11.5f, 2.5f)
                        reflectiveQuadToRelative(-2.48f, 1.02f)
                        reflectiveQuadTo(8f, 6f)
                        verticalLineToRelative(12f)
                        quadToRelative(0f, 1.46f, 1.02f, 2.48f)
                        reflectiveQuadTo(11.5f, 21f)
                        close()
                    }
                }
                .build()
            return _attachFile!!
        }

    private var _attachFile: ImageVector? = null


    val Build: ImageVector
        get() {
            if (_build != null) return _build!!
            _build = ImageVector.Builder(
                name = "build",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(22.7f, 19f)
                        lineTo(-9.1f, -9.1f)
                        quadToRelative(0.7f, -1.5f, 0.1f, -3.2f)
                        reflectiveQuadToRelative(-2.2f, -2.7f)
                        reflectiveQuadToRelative(-3.2f, -0.1f)
                        reflectiveQuadToRelative(-2.7f, 2.2f)
                        reflectiveQuadToRelative(0.1f, 3.2f)
                        reflectiveQuadToRelative(2.7f, 2.7f)
                        quadToRelative(1.1f, 0.5f, 2.3f, 0.6f)
                        lineTo(9.1f, 9.1f)
                        quadToRelative(0.4f, 0.4f, 1f, 0.4f)
                        reflectiveQuadToRelative(1f, -0.4f)
                        lineTo(1.8f, -1.8f)
                        quadToRelative(0.4f, -0.4f, 0.4f, -1f)
                        reflectiveQuadToRelative(-0.4f, -1f)
                        close()
                        moveTo(7f, 11f)
                        quadToRelative(-1.25f, 0f, -2.12f, -0.87f)
                        reflectiveQuadTo(4f, 8f)
                        reflectiveQuadToRelative(0.88f, -2.12f)
                        reflectiveQuadTo(7f, 5f)
                        reflectiveQuadToRelative(2.12f, 0.88f)
                        reflectiveQuadTo(10f, 8f)
                        reflectiveQuadToRelative(-0.88f, 2.12f)
                        reflectiveQuadTo(7f, 11f)
                        close()
                    }
                }
                .build()
            return _build!!
        }

    private var _build: ImageVector? = null


    val Calculate: ImageVector
        get() {
            if (_calculate != null) return _calculate!!
            _calculate = ImageVector.Builder(
                name = "calculate",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(19f, 3f)
                        horizontalLineTo(5f)
                        quadToRelative(-1.1f, 0f, -1.99f, 0.9f)
                        reflectiveQuadTo(2f, 5.9f)
                        verticalLineToRelative(12f)
                        quadToRelative(0f, 1.1f, 0.89f, 2f)
                        reflectiveQuadTo(5f, 20.9f)
                        horizontalLineTo(14f)
                        quadToRelative(1.1f, 0f, 1.99f, -0.9f)
                        reflectiveQuadTo(22f, 17.9f)
                        verticalLineTo(5.9f)
                        quadToRelative(0f, -1.1f, -0.9f, -2f)
                        reflectiveQuadTo(19f, 3f)
                        close()
                        moveTo(19f, 18f)
                        horizontalLineTo(5f)
                        verticalLineTo(6f)
                        horizontalLineTo(19f)
                        verticalLineToRelative(12f)
                        close()
                        moveTo(7f, 12f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineToRelative(-2f)
                        close()
                        moveTo(7f, 15f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineToRelative(-2f)
                        close()
                        moveTo(10f, 12f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineToRelative(-2f)
                        close()
                        moveTo(10f, 15f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineToRelative(-2f)
                        close()
                        moveTo(13f, 12f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineToRelative(-2f)
                        close()
                        moveTo(13f, 15f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineToRelative(-2f)
                        close()
                        moveTo(7f, 9f)
                        horizontalLineToRelative(10f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(7f)
                        close()
                    }
                }
                .build()
            return _calculate!!
        }

    private var _calculate: ImageVector? = null


    val Check: ImageVector
        get() {
            if (_check != null) return _check!!
            _check = ImageVector.Builder(
                name = "check",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(9f, 16.17f)
                        lineToRelative(-4.17f, -4.17f)
                        lineToRelative(-1.42f, 1.41f)
                        lineTo(9f, 19f)
                        lineTo(21f, 7f)
                        lineToRelative(-1.41f, -1.41f)
                        close()
                    }
                }
                .build()
            return _check!!
        }

    private var _check: ImageVector? = null


    val Clear: ImageVector
        get() {
            if (_clear != null) return _clear!!
            _clear = ImageVector.Builder(
                name = "clear",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(19f, 6.41f)
                        lineTo(17.59f, 5f)
                        lineTo(12f, 10.59f)
                        lineTo(6.41f, 5f)
                        lineTo(5f, 6.41f)
                        lineTo(10.59f, 12f)
                        lineTo(5f, 17.59f)
                        lineTo(6.41f, 19f)
                        lineTo(12f, 13.41f)
                        lineTo(17.59f, 19f)
                        lineTo(19f, 17.59f)
                        lineTo(13.41f, 12f)
                        close()
                    }
                }
                .build()
            return _clear!!
        }

    private var _clear: ImageVector? = null


    val ClearAll: ImageVector
        get() {
            if (_clearAll != null) return _clearAll!!
            _clearAll = ImageVector.Builder(
                name = "clear_all",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(6f, 19f)
                        horizontalLineToRelative(12f)
                        verticalLineToRelative(-2f)
                        horizontalLineTo(6f)
                        close()
                        moveTo(17.64f, 7.22f)
                        lineToRelative(-1.77f, -1.77f)
                        lineToRelative(-3.67f, 3.67f)
                        lineToRelative(-3.67f, -3.67f)
                        lineToRelative(-1.77f, 1.77f)
                        lineToRelative(3.67f, 3.67f)
                        lineToRelative(-3.67f, 3.67f)
                        lineToRelative(1.77f, 1.77f)
                        lineToRelative(3.67f, -3.67f)
                        lineToRelative(3.67f, 3.67f)
                        lineToRelative(1.77f, -1.77f)
                        lineToRelative(-3.67f, -3.67f)
                        close()
                    }
                }
                .build()
            return _clearAll!!
        }

    private var _clearAll: ImageVector? = null


    val Close: ImageVector
        get() {
            if (_close != null) return _close!!
            _close = ImageVector.Builder(
                name = "close",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(19f, 6.41f)
                        lineTo(17.59f, 5f)
                        lineTo(12f, 10.59f)
                        lineTo(6.41f, 5f)
                        lineTo(5f, 6.41f)
                        lineTo(10.59f, 12f)
                        lineTo(5f, 17.59f)
                        lineTo(6.41f, 19f)
                        lineTo(12f, 13.41f)
                        lineTo(17.59f, 19f)
                        lineTo(19f, 17.59f)
                        lineTo(13.41f, 12f)
                        close()
                    }
                }
                .build()
            return _close!!
        }

    private var _close: ImageVector? = null


    val Code: ImageVector
        get() {
            if (_code != null) return _code!!
            _code = ImageVector.Builder(
                name = "code",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(9.4f, 16.6f)
                        lineTo(4.8f, 12f)
                        lineToRelative(4.6f, -4.6f)
                        lineTo(8f, 6f)
                        lineToRelative(-6f, 6f)
                        lineToRelative(6f, 6f)
                        close()
                        moveTo(14.6f, 16.6f)
                        lineTo(19.2f, 12f)
                        lineToRelative(-4.6f, -4.6f)
                        lineTo(16f, 6f)
                        lineToRelative(6f, 6f)
                        lineToRelative(-6f, 6f)
                        close()
                    }
                }
                .build()
            return _code!!
        }

    private var _code: ImageVector? = null


    val ContentCopy: ImageVector
        get() {
            if (_contentCopy != null) return _contentCopy!!
            _contentCopy = ImageVector.Builder(
                name = "content_copy",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(16f, 1f)
                        horizontalLineTo(4f)
                        quadToRelative(-1.1f, 0f, -1.99f, 0.9f)
                        reflectiveQuadTo(1f, 3.9f)
                        verticalLineToRelative(14f)
                        quadToRelative(0f, 1.1f, 0.89f, 2f)
                        reflectiveQuadTo(4f, 20.9f)
                        horizontalLineTo(12f)
                        quadToRelative(1.1f, 0f, 1.99f, -0.9f)
                        reflectiveQuadTo(19f, 17.9f)
                        verticalLineTo(3f)
                        quadToRelative(0f, -1.1f, -0.9f, -2f)
                        reflectiveQuadTo(16f, 1f)
                        close()
                        moveTo(17f, 18f)
                        horizontalLineTo(3f)
                        verticalLineTo(4f)
                        horizontalLineTo(17f)
                        verticalLineToRelative(14f)
                        close()
                        moveTo(21f, 5f)
                        horizontalLineToRelative(-2f)
                        verticalLineToRelative(14f)
                        quadToRelative(0f, 1.1f, -0.9f, 2f)
                        reflectiveQuadToRelative(-2f, -0.9f)
                        horizontalLineToRelative(8f)
                        verticalLineTo(7f)
                        quadToRelative(0f, -1.1f, -0.9f, -2f)
                        close()
                    }
                }
                .build()
            return _contentCopy!!
        }

    private var _contentCopy: ImageVector? = null


    val DataArray: ImageVector
        get() {
            if (_dataArray != null) return _dataArray!!
            _dataArray = ImageVector.Builder(
                name = "data_array",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(6f, 9.99f)
                        horizontalLineToRelative(2.5f)
                        verticalLineToRelative(4.02f)
                        horizontalLineTo(6f)
                        close()
                        moveTo(10.32f, 10.7f)
                        lineToRelative(2.5f, 4.03f)
                        horizontalLineToRelative(1.8f)
                        lineToRelative(-2.5f, -4.03f)
                        close()
                        moveTo(15.5f, 9.99f)
                        horizontalLineTo(18f)
                        verticalLineToRelative(4.02f)
                        horizontalLineToRelative(-2.5f)
                        close()
                        moveTo(20f, 4f)
                        horizontalLineTo(4f)
                        quadToRelative(-1.1f, 0f, -1.99f, 0.9f)
                        reflectiveQuadTo(1f, 6.9f)
                        verticalLineToRelative(10f)
                        quadToRelative(0f, 1.1f, 0.89f, 2f)
                        reflectiveQuadTo(4f, 19.9f)
                        horizontalLineTo(16f)
                        quadToRelative(1.1f, 0f, 1.99f, -0.9f)
                        reflectiveQuadTo(22f, 16.9f)
                        verticalLineTo(6.9f)
                        quadToRelative(0f, -1.1f, -0.9f, -2f)
                        reflectiveQuadTo(20f, 4f)
                        close()
                        moveTo(20f, 17f)
                        horizontalLineTo(4f)
                        verticalLineTo(7f)
                        horizontalLineTo(20f)
                        verticalLineToRelative(10f)
                        close()
                    }
                }
                .build()
            return _dataArray!!
        }

    private var _dataArray: ImageVector? = null


    val Delete: ImageVector
        get() {
            if (_delete != null) return _delete!!
            _delete = ImageVector.Builder(
                name = "delete",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(6f, 21f)
                        quadToRelative(-1.1f, 0f, -1.99f, -0.9f)
                        reflectiveQuadTo(3f, 18f)
                        verticalLineTo(7f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(11f)
                        horizontalLineTo(16f)
                        verticalLineTo(7f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(11f)
                        quadToRelative(0f, 1.1f, -0.9f, 2f)
                        reflectiveQuadToRelative(-2f, 0.9f)
                        close()
                        moveTo(18f, 9f)
                        horizontalLineTo(6f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(18f)
                        close()
                        moveTo(10.5f, 13f)
                        horizontalLineToRelative(3f)
                        verticalLineToRelative(6f)
                        horizontalLineToRelative(-3f)
                        close()
                        moveTo(17f, 4f)
                        horizontalLineTo(7f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(17f)
                        close()
                    }
                }
                .build()
            return _delete!!
        }

    private var _delete: ImageVector? = null


    val DriveFolderUpload: ImageVector
        get() {
            if (_driveFolderUpload != null) return _driveFolderUpload!!
            _driveFolderUpload = ImageVector.Builder(
                name = "drive_folder_upload",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(9f, 13f)
                        horizontalLineToRelative(6f)
                        lineToRelative(-3f, -4f)
                        close()
                        moveTo(20f, 6f)
                        horizontalLineToRelative(-8f)
                        lineToRelative(-2f, -2f)
                        horizontalLineTo(4f)
                        quadToRelative(-1.1f, 0f, -1.99f, 0.9f)
                        reflectiveQuadTo(1f, 8.9f)
                        verticalLineToRelative(10f)
                        quadToRelative(0f, 1.1f, 0.89f, 2f)
                        reflectiveQuadTo(4f, 21.9f)
                        horizontalLineTo(16f)
                        quadToRelative(1.1f, 0f, 1.99f, -0.9f)
                        reflectiveQuadTo(22f, 18.9f)
                        verticalLineTo(8f)
                        quadToRelative(0f, -1.1f, -0.9f, -2f)
                        reflectiveQuadTo(20f, 6f)
                        close()
                        moveTo(20f, 18f)
                        horizontalLineTo(4f)
                        verticalLineTo(8f)
                        horizontalLineTo(20f)
                        verticalLineToRelative(10f)
                        close()
                    }
                }
                .build()
            return _driveFolderUpload!!
        }

    private var _driveFolderUpload: ImageVector? = null


    val FilePresent: ImageVector
        get() {
            if (_filePresent != null) return _filePresent!!
            _filePresent = ImageVector.Builder(
                name = "file_present",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(14f, 2f)
                        horizontalLineTo(6f)
                        quadToRelative(-1.1f, 0f, -1.99f, 0.9f)
                        reflectiveQuadTo(3f, 4.9f)
                        verticalLineToRelative(14f)
                        quadToRelative(0f, 1.1f, 0.89f, 2f)
                        reflectiveQuadTo(6f, 21.9f)
                        horizontalLineTo(18f)
                        quadToRelative(1.1f, 0f, 1.99f, -0.9f)
                        reflectiveQuadTo(20f, 18.9f)
                        verticalLineTo(8f)
                        close()
                        moveTo(13f, 9f)
                        horizontalLineToRelative(4f)
                        lineToRelative(-4f, -4f)
                        close()
                        moveTo(12f, 17f)
                        lineToRelative(-4f, -4f)
                        lineToRelative(1.41f, -1.41f)
                        lineToRelative(2.59f, 2.59f)
                        lineToRelative(5.59f, -5.59f)
                        lineToRelative(1.41f, 1.41f)
                        close()
                    }
                }
                .build()
            return _filePresent!!
        }

    private var _filePresent: ImageVector? = null


    val Folder: ImageVector
        get() {
            if (_folder != null) return _folder!!
            _folder = ImageVector.Builder(
                name = "folder",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(10f, 4f)
                        horizontalLineTo(4f)
                        quadToRelative(-1.1f, 0f, -1.99f, 0.9f)
                        reflectiveQuadTo(1f, 6.9f)
                        verticalLineToRelative(10f)
                        quadToRelative(0f, 1.1f, 0.89f, 2f)
                        reflectiveQuadTo(4f, 19.9f)
                        horizontalLineTo(16f)
                        quadToRelative(1.1f, 0f, 1.99f, -0.9f)
                        reflectiveQuadTo(22f, 17.9f)
                        verticalLineTo(8f)
                        quadToRelative(0f, -1.1f, -0.9f, -2f)
                        reflectiveQuadTo(20f, 5f)
                        horizontalLineToRelative(-8f)
                        close()
                    }
                }
                .build()
            return _folder!!
        }

    private var _folder: ImageVector? = null


    val FolderOpen: ImageVector
        get() {
            if (_folderOpen != null) return _folderOpen!!
            _folderOpen = ImageVector.Builder(
                name = "folder_open",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(20f, 6f)
                        horizontalLineToRelative(-8f)
                        lineToRelative(-2f, -2f)
                        horizontalLineTo(4f)
                        quadToRelative(-1.1f, 0f, -1.99f, 0.9f)
                        reflectiveQuadTo(1f, 6.9f)
                        verticalLineToRelative(8f)
                        quadToRelative(0f, 1.1f, 0.89f, 2f)
                        reflectiveQuadTo(4f, 17.9f)
                        horizontalLineTo(19f)
                        quadToRelative(1.1f, 0f, 1.99f, -0.9f)
                        reflectiveQuadTo(21f, 15.9f)
                        verticalLineTo(8f)
                        quadToRelative(0f, -1.1f, -0.9f, -2f)
                        reflectiveQuadTo(20f, 6f)
                        close()
                        moveTo(4f, 13f)
                        horizontalLineTo(16f)
                        verticalLineToRelative(3f)
                        horizontalLineTo(4f)
                        close()
                    }
                }
                .build()
            return _folderOpen!!
        }

    private var _folderOpen: ImageVector? = null


    val FormatPaint: ImageVector
        get() {
            if (_formatPaint != null) return _formatPaint!!
            _formatPaint = ImageVector.Builder(
                name = "format_paint",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(18f, 4f)
                        verticalLineTo(3f)
                        quadToRelative(0f, -1.1f, -0.9f, -2f)
                        reflectiveQuadToRelative(-2f, -0.9f)
                        horizontalLineTo(5f)
                        quadToRelative(-1.1f, 0f, -2f, 0.9f)
                        reflectiveQuadToRelative(-0.9f, 2f)
                        verticalLineToRelative(1f)
                        close()
                        moveTo(6f, 5f)
                        horizontalLineTo(14f)
                        verticalLineTo(4f)
                        horizontalLineTo(6f)
                        close()
                        moveTo(19f, 6f)
                        horizontalLineTo(3f)
                        verticalLineToRelative(5f)
                        horizontalLineTo(19f)
                        close()
                        moveTo(19f, 13f)
                        horizontalLineTo(3f)
                        verticalLineToRelative(5f)
                        quadToRelative(0f, 1.1f, 0.9f, 2f)
                        reflectiveQuadToRelative(2f, 0.9f)
                        horizontalLineTo(13f)
                        quadToRelative(1.1f, 0f, 2f, -0.9f)
                        reflectiveQuadToRelative(0.9f, -2f)
                        close()
                    }
                }
                .build()
            return _formatPaint!!
        }

    private var _formatPaint: ImageVector? = null


    val GppGood: ImageVector
        get() {
            if (_gppGood != null) return _gppGood!!
            _gppGood = ImageVector.Builder(
                name = "gpp_good",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(12f, 2f)
                        lineTo(4f, 6f)
                        verticalLineToRelative(12f)
                        lineToRelative(8f, 4f)
                        lineToRelative(8f, -4f)
                        verticalLineTo(6f)
                        close()
                        moveTo(10f, 17f)
                        lineToRelative(-3f, -3f)
                        lineToRelative(1.41f, -1.41f)
                        lineTo(10f, 14.17f)
                        lineToRelative(5.59f, -5.59f)
                        lineTo(17f, 10f)
                        close()
                    }
                }
                .build()
            return _gppGood!!
        }

    private var _gppGood: ImageVector? = null


    val Home: ImageVector
        get() {
            if (_home != null) return _home!!
            _home = ImageVector.Builder(
                name = "home",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(12f, 3f)
                        lineTo(2f, 12f)
                        horizontalLineToRelative(3f)
                        verticalLineToRelative(8f)
                        horizontalLineTo(16f)
                        verticalLineToRelative(-8f)
                        horizontalLineToRelative(3f)
                        close()
                        moveTo(10f, 20f)
                        verticalLineTo(14f)
                        horizontalLineToRelative(4f)
                        verticalLineToRelative(6f)
                        close()
                    }
                }
                .build()
            return _home!!
        }

    private var _home: ImageVector? = null


    val Html: ImageVector
        get() {
            if (_html != null) return _html!!
            _html = ImageVector.Builder(
                name = "html",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(3f, 5f)
                        verticalLineToRelative(14f)
                        horizontalLineTo(21f)
                        verticalLineTo(5f)
                        close()
                        moveTo(16f, 17f)
                        horizontalLineTo(8f)
                        verticalLineToRelative(-2f)
                        horizontalLineTo(16f)
                        close()
                        moveTo(14f, 13f)
                        horizontalLineTo(8f)
                        verticalLineToRelative(-2f)
                        horizontalLineTo(14f)
                        close()
                        moveTo(12f, 9f)
                        horizontalLineTo(8f)
                        verticalLineTo(7f)
                        horizontalLineTo(12f)
                        close()
                    }
                }
                .build()
            return _html!!
        }

    private var _html: ImageVector? = null


    val Http: ImageVector
        get() {
            if (_http != null) return _http!!
            _http = ImageVector.Builder(
                name = "http",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(4.5f, 7f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(10f)
                        horizontalLineToRelative(-2f)
                        close()
                        moveTo(8.5f, 7f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(10f)
                        horizontalLineToRelative(-2f)
                        close()
                        moveTo(16.5f, 14.5f)
                        lineTo(14.5f, 12f)
                        horizontalLineToRelative(2f)
                        lineToRelative(1f, 1.5f)
                        lineToRelative(1f, -1.5f)
                        horizontalLineToRelative(2f)
                        lineToRelative(-2.5f, 2.5f)
                        close()
                        moveTo(12.5f, 10.5f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(4f)
                        horizontalLineToRelative(-2f)
                        close()
                        moveTo(3f, 5f)
                        verticalLineToRelative(14f)
                        horizontalLineTo(21f)
                        verticalLineTo(5f)
                        close()
                        moveTo(20f, 17f)
                        horizontalLineTo(4f)
                        verticalLineTo(7f)
                        horizontalLineTo(20f)
                        close()
                    }
                }
                .build()
            return _http!!
        }

    private var _http: ImageVector? = null


    val Link: ImageVector
        get() {
            if (_link != null) return _link!!
            _link = ImageVector.Builder(
                name = "link",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(10.59f, 13f)
                        lineToRelative(3.54f, -3.54f)
                        lineToRelative(-1.41f, -1.41f)
                        lineToRelative(-3.54f, 3.54f)
                        close()
                        moveTo(16.83f, 13f)
                        lineToRelative(3.54f, -3.54f)
                        lineToRelative(-1.41f, -1.41f)
                        lineToRelative(-3.54f, 3.54f)
                        close()
                        moveTo(16.83f, 6.17f)
                        lineToRelative(3.54f, -3.54f)
                        lineToRelative(1.41f, 1.41f)
                        lineToRelative(-3.54f, 3.54f)
                        close()
                        moveTo(10.59f, 6.17f)
                        lineToRelative(3.54f, -3.54f)
                        lineToRelative(1.41f, 1.41f)
                        lineToRelative(-3.54f, 3.54f)
                        close()
                        moveTo(10f, 18f)
                        horizontalLineTo(14f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(10f)
                        close()
                    }
                }
                .build()
            return _link!!
        }

    private var _link: ImageVector? = null


    val Menu: ImageVector
        get() {
            if (_menu != null) return _menu!!
            _menu = ImageVector.Builder(
                name = "menu",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(3f, 18f)
                        horizontalLineTo(21f)
                        verticalLineToRelative(-2f)
                        horizontalLineTo(3f)
                        close()
                        moveTo(3f, 13f)
                        horizontalLineTo(21f)
                        verticalLineToRelative(-2f)
                        horizontalLineTo(3f)
                        close()
                        moveTo(3f, 6f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(21f)
                        verticalLineTo(6f)
                        close()
                    }
                }
                .build()
            return _menu!!
        }

    private var _menu: ImageVector? = null


    val MoreVert: ImageVector
        get() {
            if (_moreVert != null) return _moreVert!!
            _moreVert = ImageVector.Builder(
                name = "more_vert",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(12f, 8f)
                        quadToRelative(1.1f, 0f, 1.99f, -0.9f)
                        reflectiveQuadTo(14f, 5.1f)
                        reflectiveQuadToRelative(-0.9f, -1.99f)
                        reflectiveQuadTo(11f, 2.1f)
                        reflectiveQuadToRelative(-1.99f, 0.9f)
                        reflectiveQuadTo(8f, 5.1f)
                        reflectiveQuadToRelative(0.9f, 1.99f)
                        reflectiveQuadTo(11f, 8.1f)
                        close()
                        moveTo(12f, 14f)
                        quadToRelative(1.1f, 0f, 1.99f, -0.9f)
                        reflectiveQuadTo(14f, 11.1f)
                        reflectiveQuadToRelative(-0.9f, -1.99f)
                        reflectiveQuadTo(11f, 8.1f)
                        reflectiveQuadToRelative(-1.99f, 0.9f)
                        reflectiveQuadTo(8f, 11.1f)
                        reflectiveQuadToRelative(0.9f, 1.99f)
                        reflectiveQuadTo(11f, 14.1f)
                        close()
                        moveTo(12f, 20f)
                        quadToRelative(1.1f, 0f, 1.99f, -0.9f)
                        reflectiveQuadTo(14f, 17.1f)
                        reflectiveQuadToRelative(-0.9f, -1.99f)
                        reflectiveQuadTo(11f, 14.1f)
                        reflectiveQuadToRelative(-1.99f, 0.9f)
                        reflectiveQuadTo(8f, 17.1f)
                        reflectiveQuadToRelative(0.9f, 1.99f)
                        reflectiveQuadTo(11f, 20.1f)
                        close()
                    }
                }
                .build()
            return _moreVert!!
        }

    private var _moreVert: ImageVector? = null


    val NetworkPing: ImageVector
        get() {
            if (_networkPing != null) return _networkPing!!
            _networkPing = ImageVector.Builder(
                name = "network_ping",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(2f, 12f)
                        lineToRelative(4f, 4f)
                        lineToRelative(4f, -4f)
                        lineToRelative(-4f, -4f)
                        close()
                        moveTo(12f, 12f)
                        lineToRelative(4f, 4f)
                        lineToRelative(4f, -4f)
                        lineToRelative(-4f, -4f)
                        close()
                        moveTo(22f, 2f)
                        horizontalLineTo(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(22f)
                        close()
                    }
                }
                .build()
            return _networkPing!!
        }

    private var _networkPing: ImageVector? = null


    val QrCode: ImageVector
        get() {
            if (_qrCode != null) return _qrCode!!
            _qrCode = ImageVector.Builder(
                name = "qr_code",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(3f, 11f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(3f)
                        close()
                        moveTo(3f, 15f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(3f)
                        close()
                        moveTo(3f, 7f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(3f)
                        close()
                        moveTo(7f, 3f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(7f)
                        close()
                        moveTo(11f, 3f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(11f)
                        close()
                        moveTo(15f, 3f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(15f)
                        close()
                        moveTo(19f, 3f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(19f)
                        close()
                        moveTo(19f, 7f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(19f)
                        close()
                        moveTo(7f, 7f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(7f)
                        close()
                        moveTo(15f, 7f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(15f)
                        close()
                        moveTo(19f, 11f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(19f)
                        close()
                        moveTo(19f, 15f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(19f)
                        close()
                        moveTo(19f, 19f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(19f)
                        close()
                        moveTo(15f, 19f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(15f)
                        close()
                        moveTo(11f, 19f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(11f)
                        close()
                        moveTo(7f, 19f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(7f)
                        close()
                        moveTo(3f, 19f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(3f)
                        close()
                    }
                }
                .build()
            return _qrCode!!
        }

    private var _qrCode: ImageVector? = null


    val Restore: ImageVector
        get() {
            if (_restore != null) return _restore!!
            _restore = ImageVector.Builder(
                name = "restore",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(13f, 3f)
                        verticalLineToRelative(2f)
                        quadToRelative(4.13f, 0.4f, 6.95f, 3.23f)
                        reflectiveQuadTo(23f, 15f)
                        quadToRelative(0f, 4.18f, -2.9f, 7.1f)
                        reflectiveQuadToRelative(-7.1f, 2.9f)
                        quadToRelative(-4.18f, 0f, -7.1f, -2.9f)
                        reflectiveQuadToRelative(-2.9f, -7.1f)
                        horizontalLineTo(3f)
                        lineToRelative(4f, -4f)
                        lineToRelative(4f, 4f)
                        horizontalLineTo(7f)
                        quadToRelative(0f, 3.32f, 2.34f, 5.66f)
                        reflectiveQuadTo(15f, 20f)
                        quadToRelative(3.32f, 0f, 5.66f, -2.34f)
                        reflectiveQuadTo(22f, 12f)
                        quadToRelative(0f, -3.32f, -2.34f, -5.66f)
                        reflectiveQuadTo(14f, 4f)
                        horizontalLineToRelative(-1f)
                        close()
                    }
                }
                .build()
            return _restore!!
        }

    private var _restore: ImageVector? = null


    val Search: ImageVector
        get() {
            if (_search != null) return _search!!
            _search = ImageVector.Builder(
                name = "search",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(15.5f, 14f)
                        horizontalLineToRelative(-0.79f)
                        lineToRelative(-0.28f, -0.27f)
                        quadToRelative(1.2f, -1.36f, 1.2f, -3.23f)
                        reflectiveQuadToRelative(-1.2f, -3.23f)
                        reflectiveQuadToRelative(-3.23f, -1.2f)
                        reflectiveQuadToRelative(-3.23f, 1.2f)
                        reflectiveQuadToRelative(-1.2f, 3.23f)
                        reflectiveQuadToRelative(1.2f, 3.23f)
                        quadToRelative(1.87f, 0f, 3.23f, -1.2f)
                        lineToRelative(0.27f, 0.28f)
                        verticalLineToRelative(0.79f)
                        lineToRelative(4.25f, 4.25f)
                        lineToRelative(1.5f, -1.5f)
                        close()
                        moveTo(10f, 14f)
                        quadToRelative(-2.48f, 0f, -4.24f, -1.76f)
                        reflectiveQuadTo(4f, 8f)
                        reflectiveQuadToRelative(1.76f, -4.24f)
                        reflectiveQuadTo(10f, 2f)
                        reflectiveQuadToRelative(4.24f, 1.76f)
                        reflectiveQuadTo(16f, 8f)
                        reflectiveQuadToRelative(-1.76f, 4.24f)
                        reflectiveQuadTo(10f, 14f)
                        close()
                    }
                }
                .build()
            return _search!!
        }

    private var _search: ImageVector? = null


    val Send: ImageVector
        get() {
            if (_send != null) return _send!!
            _send = ImageVector.Builder(
                name = "send",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(2.01f, 21f)
                        lineTo(23f, 12f)
                        lineTo(2.01f, 3f)
                        lineTo(2f, 10f)
                        lineToRelative(15f, 2f)
                        lineToRelative(-15f, 2f)
                        close()
                    }
                }
                .build()
            return _send!!
        }

    private var _send: ImageVector? = null


    val Star: ImageVector
        get() {
            if (_star != null) return _star!!
            _star = ImageVector.Builder(
                name = "star",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(12f, 17.27f)
                        lineToRelative(4.15f, 2.51f)
                        lineToRelative(-1.1f, -4.72f)
                        lineToRelative(3.67f, -3.16f)
                        lineToRelative(-4.72f, -0.41f)
                        lineToRelative(-1.87f, -4.32f)
                        lineToRelative(-1.87f, 4.32f)
                        lineToRelative(-4.72f, 0.41f)
                        lineToRelative(3.67f, 3.16f)
                        lineToRelative(-1.1f, 4.72f)
                        close()
                    }
                }
                .build()
            return _star!!
        }

    private var _star: ImageVector? = null


    val Storage: ImageVector
        get() {
            if (_storage != null) return _storage!!
            _storage = ImageVector.Builder(
                name = "storage",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(2f, 20f)
                        horizontalLineTo(22f)
                        verticalLineToRelative(-4f)
                        horizontalLineTo(2f)
                        close()
                        moveTo(2f, 8f)
                        horizontalLineTo(22f)
                        verticalLineTo(4f)
                        horizontalLineTo(2f)
                        close()
                        moveTo(2f, 14f)
                        horizontalLineTo(22f)
                        verticalLineToRelative(-4f)
                        horizontalLineTo(2f)
                        close()
                    }
                }
                .build()
            return _storage!!
        }

    private var _storage: ImageVector? = null


    val TextFormat: ImageVector
        get() {
            if (_textFormat != null) return _textFormat!!
            _textFormat = ImageVector.Builder(
                name = "text_format",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(5f, 18f)
                        horizontalLineToRelative(2f)
                        lineToRelative(3f, -9f)
                        horizontalLineToRelative(2f)
                        lineToRelative(3f, 9f)
                        horizontalLineToRelative(2f)
                        lineToRelative(-4f, -10f)
                        horizontalLineTo(9f)
                        close()
                    }
                }
                .build()
            return _textFormat!!
        }

    private var _textFormat: ImageVector? = null


    val Timer: ImageVector
        get() {
            if (_timer != null) return _timer!!
            _timer = ImageVector.Builder(
                name = "timer",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(15f, 1f)
                        horizontalLineTo(9f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(15f)
                        close()
                        moveTo(12f, 20f)
                        quadToRelative(3.31f, 0f, 5.65f, -2.34f)
                        reflectiveQuadTo(20f, 12f)
                        reflectiveQuadToRelative(-2.35f, -5.65f)
                        reflectiveQuadTo(12f, 4f)
                        reflectiveQuadToRelative(-5.65f, 2.35f)
                        reflectiveQuadTo(4f, 12f)
                        reflectiveQuadToRelative(2.35f, 5.65f)
                        reflectiveQuadTo(12f, 20f)
                        close()
                        moveTo(12f, 22f)
                        quadToRelative(-4.14f, 0f, -7.07f, -2.93f)
                        reflectiveQuadTo(2f, 12f)
                        reflectiveQuadToRelative(2.93f, -7.07f)
                        reflectiveQuadTo(12f, 2f)
                        reflectiveQuadToRelative(7.07f, 2.93f)
                        reflectiveQuadTo(22f, 12f)
                        reflectiveQuadToRelative(-2.93f, 7.07f)
                        reflectiveQuadTo(12f, 22f)
                        close()
                        moveTo(13f, 7f)
                        horizontalLineToRelative(-2f)
                        verticalLineToRelative(6f)
                        horizontalLineToRelative(6f)
                        verticalLineToRelative(-2f)
                        close()
                    }
                }
                .build()
            return _timer!!
        }

    private var _timer: ImageVector? = null


    val Transform: ImageVector
        get() {
            if (_transform != null) return _transform!!
            _transform = ImageVector.Builder(
                name = "transform",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(22f, 18f)
                        verticalLineTo(9f)
                        horizontalLineTo(11f)
                        verticalLineTo(6f)
                        horizontalLineTo(5f)
                        verticalLineToRelative(15f)
                        horizontalLineTo(22f)
                        close()
                        moveTo(11f, 17f)
                        horizontalLineTo(7f)
                        verticalLineTo(8f)
                        horizontalLineTo(11f)
                        close()
                        moveTo(22f, 22f)
                        horizontalLineTo(2f)
                        verticalLineTo(3f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(16f)
                        horizontalLineTo(18f)
                        verticalLineToRelative(3f)
                        close()
                    }
                }
                .build()
            return _transform!!
        }

    private var _transform: ImageVector? = null


    val Verified: ImageVector
        get() {
            if (_verified != null) return _verified!!
            _verified = ImageVector.Builder(
                name = "verified",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(12f, 2f)
                        lineTo(4.22f, 6.22f)
                        lineTo(2f, 14f)
                        lineToRelative(7.78f, 7.78f)
                        lineTo(12f, 22f)
                        lineToRelative(4.22f, -4.22f)
                        lineTo(22f, 14f)
                        lineToRelative(-2.22f, -7.78f)
                        close()
                        moveTo(10.74f, 16.83f)
                        lineTo(6.5f, 12.58f)
                        lineToRelative(1.41f, -1.41f)
                        lineToRelative(2.83f, 2.83f)
                        lineToRelative(5.66f, -5.66f)
                        lineToRelative(1.41f, 1.41f)
                        close()
                    }
                }
                .build()
            return _verified!!
        }

    private var _verified: ImageVector? = null


    val ArrowDropDown: ImageVector
        get() {
            if (_arrow_drop_down != null) {
                return _arrow_drop_down!!
            }
            _arrow_drop_down =
                ImageVector.Builder(
                    name = "arrow_drop_down",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(12f, 15f)
                            lineTo(7f, 10f)
                            horizontalLineTo(17f)
                            lineToRelative(-5f, 5f)
                            close()
                        }
                    }
                    .build()
            return _arrow_drop_down!!
        }

    private var _arrow_drop_down: ImageVector? = null

    val ChevronRight: ImageVector
        get() {
            if (_chevron_right != null) {
                return _chevron_right!!
            }
            _chevron_right =
                ImageVector.Builder(
                    name = "chevron_right",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(12.6f, 12f)
                            lineTo(8f, 7.4f)
                            lineTo(9.4f, 6f)
                            lineToRelative(6f, 6f)
                            lineToRelative(-6f, 6f)
                            lineTo(8f, 16.6f)
                            lineTo(12.6f, 12f)
                            close()
                        }
                    }
                    .build()
            return _chevron_right!!
        }

    private var _chevron_right: ImageVector? = null

    val CheckCircle: ImageVector
        get() {
            if (_check_circle != null) {
                return _check_circle!!
            }
            _check_circle =
                ImageVector.Builder(
                    name = "check_circle",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(10.6f, 16.6f)
                            lineTo(17.65f, 9.55f)
                            lineToRelative(-1.4f, -1.4f)
                            lineTo(10.6f, 13.8f)
                            lineTo(7.75f, 10.95f)
                            lineToRelative(-1.4f, 1.4f)
                            lineTo(10.6f, 16.6f)
                            close()
                            moveTo(12f, 22f)
                            quadTo(9.93f, 22f, 8.1f, 21.21f)
                            quadTo(6.28f, 20.43f, 4.93f, 19.08f)
                            quadTo(3.58f, 17.73f, 2.79f, 15.9f)
                            reflectiveQuadTo(2f, 12f)
                            quadTo(2f, 9.92f, 2.79f, 8.1f)
                            quadTo(3.58f, 6.27f, 4.93f, 4.93f)
                            quadTo(6.28f, 3.57f, 8.1f, 2.79f)
                            quadTo(9.93f, 2f, 12f, 2f)
                            reflectiveQuadToRelative(3.9f, 0.79f)
                            reflectiveQuadToRelative(3.17f, 2.14f)
                            quadToRelative(1.35f, 1.35f, 2.14f, 3.17f)
                            quadTo(22f, 9.92f, 22f, 12f)
                            reflectiveQuadToRelative(-0.79f, 3.9f)
                            reflectiveQuadToRelative(-2.14f, 3.17f)
                            quadToRelative(-1.35f, 1.35f, -3.17f, 2.14f)
                            reflectiveQuadTo(12f, 22f)
                            close()
                            moveToRelative(0f, -2f)
                            quadToRelative(3.35f, 0f, 5.68f, -2.32f)
                            reflectiveQuadTo(20f, 12f)
                            reflectiveQuadTo(17.68f, 6.32f)
                            reflectiveQuadTo(12f, 4f)
                            reflectiveQuadTo(6.33f, 6.32f)
                            reflectiveQuadTo(4f, 12f)
                            reflectiveQuadToRelative(2.33f, 5.68f)
                            reflectiveQuadTo(12f, 20f)
                            close()
                            moveToRelative(0f, -8f)
                            close()
                        }
                    }
                    .build()
            return _check_circle!!
        }

    private var _check_circle: ImageVector? = null

    val QrCodeScanner: ImageVector
        get() {
            if (_qr_code_scanner != null) {
                return _qr_code_scanner!!
            }
            _qr_code_scanner =
                ImageVector.Builder(
                    name = "qr_code_scanner",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(2f, 7f)
                            verticalLineTo(2f)
                            horizontalLineTo(7f)
                            verticalLineTo(4f)
                            horizontalLineTo(4f)
                            verticalLineTo(7f)
                            horizontalLineTo(2f)
                            close()
                            moveTo(2f, 22f)
                            verticalLineTo(17f)
                            horizontalLineTo(4f)
                            verticalLineToRelative(3f)
                            horizontalLineTo(7f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(2f)
                            close()
                            moveToRelative(15f, 0f)
                            verticalLineTo(20f)
                            horizontalLineToRelative(3f)
                            verticalLineTo(17f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(5f)
                            horizontalLineTo(17f)
                            close()
                            moveTo(20f, 7f)
                            verticalLineTo(4f)
                            horizontalLineTo(17f)
                            verticalLineTo(2f)
                            horizontalLineToRelative(5f)
                            verticalLineTo(7f)
                            horizontalLineTo(20f)
                            close()
                            moveTo(17.5f, 17.5f)
                            horizontalLineTo(19f)
                            verticalLineTo(19f)
                            horizontalLineTo(17.5f)
                            verticalLineTo(17.5f)
                            close()
                            moveToRelative(0f, -3f)
                            horizontalLineTo(19f)
                            verticalLineTo(16f)
                            horizontalLineTo(17.5f)
                            verticalLineTo(14.5f)
                            close()
                            moveTo(16f, 16f)
                            horizontalLineToRelative(1.5f)
                            verticalLineToRelative(1.5f)
                            horizontalLineTo(16f)
                            verticalLineTo(16f)
                            close()
                            moveToRelative(-1.5f, 1.5f)
                            horizontalLineTo(16f)
                            verticalLineTo(19f)
                            horizontalLineTo(14.5f)
                            verticalLineTo(17.5f)
                            close()
                            moveTo(13f, 16f)
                            horizontalLineToRelative(1.5f)
                            verticalLineToRelative(1.5f)
                            horizontalLineTo(13f)
                            verticalLineTo(16f)
                            close()
                            moveToRelative(3f, -3f)
                            horizontalLineToRelative(1.5f)
                            verticalLineToRelative(1.5f)
                            horizontalLineTo(16f)
                            verticalLineTo(13f)
                            close()
                            moveToRelative(-1.5f, 1.5f)
                            horizontalLineTo(16f)
                            verticalLineTo(16f)
                            horizontalLineTo(14.5f)
                            verticalLineTo(14.5f)
                            close()
                            moveTo(13f, 13f)
                            horizontalLineToRelative(1.5f)
                            verticalLineToRelative(1.5f)
                            horizontalLineTo(13f)
                            verticalLineTo(13f)
                            close()
                            moveTo(19f, 5f)
                            verticalLineToRelative(6f)
                            horizontalLineTo(13f)
                            verticalLineTo(5f)
                            horizontalLineToRelative(6f)
                            close()
                            moveToRelative(-8f, 8f)
                            verticalLineToRelative(6f)
                            horizontalLineTo(5f)
                            verticalLineTo(13f)
                            horizontalLineToRelative(6f)
                            close()
                            moveTo(11f, 5f)
                            verticalLineToRelative(6f)
                            horizontalLineTo(5f)
                            verticalLineTo(5f)
                            horizontalLineToRelative(6f)
                            close()
                            moveTo(9.5f, 17.5f)
                            verticalLineToRelative(-3f)
                            horizontalLineToRelative(-3f)
                            verticalLineToRelative(3f)
                            horizontalLineToRelative(3f)
                            close()
                            moveToRelative(0f, -8f)
                            verticalLineToRelative(-3f)
                            horizontalLineToRelative(-3f)
                            verticalLineToRelative(3f)
                            horizontalLineToRelative(3f)
                            close()
                            moveToRelative(8f, 0f)
                            verticalLineToRelative(-3f)
                            horizontalLineToRelative(-3f)
                            verticalLineToRelative(3f)
                            horizontalLineToRelative(3f)
                            close()
                        }
                    }
                    .build()
            return _qr_code_scanner!!
        }

    private var _qr_code_scanner: ImageVector? = null
    val FindInPage: ImageVector
        get() {
            if (_find_in_page != null) {
                return _find_in_page!!
            }
            _find_in_page =
                ImageVector.Builder(
                    name = "find_in_page",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(14.75f, 20f)
                            lineToRelative(2f, 2f)
                            horizontalLineTo(6f)
                            quadTo(5.18f, 22f, 4.59f, 21.41f)
                            reflectiveQuadTo(4f, 20f)
                            verticalLineTo(4f)
                            quadTo(4f, 3.17f, 4.59f, 2.59f)
                            reflectiveQuadTo(6f, 2f)
                            horizontalLineToRelative(9f)
                            lineToRelative(5f, 6f)
                            verticalLineTo(20f)
                            quadToRelative(0f, 0.5f, -0.21f, 0.91f)
                            reflectiveQuadTo(19.2f, 21.6f)
                            lineTo(14f, 16.45f)
                            quadToRelative(-0.42f, 0.28f, -0.92f, 0.41f)
                            reflectiveQuadTo(12f, 17f)
                            quadTo(10.35f, 17f, 9.18f, 15.83f)
                            reflectiveQuadTo(8f, 13f)
                            reflectiveQuadTo(9.18f, 10.17f)
                            reflectiveQuadTo(12f, 9f)
                            reflectiveQuadToRelative(2.83f, 1.17f)
                            reflectiveQuadTo(16f, 13f)
                            quadToRelative(0f, 0.57f, -0.14f, 1.07f)
                            reflectiveQuadTo(15.45f, 15f)
                            lineTo(18f, 17.6f)
                            verticalLineTo(8.7f)
                            lineTo(14.05f, 4f)
                            horizontalLineTo(6f)
                            verticalLineTo(20f)
                            horizontalLineToRelative(8.75f)
                            close()
                            moveTo(13.41f, 14.41f)
                            quadTo(14f, 13.83f, 14f, 13f)
                            reflectiveQuadTo(13.41f, 11.59f)
                            reflectiveQuadTo(12f, 11f)
                            reflectiveQuadToRelative(-1.41f, 0.59f)
                            quadTo(10f, 12.18f, 10f, 13f)
                            reflectiveQuadToRelative(0.59f, 1.41f)
                            reflectiveQuadTo(12f, 15f)
                            reflectiveQuadToRelative(1.41f, -0.59f)
                            close()
                            moveTo(12f, 13f)
                            close()
                            moveToRelative(0f, 0f)
                            close()
                        }
                    }
                    .build()
            return _find_in_page!!
        }

    private var _find_in_page: ImageVector? = null

    val KeyboardArrowUp: ImageVector
        get() {
            if (_keyboard_arrow_up != null) {
                return _keyboard_arrow_up!!
            }
            _keyboard_arrow_up =
                ImageVector.Builder(
                    name = "keyboard_arrow_up",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(12f, 10.8f)
                            lineTo(7.4f, 15.4f)
                            lineTo(6f, 14f)
                            lineTo(12f, 8f)
                            lineToRelative(6f, 6f)
                            lineToRelative(-1.4f, 1.4f)
                            lineTo(12f, 10.8f)
                            close()
                        }
                    }
                    .build()
            return _keyboard_arrow_up!!
        }

    private var _keyboard_arrow_up: ImageVector? = null

    val KeyboardArrowDown: ImageVector
        get() {
            if (_keyboard_arrow_down != null) {
                return _keyboard_arrow_down!!
            }
            _keyboard_arrow_down =
                ImageVector.Builder(
                    name = "keyboard_arrow_down",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(12f, 15.4f)
                            lineTo(6f, 9.4f)
                            lineTo(7.4f, 8f)
                            lineTo(12f, 12.6f)
                            lineTo(16.6f, 8f)
                            lineTo(18f, 9.4f)
                            lineToRelative(-6f, 6f)
                            close()
                        }
                    }
                    .build()
            return _keyboard_arrow_down!!
        }

    private var _keyboard_arrow_down: ImageVector? = null

    val PlayArrow: ImageVector
        get() {
            if (_play_arrow != null) {
                return _play_arrow!!
            }
            _play_arrow =
                ImageVector.Builder(
                    name = "play_arrow",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(8f, 19f)
                            verticalLineTo(5f)
                            lineToRelative(11f, 7f)
                            lineTo(8f, 19f)
                            close()
                            moveToRelative(2f, -7f)
                            close()
                            moveToRelative(0f, 3.35f)
                            lineTo(15.25f, 12f)
                            lineTo(10f, 8.65f)
                            verticalLineToRelative(6.7f)
                            close()
                        }
                    }
                    .build()
            return _play_arrow!!
        }

    private var _play_arrow: ImageVector? = null

    val Error: ImageVector
        get() {
            if (_error != null) {
                return _error!!
            }
            _error =
                ImageVector.Builder(
                    name = "Error",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(12.71f, 16.71f)
                            quadTo(13f, 16.43f, 13f, 16f)
                            reflectiveQuadTo(12.71f, 15.29f)
                            reflectiveQuadTo(12f, 15f)
                            reflectiveQuadToRelative(-0.71f, 0.29f)
                            reflectiveQuadTo(11f, 16f)
                            reflectiveQuadToRelative(0.29f, 0.71f)
                            reflectiveQuadTo(12f, 17f)
                            reflectiveQuadToRelative(0.71f, -0.29f)
                            close()
                            moveTo(11f, 13f)
                            horizontalLineToRelative(2f)
                            verticalLineTo(7f)
                            horizontalLineTo(11f)
                            verticalLineToRelative(6f)
                            close()
                            moveToRelative(1f, 9f)
                            quadTo(9.93f, 22f, 8.1f, 21.21f)
                            quadTo(6.28f, 20.43f, 4.93f, 19.08f)
                            quadTo(3.58f, 17.73f, 2.79f, 15.9f)
                            reflectiveQuadTo(2f, 12f)
                            quadTo(2f, 9.92f, 2.79f, 8.1f)
                            quadTo(3.58f, 6.27f, 4.93f, 4.93f)
                            quadTo(6.28f, 3.57f, 8.1f, 2.79f)
                            quadTo(9.93f, 2f, 12f, 2f)
                            reflectiveQuadToRelative(3.9f, 0.79f)
                            reflectiveQuadToRelative(3.17f, 2.14f)
                            quadToRelative(1.35f, 1.35f, 2.14f, 3.17f)
                            quadTo(22f, 9.92f, 22f, 12f)
                            reflectiveQuadToRelative(-0.79f, 3.9f)
                            reflectiveQuadToRelative(-2.14f, 3.17f)
                            quadToRelative(-1.35f, 1.35f, -3.17f, 2.14f)
                            reflectiveQuadTo(12f, 22f)
                            close()
                            moveToRelative(0f, -2f)
                            quadToRelative(3.35f, 0f, 5.68f, -2.32f)
                            reflectiveQuadTo(20f, 12f)
                            reflectiveQuadTo(17.68f, 6.32f)
                            reflectiveQuadTo(12f, 4f)
                            reflectiveQuadTo(6.33f, 6.32f)
                            reflectiveQuadTo(4f, 12f)
                            reflectiveQuadToRelative(2.33f, 5.68f)
                            reflectiveQuadTo(12f, 20f)
                            close()
                            moveToRelative(0f, -8f)
                            close()
                        }
                    }
                    .build()
            return _error!!
        }

    private var _error: ImageVector? = null

    val Cancel: ImageVector
        get() {
            if (_cancel != null) {
                return _cancel!!
            }
            _cancel =
                ImageVector.Builder(
                    name = "Cancel",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(8.4f, 17f)
                            lineTo(12f, 13.4f)
                            lineTo(15.6f, 17f)
                            lineTo(17f, 15.6f)
                            lineTo(13.4f, 12f)
                            lineTo(17f, 8.4f)
                            lineTo(15.6f, 7f)
                            lineTo(12f, 10.6f)
                            lineTo(8.4f, 7f)
                            lineTo(7f, 8.4f)
                            lineTo(10.6f, 12f)
                            lineTo(7f, 15.6f)
                            lineTo(8.4f, 17f)
                            close()
                            moveTo(12f, 22f)
                            quadTo(9.93f, 22f, 8.1f, 21.21f)
                            quadTo(6.28f, 20.43f, 4.93f, 19.08f)
                            quadTo(3.58f, 17.73f, 2.79f, 15.9f)
                            reflectiveQuadTo(2f, 12f)
                            quadTo(2f, 9.92f, 2.79f, 8.1f)
                            quadTo(3.58f, 6.27f, 4.93f, 4.93f)
                            quadTo(6.28f, 3.57f, 8.1f, 2.79f)
                            quadTo(9.93f, 2f, 12f, 2f)
                            reflectiveQuadToRelative(3.9f, 0.79f)
                            reflectiveQuadToRelative(3.17f, 2.14f)
                            quadToRelative(1.35f, 1.35f, 2.14f, 3.17f)
                            quadTo(22f, 9.92f, 22f, 12f)
                            reflectiveQuadToRelative(-0.79f, 3.9f)
                            reflectiveQuadToRelative(-2.14f, 3.17f)
                            quadToRelative(-1.35f, 1.35f, -3.17f, 2.14f)
                            reflectiveQuadTo(12f, 22f)
                            close()
                            moveToRelative(0f, -2f)
                            quadToRelative(3.35f, 0f, 5.68f, -2.32f)
                            reflectiveQuadTo(20f, 12f)
                            reflectiveQuadTo(17.68f, 6.32f)
                            reflectiveQuadTo(12f, 4f)
                            reflectiveQuadTo(6.33f, 6.32f)
                            reflectiveQuadTo(4f, 12f)
                            reflectiveQuadToRelative(2.33f, 5.68f)
                            reflectiveQuadTo(12f, 20f)
                            close()
                            moveToRelative(0f, -8f)
                            close()
                        }
                    }
                    .build()
            return _cancel!!
        }

    private var _cancel: ImageVector? = null

    val Schedule: ImageVector
        get() {
            if (_schedule != null) {
                return _schedule!!
            }
            _schedule =
                ImageVector.Builder(
                    name = "Schedule",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(15.3f, 16.7f)
                            lineToRelative(1.4f, -1.4f)
                            lineTo(13f, 11.6f)
                            verticalLineTo(7f)
                            horizontalLineTo(11f)
                            verticalLineToRelative(5.4f)
                            lineToRelative(4.3f, 4.3f)
                            close()
                            moveTo(12f, 22f)
                            quadTo(9.93f, 22f, 8.1f, 21.21f)
                            quadTo(6.28f, 20.43f, 4.93f, 19.08f)
                            quadTo(3.58f, 17.73f, 2.79f, 15.9f)
                            reflectiveQuadTo(2f, 12f)
                            quadTo(2f, 9.92f, 2.79f, 8.1f)
                            quadTo(3.58f, 6.27f, 4.93f, 4.93f)
                            quadTo(6.28f, 3.57f, 8.1f, 2.79f)
                            quadTo(9.93f, 2f, 12f, 2f)
                            reflectiveQuadToRelative(3.9f, 0.79f)
                            reflectiveQuadToRelative(3.17f, 2.14f)
                            quadToRelative(1.35f, 1.35f, 2.14f, 3.17f)
                            quadTo(22f, 9.92f, 22f, 12f)
                            reflectiveQuadToRelative(-0.79f, 3.9f)
                            reflectiveQuadToRelative(-2.14f, 3.17f)
                            quadToRelative(-1.35f, 1.35f, -3.17f, 2.14f)
                            reflectiveQuadTo(12f, 22f)
                            close()
                            moveTo(12f, 12f)
                            close()
                            moveToRelative(0f, 8f)
                            quadToRelative(3.33f, 0f, 5.66f, -2.34f)
                            reflectiveQuadTo(20f, 12f)
                            quadTo(20f, 8.67f, 17.66f, 6.34f)
                            reflectiveQuadTo(12f, 4f)
                            quadTo(8.68f, 4f, 6.34f, 6.34f)
                            reflectiveQuadTo(4f, 12f)
                            reflectiveQuadToRelative(2.34f, 5.66f)
                            reflectiveQuadTo(12f, 20f)
                            close()
                        }
                    }
                    .build()
            return _schedule!!
        }

    private var _schedule: ImageVector? = null

    val ArrowDownward: ImageVector
        get() {
            if (_arrow_downward != null) {
                return _arrow_downward!!
            }
            _arrow_downward =
                ImageVector.Builder(
                    name = "arrow_downward",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(11f, 4f)
                            verticalLineTo(16.18f)
                            lineTo(5.4f, 10.58f)
                            lineTo(4f, 12f)
                            lineToRelative(8f, 8f)
                            lineToRelative(8f, -8f)
                            lineTo(18.6f, 10.58f)
                            lineTo(13f, 16.18f)
                            verticalLineTo(4f)
                            horizontalLineTo(11f)
                            close()
                        }
                    }
                    .build()
            return _arrow_downward!!
        }

    private var _arrow_downward: ImageVector? = null


    val ArrowUpward: ImageVector
        get() {
            if (_arrow_upward != null) {
                return _arrow_upward!!
            }
            _arrow_upward =
                ImageVector.Builder(
                    name = "arrow_upward",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(11f, 20f)
                            verticalLineTo(7.82f)
                            lineToRelative(-5.6f, 5.6f)
                            lineTo(4f, 12f)
                            lineTo(12f, 4f)
                            lineToRelative(8f, 8f)
                            lineToRelative(-1.4f, 1.42f)
                            lineTo(13f, 7.82f)
                            verticalLineTo(20f)
                            horizontalLineTo(11f)
                            close()
                        }
                    }
                    .build()
            return _arrow_upward!!
        }

    private var _arrow_upward: ImageVector? = null

    val MyLocation: ImageVector
        get() {
            if (_my_location != null) {
                return _my_location!!
            }
            _my_location =
                ImageVector.Builder(
                    name = "my_location",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(11f, 22.95f)
                            verticalLineToRelative(-2f)
                            quadTo(7.88f, 20.6f, 5.64f, 18.36f)
                            reflectiveQuadTo(3.05f, 13f)
                            horizontalLineToRelative(-2f)
                            verticalLineTo(11f)
                            horizontalLineToRelative(2f)
                            quadTo(3.4f, 7.88f, 5.64f, 5.64f)
                            reflectiveQuadTo(11f, 3.05f)
                            verticalLineToRelative(-2f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(2f)
                            quadToRelative(3.13f, 0.35f, 5.36f, 2.59f)
                            reflectiveQuadTo(20.95f, 11f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(2f)
                            horizontalLineToRelative(-2f)
                            quadToRelative(-0.35f, 3.13f, -2.59f, 5.36f)
                            reflectiveQuadTo(13f, 20.95f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(11f)
                            close()
                            moveToRelative(5.95f, -6f)
                            quadTo(19f, 14.9f, 19f, 12f)
                            reflectiveQuadTo(16.95f, 7.05f)
                            reflectiveQuadTo(12f, 5f)
                            reflectiveQuadTo(7.05f, 7.05f)
                            reflectiveQuadTo(5f, 12f)
                            reflectiveQuadToRelative(2.05f, 4.95f)
                            reflectiveQuadTo(12f, 19f)
                            reflectiveQuadToRelative(4.95f, -2.05f)
                            close()
                            moveTo(9.18f, 14.83f)
                            quadTo(8f, 13.65f, 8f, 12f)
                            reflectiveQuadTo(9.18f, 9.17f)
                            reflectiveQuadTo(12f, 8f)
                            reflectiveQuadToRelative(2.83f, 1.17f)
                            reflectiveQuadTo(16f, 12f)
                            reflectiveQuadToRelative(-1.17f, 2.82f)
                            reflectiveQuadTo(12f, 16f)
                            reflectiveQuadTo(9.18f, 14.83f)
                            close()
                            moveToRelative(4.24f, -1.41f)
                            quadTo(14f, 12.83f, 14f, 12f)
                            reflectiveQuadTo(13.41f, 10.59f)
                            reflectiveQuadTo(12f, 10f)
                            reflectiveQuadToRelative(-1.41f, 0.59f)
                            quadTo(10f, 11.18f, 10f, 12f)
                            reflectiveQuadToRelative(0.59f, 1.41f)
                            reflectiveQuadTo(12f, 14f)
                            reflectiveQuadToRelative(1.41f, -0.59f)
                            close()
                            moveTo(12f, 12f)
                            close()
                        }
                    }
                    .build()
            return _my_location!!
        }

    private var _my_location: ImageVector? = null

    val Upload: ImageVector
        get() {
            if (_upload != null) {
                return _upload!!
            }
            _upload =
                ImageVector.Builder(
                    name = "Upload",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(11f, 16f)
                            verticalLineTo(7.85f)
                            lineToRelative(-2.6f, 2.6f)
                            lineTo(7f, 9f)
                            lineTo(12f, 4f)
                            lineToRelative(5f, 5f)
                            lineToRelative(-1.4f, 1.45f)
                            lineTo(13f, 7.85f)
                            verticalLineTo(16f)
                            horizontalLineTo(11f)
                            close()
                            moveTo(6f, 20f)
                            quadTo(5.18f, 20f, 4.59f, 19.41f)
                            reflectiveQuadTo(4f, 18f)
                            verticalLineTo(15f)
                            horizontalLineTo(6f)
                            verticalLineToRelative(3f)
                            horizontalLineTo(18f)
                            verticalLineTo(15f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(3f)
                            quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                            reflectiveQuadTo(18f, 20f)
                            horizontalLineTo(6f)
                            close()
                        }
                    }
                    .build()
            return _upload!!
        }

    private var _upload: ImageVector? = null

    val Refresh: ImageVector
        get() {
            if (_refresh != null) {
                return _refresh!!
            }
            _refresh =
                ImageVector.Builder(
                    name = "Refresh",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(12f, 20f)
                            quadTo(8.65f, 20f, 6.33f, 17.68f)
                            reflectiveQuadTo(4f, 12f)
                            reflectiveQuadTo(6.33f, 6.32f)
                            reflectiveQuadTo(12f, 4f)
                            quadToRelative(1.73f, 0f, 3.3f, 0.71f)
                            quadTo(16.88f, 5.43f, 18f, 6.75f)
                            verticalLineTo(4f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(7f)
                            horizontalLineTo(13f)
                            verticalLineTo(9f)
                            horizontalLineToRelative(4.2f)
                            quadTo(16.4f, 7.6f, 15.01f, 6.8f)
                            reflectiveQuadTo(12f, 6f)
                            quadTo(9.5f, 6f, 7.75f, 7.75f)
                            reflectiveQuadTo(6f, 12f)
                            reflectiveQuadToRelative(1.75f, 4.25f)
                            reflectiveQuadTo(12f, 18f)
                            quadToRelative(1.93f, 0f, 3.48f, -1.1f)
                            reflectiveQuadTo(17.65f, 14f)
                            horizontalLineToRelative(2.1f)
                            quadToRelative(-0.7f, 2.65f, -2.85f, 4.32f)
                            reflectiveQuadTo(12f, 20f)
                            close()
                        }
                    }
                    .build()
            return _refresh!!
        }

    private var _refresh: ImageVector? = null

    val Files: ImageVector
        get() {
            if (_files != null) {
                return _files!!
            }
            _files =
                ImageVector.Builder(
                    name = "files",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(4f, 20f)
                            quadTo(3.18f, 20f, 2.59f, 19.41f)
                            reflectiveQuadTo(2f, 18f)
                            verticalLineTo(8f)
                            quadTo(2f, 7.18f, 2.59f, 6.59f)
                            reflectiveQuadTo(4f, 6f)
                            horizontalLineToRelative(6f)
                            lineTo(12f, 4f)
                            horizontalLineToRelative(8f)
                            quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                            quadTo(22f, 5.18f, 22f, 6f)
                            verticalLineTo(18f)
                            quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                            reflectiveQuadTo(20f, 20f)
                            horizontalLineTo(4f)
                            close()
                            moveTo(5.83f, 13f)
                            horizontalLineTo(11f)
                            verticalLineTo(7.82f)
                            lineTo(5.83f, 13f)
                            close()
                            moveTo(4f, 12f)
                            lineTo(8f, 8f)
                            horizontalLineTo(4f)
                            verticalLineToRelative(4f)
                            close()
                            moveToRelative(0f, 3f)
                            verticalLineToRelative(3f)
                            horizontalLineTo(20f)
                            verticalLineTo(6f)
                            horizontalLineTo(13f)
                            verticalLineToRelative(7f)
                            quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                            reflectiveQuadTo(11f, 15f)
                            horizontalLineTo(4f)
                            close()
                            moveToRelative(7f, -4f)
                            close()
                        }
                    }
                    .build()
            return _files!!
        }

    private var _files: ImageVector? = null

    val Download: ImageVector
        get() {
            if (_download != null) {
                return _download!!
            }
            _download =
                ImageVector.Builder(
                    name = "Download",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 24f,
                    viewportHeight = 24f,
                )
                    .apply {
                        path(
                            fill = SolidColor(Color.Black),
                            fillAlpha = 1f,
                            stroke = null,
                            strokeAlpha = 1f,
                            strokeLineWidth = 1f,
                            strokeLineCap = StrokeCap.Butt,
                            strokeLineJoin = StrokeJoin.Bevel,
                            strokeLineMiter = 1f,
                            pathFillType = PathFillType.Companion.NonZero,
                        ) {
                            moveTo(12f, 16f)
                            lineTo(7f, 11f)
                            lineTo(8.4f, 9.55f)
                            lineToRelative(2.6f, 2.6f)
                            verticalLineTo(4f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(8.15f)
                            lineToRelative(2.6f, -2.6f)
                            lineTo(17f, 11f)
                            lineToRelative(-5f, 5f)
                            close()
                            moveTo(6f, 20f)
                            quadTo(5.18f, 20f, 4.59f, 19.41f)
                            reflectiveQuadTo(4f, 18f)
                            verticalLineTo(15f)
                            horizontalLineTo(6f)
                            verticalLineToRelative(3f)
                            horizontalLineTo(18f)
                            verticalLineTo(15f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(3f)
                            quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                            reflectiveQuadTo(18f, 20f)
                            horizontalLineTo(6f)
                            close()
                        }
                    }
                    .build()
            return _download!!
        }

    private var _download: ImageVector? = null
}
