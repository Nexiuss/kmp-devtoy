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
            if (_arrow_back != null) {
                return _arrow_back!!
            }
            _arrow_back =
                ImageVector.Builder(
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
                            moveTo(7.83f, 13f)
                            lineToRelative(5.6f, 5.6f)
                            lineTo(12f, 20f)
                            lineTo(4f, 12f)
                            lineTo(12f, 4f)
                            lineToRelative(1.43f, 1.4f)
                            lineTo(7.83f, 11f)
                            horizontalLineTo(20f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(7.83f)
                            close()
                        }
                    }
                    .build()
            return _arrow_back!!
        }

    private var _arrow_back: ImageVector? = null


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
            if (_build != null) {
                return _build!!
            }
            _build =
                ImageVector.Builder(
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
                            moveTo(17.15f, 20.7f)
                            lineTo(11.1f, 14.6f)
                            quadToRelative(-0.5f, 0.2f, -1.01f, 0.3f)
                            quadTo(9.58f, 15f, 9f, 15f)
                            quadTo(6.5f, 15f, 4.75f, 13.25f)
                            reflectiveQuadTo(3f, 9f)
                            quadTo(3f, 8.1f, 3.25f, 7.29f)
                            reflectiveQuadTo(3.95f, 5.75f)
                            lineTo(7.6f, 9.4f)
                            lineTo(9.4f, 7.6f)
                            lineTo(5.75f, 3.95f)
                            quadTo(6.48f, 3.5f, 7.29f, 3.25f)
                            reflectiveQuadTo(9f, 3f)
                            quadToRelative(2.5f, 0f, 4.25f, 1.75f)
                            reflectiveQuadTo(15f, 9f)
                            quadToRelative(0f, 0.57f, -0.1f, 1.09f)
                            reflectiveQuadTo(14.6f, 11.1f)
                            lineToRelative(6.1f, 6.05f)
                            quadToRelative(0.3f, 0.3f, 0.3f, 0.73f)
                            reflectiveQuadTo(20.7f, 18.6f)
                            lineToRelative(-2.1f, 2.1f)
                            quadTo(18.3f, 21f, 17.88f, 21f)
                            reflectiveQuadTo(17.15f, 20.7f)
                            close()
                            moveToRelative(0.72f, -2.13f)
                            lineTo(18.55f, 17.9f)
                            lineToRelative(-6.4f, -6.4f)
                            quadTo(12.6f, 11f, 12.8f, 10.34f)
                            reflectiveQuadTo(13f, 9f)
                            quadTo(13f, 7.5f, 12.04f, 6.39f)
                            reflectiveQuadTo(9.65f, 5.05f)
                            lineTo(11.5f, 6.9f)
                            quadToRelative(0.3f, 0.3f, 0.3f, 0.7f)
                            reflectiveQuadTo(11.5f, 8.3f)
                            lineTo(8.3f, 11.5f)
                            quadTo(8f, 11.8f, 7.6f, 11.8f)
                            reflectiveQuadTo(6.9f, 11.5f)
                            lineTo(5.05f, 9.65f)
                            quadToRelative(0.22f, 1.43f, 1.34f, 2.39f)
                            reflectiveQuadTo(9f, 13f)
                            quadToRelative(0.65f, 0f, 1.3f, -0.2f)
                            reflectiveQuadToRelative(1.18f, -0.63f)
                            lineToRelative(6.4f, 6.4f)
                            close()
                            moveTo(11.8f, 11.8f)
                            close()
                        }
                    }
                    .build()
            return _build!!
        }

    private var _build: ImageVector? = null


    val Calculate: ImageVector
        get() {
            if (_calculate != null) {
                return _calculate!!
            }
            _calculate =
                ImageVector.Builder(
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
                            moveTo(8f, 18f)
                            horizontalLineTo(9.5f)
                            verticalLineTo(16f)
                            horizontalLineToRelative(2f)
                            verticalLineTo(14.5f)
                            horizontalLineToRelative(-2f)
                            verticalLineToRelative(-2f)
                            horizontalLineTo(8f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(6f)
                            verticalLineTo(16f)
                            horizontalLineTo(8f)
                            verticalLineToRelative(2f)
                            close()
                            moveToRelative(5f, -0.75f)
                            horizontalLineToRelative(5f)
                            verticalLineToRelative(-1.5f)
                            horizontalLineTo(13f)
                            verticalLineToRelative(1.5f)
                            close()
                            moveToRelative(0f, -2.5f)
                            horizontalLineToRelative(5f)
                            verticalLineToRelative(-1.5f)
                            horizontalLineTo(13f)
                            verticalLineToRelative(1.5f)
                            close()
                            moveToRelative(1.1f, -3.8f)
                            lineToRelative(1.4f, -1.4f)
                            lineToRelative(1.4f, 1.4f)
                            lineTo(17.95f, 9.9f)
                            lineTo(16.55f, 8.45f)
                            lineToRelative(1.4f, -1.4f)
                            lineTo(16.9f, 6f)
                            lineTo(15.5f, 7.4f)
                            lineTo(14.1f, 6f)
                            lineTo(13.05f, 7.05f)
                            lineToRelative(1.4f, 1.4f)
                            lineTo(13.05f, 9.9f)
                            lineToRelative(1.05f, 1.05f)
                            close()
                            moveTo(6.25f, 9.2f)
                            horizontalLineToRelative(5f)
                            verticalLineTo(7.7f)
                            horizontalLineToRelative(-5f)
                            verticalLineTo(9.2f)
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
                            moveTo(5f, 5f)
                            verticalLineTo(19f)
                            verticalLineTo(5f)
                            close()
                        }
                    }
                    .build()
            return _calculate!!
        }

    private var _calculate: ImageVector? = null


    val Check: ImageVector
        get() {
            if (_check != null) {
                return _check!!
            }
            _check =
                ImageVector.Builder(
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
                            moveTo(9.55f, 18f)
                            lineTo(3.85f, 12.3f)
                            lineTo(5.28f, 10.88f)
                            lineToRelative(4.28f, 4.28f)
                            lineTo(18.73f, 5.97f)
                            lineTo(20.15f, 7.4f)
                            lineTo(9.55f, 18f)
                            close()
                        }
                    }
                    .build()
            return _check!!
        }

    private var _check: ImageVector? = null


    val Clear: ImageVector
        get() {
            if (_cleaning_services != null) {
                return _cleaning_services!!
            }
            _cleaning_services =
                ImageVector.Builder(
                    name = "cleaning_services",
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
                            moveTo(3f, 23f)
                            verticalLineTo(16f)
                            quadTo(3f, 13.93f, 4.46f, 12.46f)
                            reflectiveQuadTo(8f, 11f)
                            horizontalLineTo(9f)
                            verticalLineTo(3f)
                            quadTo(9f, 2.17f, 9.59f, 1.59f)
                            reflectiveQuadTo(11f, 1f)
                            horizontalLineToRelative(2f)
                            quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                            reflectiveQuadTo(15f, 3f)
                            verticalLineToRelative(8f)
                            horizontalLineToRelative(1f)
                            quadToRelative(2.07f, 0f, 3.54f, 1.46f)
                            quadTo(21f, 13.93f, 21f, 16f)
                            verticalLineToRelative(7f)
                            horizontalLineTo(3f)
                            close()
                            moveTo(5f, 21f)
                            horizontalLineTo(7f)
                            verticalLineTo(18f)
                            quadTo(7f, 17.58f, 7.29f, 17.29f)
                            reflectiveQuadTo(8f, 17f)
                            reflectiveQuadToRelative(0.71f, 0.29f)
                            reflectiveQuadTo(9f, 18f)
                            verticalLineToRelative(3f)
                            horizontalLineToRelative(2f)
                            verticalLineTo(18f)
                            quadToRelative(0f, -0.43f, 0.29f, -0.71f)
                            reflectiveQuadTo(12f, 17f)
                            reflectiveQuadToRelative(0.71f, 0.29f)
                            reflectiveQuadTo(13f, 18f)
                            verticalLineToRelative(3f)
                            horizontalLineToRelative(2f)
                            verticalLineTo(18f)
                            quadToRelative(0f, -0.43f, 0.29f, -0.71f)
                            reflectiveQuadTo(16f, 17f)
                            quadToRelative(0.43f, 0f, 0.71f, 0.29f)
                            reflectiveQuadTo(17f, 18f)
                            verticalLineToRelative(3f)
                            horizontalLineToRelative(2f)
                            verticalLineTo(16f)
                            quadToRelative(0f, -1.25f, -0.88f, -2.13f)
                            reflectiveQuadTo(16f, 13f)
                            horizontalLineTo(8f)
                            quadTo(6.75f, 13f, 5.88f, 13.88f)
                            reflectiveQuadTo(5f, 16f)
                            verticalLineToRelative(5f)
                            close()
                            moveTo(13f, 11f)
                            verticalLineTo(3f)
                            horizontalLineTo(11f)
                            verticalLineToRelative(8f)
                            horizontalLineToRelative(2f)
                            close()
                            moveToRelative(0f, 0f)
                            horizontalLineTo(11f)
                            horizontalLineToRelative(2f)
                            close()
                        }
                    }
                    .build()
            return _cleaning_services!!
        }

    private var _cleaning_services: ImageVector? = null


    val ClearAll: ImageVector
        get() {
            if (_clear_all != null) {
                return _clear_all!!
            }
            _clear_all =
                ImageVector.Builder(
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
                            moveTo(3f, 17f)
                            verticalLineTo(15f)
                            horizontalLineTo(17f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(3f)
                            close()
                            moveTo(5f, 13f)
                            verticalLineTo(11f)
                            horizontalLineTo(19f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(5f)
                            close()
                            moveTo(7f, 9f)
                            verticalLineTo(7f)
                            horizontalLineTo(21f)
                            verticalLineTo(9f)
                            horizontalLineTo(7f)
                            close()
                        }
                    }
                    .build()
            return _clear_all!!
        }

    private var _clear_all: ImageVector? = null

    val SettingsBackupRestore: ImageVector
        get() {
            if (_settings_backup_restore != null) {
                return _settings_backup_restore!!
            }
            _settings_backup_restore =
                ImageVector.Builder(
                    name = "settings_backup_restore",
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
                            moveTo(12f, 14f)
                            quadToRelative(-0.82f, 0f, -1.41f, -0.59f)
                            reflectiveQuadTo(10f, 12f)
                            reflectiveQuadToRelative(0.59f, -1.41f)
                            reflectiveQuadTo(12f, 10f)
                            reflectiveQuadToRelative(1.41f, 0.59f)
                            quadTo(14f, 11.18f, 14f, 12f)
                            reflectiveQuadToRelative(-0.59f, 1.41f)
                            reflectiveQuadTo(12f, 14f)
                            close()
                            moveToRelative(0f, 7f)
                            quadTo(8.53f, 21f, 5.98f, 18.71f)
                            quadTo(3.43f, 16.43f, 3.05f, 13f)
                            horizontalLineTo(5.1f)
                            quadToRelative(0.35f, 2.6f, 2.31f, 4.3f)
                            reflectiveQuadTo(12f, 19f)
                            quadToRelative(2.93f, 0f, 4.96f, -2.04f)
                            quadTo(19f, 14.93f, 19f, 12f)
                            quadTo(19f, 9.07f, 16.96f, 7.04f)
                            reflectiveQuadTo(12f, 5f)
                            quadTo(10.28f, 5f, 8.78f, 5.8f)
                            reflectiveQuadTo(6.25f, 8f)
                            horizontalLineTo(9f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(3f)
                            verticalLineTo(4f)
                            horizontalLineTo(5f)
                            verticalLineTo(6.35f)
                            quadTo(6.28f, 4.75f, 8.11f, 3.88f)
                            reflectiveQuadTo(12f, 3f)
                            quadToRelative(1.88f, 0f, 3.51f, 0.71f)
                            reflectiveQuadToRelative(2.85f, 1.93f)
                            reflectiveQuadToRelative(1.93f, 2.85f)
                            reflectiveQuadTo(21f, 12f)
                            reflectiveQuadToRelative(-0.71f, 3.51f)
                            reflectiveQuadToRelative(-1.93f, 2.85f)
                            reflectiveQuadToRelative(-2.85f, 1.93f)
                            reflectiveQuadTo(12f, 21f)
                            close()
                        }
                    }
                    .build()
            return _settings_backup_restore!!
        }

    private var _settings_backup_restore: ImageVector? = null


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
            if (_content_copy != null) {
                return _content_copy!!
            }
            _content_copy =
                ImageVector.Builder(
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
                            moveTo(9f, 18f)
                            quadTo(8.18f, 18f, 7.59f, 17.41f)
                            reflectiveQuadTo(7f, 16f)
                            verticalLineTo(4f)
                            quadTo(7f, 3.17f, 7.59f, 2.59f)
                            reflectiveQuadTo(9f, 2f)
                            horizontalLineToRelative(9f)
                            quadToRelative(0.82f, 0f, 1.41f, 0.59f)
                            reflectiveQuadTo(20f, 4f)
                            verticalLineTo(16f)
                            quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                            reflectiveQuadTo(18f, 18f)
                            horizontalLineTo(9f)
                            close()
                            moveTo(9f, 16f)
                            horizontalLineToRelative(9f)
                            verticalLineTo(4f)
                            horizontalLineTo(9f)
                            verticalLineTo(16f)
                            close()
                            moveTo(5f, 22f)
                            quadTo(4.18f, 22f, 3.59f, 21.41f)
                            reflectiveQuadTo(3f, 20f)
                            verticalLineTo(6f)
                            horizontalLineTo(5f)
                            verticalLineTo(20f)
                            horizontalLineTo(16f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(5f)
                            close()
                            moveTo(9f, 16f)
                            verticalLineTo(4f)
                            verticalLineTo(16f)
                            close()
                        }
                    }
                    .build()
            return _content_copy!!
        }

    private var _content_copy: ImageVector? = null


    val DataArray: ImageVector
        get() {
            if (_data_array != null) {
                return _data_array!!
            }
            _data_array =
                ImageVector.Builder(
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
                            moveTo(15f, 20f)
                            verticalLineTo(18f)
                            horizontalLineToRelative(3f)
                            verticalLineTo(6f)
                            horizontalLineTo(15f)
                            verticalLineTo(4f)
                            horizontalLineToRelative(5f)
                            verticalLineTo(20f)
                            horizontalLineTo(15f)
                            close()
                            moveTo(4f, 20f)
                            verticalLineTo(4f)
                            horizontalLineTo(9f)
                            verticalLineTo(6f)
                            horizontalLineTo(6f)
                            verticalLineTo(18f)
                            horizontalLineTo(9f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(4f)
                            close()
                        }
                    }
                    .build()
            return _data_array!!
        }

    private var _data_array: ImageVector? = null


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
            if (_drive_folder_upload != null) {
                return _drive_folder_upload!!
            }
            _drive_folder_upload =
                ImageVector.Builder(
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
                            moveTo(11f, 17f)
                            horizontalLineToRelative(2f)
                            verticalLineTo(12.8f)
                            lineToRelative(1.6f, 1.6f)
                            lineTo(16f, 13f)
                            lineTo(12f, 9f)
                            lineTo(8f, 13f)
                            lineToRelative(1.4f, 1.4f)
                            lineTo(11f, 12.8f)
                            verticalLineTo(17f)
                            close()
                            moveTo(4f, 20f)
                            quadTo(3.18f, 20f, 2.59f, 19.41f)
                            reflectiveQuadTo(2f, 18f)
                            verticalLineTo(6f)
                            quadTo(2f, 5.18f, 2.59f, 4.59f)
                            reflectiveQuadTo(4f, 4f)
                            horizontalLineToRelative(6f)
                            lineToRelative(2f, 2f)
                            horizontalLineToRelative(8f)
                            quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                            quadTo(22f, 7.18f, 22f, 8f)
                            verticalLineTo(18f)
                            quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                            reflectiveQuadTo(20f, 20f)
                            horizontalLineTo(4f)
                            close()
                            moveTo(4f, 18f)
                            horizontalLineTo(20f)
                            verticalLineTo(8f)
                            horizontalLineTo(11.18f)
                            lineToRelative(-2f, -2f)
                            horizontalLineTo(4f)
                            verticalLineTo(18f)
                            close()
                            moveToRelative(0f, 0f)
                            verticalLineTo(6f)
                            verticalLineTo(8f)
                            verticalLineTo(18f)
                            close()
                        }
                    }
                    .build()
            return _drive_folder_upload!!
        }

    private var _drive_folder_upload: ImageVector? = null


    val FilePresent: ImageVector
        get() {
            if (_file_present != null) {
                return _file_present!!
            }
            _file_present =
                ImageVector.Builder(
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
                            moveTo(6f, 22f)
                            quadTo(5.18f, 22f, 4.59f, 21.41f)
                            reflectiveQuadTo(4f, 20f)
                            verticalLineTo(4f)
                            quadTo(4f, 3.17f, 4.59f, 2.59f)
                            reflectiveQuadTo(6f, 2f)
                            horizontalLineToRelative(9f)
                            lineToRelative(5f, 5f)
                            verticalLineTo(20f)
                            quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                            reflectiveQuadTo(18f, 22f)
                            horizontalLineTo(6f)
                            close()
                            moveTo(6f, 20f)
                            horizontalLineTo(18f)
                            verticalLineTo(8f)
                            horizontalLineTo(14f)
                            verticalLineTo(4f)
                            horizontalLineTo(6f)
                            verticalLineTo(20f)
                            close()
                            moveToRelative(6f, -1f)
                            quadToRelative(1.68f, 0f, 2.84f, -1.18f)
                            reflectiveQuadTo(16f, 15f)
                            verticalLineTo(11f)
                            horizontalLineTo(14f)
                            verticalLineToRelative(4f)
                            quadToRelative(0f, 0.82f, -0.57f, 1.41f)
                            reflectiveQuadTo(12f, 17f)
                            quadToRelative(-0.82f, 0f, -1.41f, -0.59f)
                            reflectiveQuadTo(10f, 15f)
                            verticalLineTo(9.5f)
                            quadTo(10f, 9.27f, 10.15f, 9.14f)
                            reflectiveQuadTo(10.5f, 9f)
                            quadToRelative(0.23f, 0f, 0.36f, 0.14f)
                            reflectiveQuadTo(11f, 9.5f)
                            verticalLineTo(15f)
                            horizontalLineToRelative(2f)
                            verticalLineTo(9.5f)
                            quadTo(13f, 8.45f, 12.28f, 7.72f)
                            reflectiveQuadTo(10.5f, 7f)
                            reflectiveQuadTo(8.73f, 7.72f)
                            reflectiveQuadTo(8f, 9.5f)
                            verticalLineTo(15f)
                            quadToRelative(0f, 1.65f, 1.18f, 2.82f)
                            reflectiveQuadTo(12f, 19f)
                            close()
                            moveTo(6f, 4f)
                            verticalLineTo(8f)
                            verticalLineTo(4f)
                            verticalLineTo(8f)
                            verticalLineTo(20f)
                            verticalLineTo(4f)
                            close()
                        }
                    }
                    .build()
            return _file_present!!
        }

    private var _file_present: ImageVector? = null


    val Folder: ImageVector
        get() {
            if (_folder != null) {
                return _folder!!
            }
            _folder =
                ImageVector.Builder(
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
                            moveTo(4f, 20f)
                            quadTo(3.18f, 20f, 2.59f, 19.41f)
                            reflectiveQuadTo(2f, 18f)
                            verticalLineTo(6f)
                            quadTo(2f, 5.18f, 2.59f, 4.59f)
                            reflectiveQuadTo(4f, 4f)
                            horizontalLineToRelative(6f)
                            lineToRelative(2f, 2f)
                            horizontalLineToRelative(8f)
                            quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                            quadTo(22f, 7.18f, 22f, 8f)
                            verticalLineTo(18f)
                            quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                            reflectiveQuadTo(20f, 20f)
                            horizontalLineTo(4f)
                            close()
                            moveTo(4f, 18f)
                            horizontalLineTo(20f)
                            verticalLineTo(8f)
                            horizontalLineTo(11.18f)
                            lineToRelative(-2f, -2f)
                            horizontalLineTo(4f)
                            verticalLineTo(18f)
                            close()
                            moveToRelative(0f, 0f)
                            verticalLineTo(6f)
                            verticalLineTo(8f)
                            verticalLineTo(18f)
                            close()
                        }
                    }
                    .build()
            return _folder!!
        }

    private var _folder: ImageVector? = null


    val FolderOpen: ImageVector
        get() {
            if (_folder_open != null) {
                return _folder_open!!
            }
            _folder_open =
                ImageVector.Builder(
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
                            moveTo(4f, 20f)
                            quadTo(3.18f, 20f, 2.59f, 19.41f)
                            reflectiveQuadTo(2f, 18f)
                            verticalLineTo(6f)
                            quadTo(2f, 5.18f, 2.59f, 4.59f)
                            reflectiveQuadTo(4f, 4f)
                            horizontalLineToRelative(6f)
                            lineToRelative(2f, 2f)
                            horizontalLineToRelative(8f)
                            quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                            quadTo(22f, 7.18f, 22f, 8f)
                            horizontalLineTo(11.18f)
                            lineToRelative(-2f, -2f)
                            horizontalLineTo(4f)
                            verticalLineTo(18f)
                            lineTo(6.4f, 10f)
                            horizontalLineTo(23.5f)
                            lineToRelative(-2.57f, 8.57f)
                            quadToRelative(-0.2f, 0.65f, -0.74f, 1.04f)
                            reflectiveQuadTo(19f, 20f)
                            horizontalLineTo(4f)
                            close()
                            moveTo(6.1f, 18f)
                            horizontalLineTo(19f)
                            lineToRelative(1.8f, -6f)
                            horizontalLineTo(7.9f)
                            lineTo(6.1f, 18f)
                            close()
                            moveToRelative(0f, 0f)
                            lineTo(7.9f, 12f)
                            lineTo(6.1f, 18f)
                            close()
                            moveTo(4f, 8f)
                            verticalLineTo(6f)
                            verticalLineTo(8f)
                            close()
                        }
                    }
                    .build()
            return _folder_open!!
        }

    private var _folder_open: ImageVector? = null


    val FormatPaint: ImageVector
        get() {
            if (_format_paint != null) {
                return _format_paint!!
            }
            _format_paint =
                ImageVector.Builder(
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
                            moveTo(11f, 22f)
                            quadTo(10.18f, 22f, 9.59f, 21.41f)
                            reflectiveQuadTo(9f, 20f)
                            verticalLineTo(16f)
                            horizontalLineTo(6f)
                            quadTo(5.18f, 16f, 4.59f, 15.41f)
                            reflectiveQuadTo(4f, 14f)
                            verticalLineTo(7f)
                            quadTo(4f, 5.35f, 5.18f, 4.17f)
                            reflectiveQuadTo(8f, 3f)
                            horizontalLineTo(20f)
                            verticalLineTo(14f)
                            quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                            reflectiveQuadTo(18f, 16f)
                            horizontalLineTo(15f)
                            verticalLineToRelative(4f)
                            quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                            reflectiveQuadTo(13f, 22f)
                            horizontalLineTo(11f)
                            close()
                            moveTo(6f, 10f)
                            horizontalLineTo(18f)
                            verticalLineTo(5f)
                            horizontalLineTo(17f)
                            verticalLineTo(9f)
                            horizontalLineTo(15f)
                            verticalLineTo(5f)
                            horizontalLineTo(14f)
                            verticalLineTo(7f)
                            horizontalLineTo(12f)
                            verticalLineTo(5f)
                            horizontalLineTo(8f)
                            quadTo(7.18f, 5f, 6.59f, 5.59f)
                            quadTo(6f, 6.18f, 6f, 7f)
                            verticalLineToRelative(3f)
                            close()
                            moveToRelative(0f, 4f)
                            horizontalLineTo(18f)
                            verticalLineTo(12f)
                            horizontalLineTo(6f)
                            verticalLineToRelative(2f)
                            close()
                            moveToRelative(0f, 0f)
                            verticalLineTo(12f)
                            verticalLineToRelative(2f)
                            close()
                        }
                    }
                    .build()
            return _format_paint!!
        }

    private var _format_paint: ImageVector? = null


    val GppGood: ImageVector
        get() {
            if (_verified_user != null) {
                return _verified_user!!
            }
            _verified_user =
                ImageVector.Builder(
                    name = "verified_user",
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
                            moveTo(10.95f, 15.55f)
                            lineTo(16.6f, 9.9f)
                            lineTo(15.18f, 8.48f)
                            lineTo(10.95f, 12.7f)
                            lineTo(8.85f, 10.6f)
                            lineTo(7.43f, 12.02f)
                            lineToRelative(3.53f, 3.53f)
                            close()
                            moveTo(12f, 22f)
                            quadTo(8.53f, 21.13f, 6.26f, 18.01f)
                            reflectiveQuadTo(4f, 11.1f)
                            verticalLineTo(5f)
                            lineTo(12f, 2f)
                            lineToRelative(8f, 3f)
                            verticalLineToRelative(6.1f)
                            quadToRelative(0f, 3.8f, -2.26f, 6.91f)
                            reflectiveQuadTo(12f, 22f)
                            close()
                            moveToRelative(0f, -2.1f)
                            quadToRelative(2.6f, -0.82f, 4.3f, -3.3f)
                            reflectiveQuadTo(18f, 11.1f)
                            verticalLineTo(6.38f)
                            lineTo(12f, 4.13f)
                            lineTo(6f, 6.38f)
                            verticalLineTo(11.1f)
                            quadToRelative(0f, 3.03f, 1.7f, 5.5f)
                            reflectiveQuadTo(12f, 19.9f)
                            close()
                            moveTo(12f, 12f)
                            close()
                        }
                    }
                    .build()
            return _verified_user!!
        }

    private var _verified_user: ImageVector? = null


    val Home: ImageVector
        get() {
            if (_home != null) {
                return _home!!
            }
            _home =
                ImageVector.Builder(
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
                            moveTo(6f, 19f)
                            horizontalLineTo(9f)
                            verticalLineTo(13f)
                            horizontalLineToRelative(6f)
                            verticalLineToRelative(6f)
                            horizontalLineToRelative(3f)
                            verticalLineTo(10f)
                            lineTo(12f, 5.5f)
                            lineTo(6f, 10f)
                            verticalLineToRelative(9f)
                            close()
                            moveTo(4f, 21f)
                            verticalLineTo(9f)
                            lineTo(12f, 3f)
                            lineToRelative(8f, 6f)
                            verticalLineTo(21f)
                            horizontalLineTo(13f)
                            verticalLineTo(15f)
                            horizontalLineTo(11f)
                            verticalLineToRelative(6f)
                            horizontalLineTo(4f)
                            close()
                            moveToRelative(8f, -8.75f)
                            close()
                        }
                    }
                    .build()
            return _home!!
        }

    private var _home: ImageVector? = null


    val Html: ImageVector
        get() {
            if (_html != null) {
                return _html!!
            }
            _html =
                ImageVector.Builder(
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
                            moveTo(0f, 15f)
                            verticalLineTo(9f)
                            horizontalLineTo(1.5f)
                            verticalLineToRelative(2f)
                            horizontalLineToRelative(2f)
                            verticalLineTo(9f)
                            horizontalLineTo(5f)
                            verticalLineToRelative(6f)
                            horizontalLineTo(3.5f)
                            verticalLineTo(12.5f)
                            horizontalLineToRelative(-2f)
                            verticalLineTo(15f)
                            horizontalLineTo(0f)
                            close()
                            moveToRelative(7.75f, 0f)
                            verticalLineTo(10.5f)
                            horizontalLineTo(6f)
                            verticalLineTo(9f)
                            horizontalLineToRelative(5f)
                            verticalLineToRelative(1.5f)
                            horizontalLineTo(9.25f)
                            verticalLineTo(15f)
                            horizontalLineTo(7.75f)
                            close()
                            moveTo(12f, 15f)
                            verticalLineTo(10f)
                            quadTo(12f, 9.57f, 12.29f, 9.29f)
                            reflectiveQuadTo(13f, 9f)
                            horizontalLineToRelative(4.5f)
                            quadToRelative(0.43f, 0f, 0.71f, 0.29f)
                            reflectiveQuadTo(18.5f, 10f)
                            verticalLineToRelative(5f)
                            horizontalLineTo(17f)
                            verticalLineTo(10.5f)
                            horizontalLineTo(16f)
                            verticalLineTo(14f)
                            horizontalLineTo(14.5f)
                            verticalLineTo(10.5f)
                            horizontalLineToRelative(-1f)
                            verticalLineTo(15f)
                            horizontalLineTo(12f)
                            close()
                            moveToRelative(8f, 0f)
                            verticalLineTo(9f)
                            horizontalLineToRelative(1.5f)
                            verticalLineToRelative(4.5f)
                            horizontalLineTo(24f)
                            verticalLineTo(15f)
                            horizontalLineTo(20f)
                            close()
                        }
                    }
                    .build()
            return _html!!
        }

    private var _html: ImageVector? = null


    val Http: ImageVector
        get() {
            if (_http != null) {
                return _http!!
            }
            _http =
                ImageVector.Builder(
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
                            moveTo(1f, 15f)
                            verticalLineTo(9f)
                            horizontalLineTo(2.5f)
                            verticalLineToRelative(2f)
                            horizontalLineToRelative(2f)
                            verticalLineTo(9f)
                            horizontalLineTo(6f)
                            verticalLineToRelative(6f)
                            horizontalLineTo(4.5f)
                            verticalLineTo(12.5f)
                            horizontalLineToRelative(-2f)
                            verticalLineTo(15f)
                            horizontalLineTo(1f)
                            close()
                            moveToRelative(7.5f, 0f)
                            verticalLineTo(10.5f)
                            horizontalLineTo(7f)
                            verticalLineTo(9f)
                            horizontalLineToRelative(4.5f)
                            verticalLineToRelative(1.5f)
                            horizontalLineTo(10f)
                            verticalLineTo(15f)
                            horizontalLineTo(8.5f)
                            close()
                            moveTo(14f, 15f)
                            verticalLineTo(10.5f)
                            horizontalLineTo(12.5f)
                            verticalLineTo(9f)
                            horizontalLineTo(17f)
                            verticalLineToRelative(1.5f)
                            horizontalLineTo(15.5f)
                            verticalLineTo(15f)
                            horizontalLineTo(14f)
                            close()
                            moveToRelative(4f, 0f)
                            verticalLineTo(9f)
                            horizontalLineToRelative(3.5f)
                            quadToRelative(0.6f, 0f, 1.05f, 0.45f)
                            reflectiveQuadTo(23f, 10.5f)
                            verticalLineToRelative(1f)
                            quadToRelative(0f, 0.6f, -0.45f, 1.05f)
                            reflectiveQuadTo(21.5f, 13f)
                            horizontalLineToRelative(-2f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(18f)
                            close()
                            moveToRelative(1.5f, -3.5f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(-1f)
                            horizontalLineToRelative(-2f)
                            verticalLineToRelative(1f)
                            close()
                        }
                    }
                    .build()
            return _http!!
        }

    private var _http: ImageVector? = null


    val Link: ImageVector
        get() {
            if (_link != null) {
                return _link!!
            }
            _link =
                ImageVector.Builder(
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
                            moveTo(11f, 17f)
                            horizontalLineTo(7f)
                            quadTo(4.93f, 17f, 3.46f, 15.54f)
                            reflectiveQuadTo(2f, 12f)
                            quadTo(2f, 9.92f, 3.46f, 8.46f)
                            reflectiveQuadTo(7f, 7f)
                            horizontalLineToRelative(4f)
                            verticalLineTo(9f)
                            horizontalLineTo(7f)
                            quadTo(5.75f, 9f, 4.88f, 9.88f)
                            reflectiveQuadTo(4f, 12f)
                            reflectiveQuadToRelative(0.88f, 2.13f)
                            reflectiveQuadTo(7f, 15f)
                            horizontalLineToRelative(4f)
                            verticalLineToRelative(2f)
                            close()
                            moveTo(8f, 13f)
                            verticalLineTo(11f)
                            horizontalLineToRelative(8f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(8f)
                            close()
                            moveToRelative(5f, 4f)
                            verticalLineTo(15f)
                            horizontalLineToRelative(4f)
                            quadToRelative(1.25f, 0f, 2.13f, -0.88f)
                            reflectiveQuadTo(20f, 12f)
                            reflectiveQuadTo(19.13f, 9.88f)
                            reflectiveQuadTo(17f, 9f)
                            horizontalLineTo(13f)
                            verticalLineTo(7f)
                            horizontalLineToRelative(4f)
                            quadToRelative(2.07f, 0f, 3.54f, 1.46f)
                            reflectiveQuadTo(22f, 12f)
                            reflectiveQuadToRelative(-1.46f, 3.54f)
                            reflectiveQuadTo(17f, 17f)
                            horizontalLineTo(13f)
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
            if (_more_vert != null) {
                return _more_vert!!
            }
            _more_vert =
                ImageVector.Builder(
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
                            moveTo(12f, 20f)
                            quadToRelative(-0.82f, 0f, -1.41f, -0.59f)
                            reflectiveQuadTo(10f, 18f)
                            reflectiveQuadToRelative(0.59f, -1.41f)
                            reflectiveQuadTo(12f, 16f)
                            reflectiveQuadToRelative(1.41f, 0.59f)
                            quadTo(14f, 17.18f, 14f, 18f)
                            reflectiveQuadToRelative(-0.59f, 1.41f)
                            reflectiveQuadTo(12f, 20f)
                            close()
                            moveToRelative(0f, -6f)
                            quadToRelative(-0.82f, 0f, -1.41f, -0.59f)
                            reflectiveQuadTo(10f, 12f)
                            reflectiveQuadToRelative(0.59f, -1.41f)
                            reflectiveQuadTo(12f, 10f)
                            reflectiveQuadToRelative(1.41f, 0.59f)
                            quadTo(14f, 11.18f, 14f, 12f)
                            reflectiveQuadToRelative(-0.59f, 1.41f)
                            reflectiveQuadTo(12f, 14f)
                            close()
                            moveTo(12f, 8f)
                            quadTo(11.18f, 8f, 10.59f, 7.41f)
                            reflectiveQuadTo(10f, 6f)
                            reflectiveQuadTo(10.59f, 4.59f)
                            reflectiveQuadTo(12f, 4f)
                            reflectiveQuadToRelative(1.41f, 0.59f)
                            quadTo(14f, 5.18f, 14f, 6f)
                            reflectiveQuadTo(13.41f, 7.41f)
                            reflectiveQuadTo(12f, 8f)
                            close()
                        }
                    }
                    .build()
            return _more_vert!!
        }

    private var _more_vert: ImageVector? = null


    val NetworkPing: ImageVector
        get() {
            if (_network_ping != null) {
                return _network_ping!!
            }
            _network_ping =
                ImageVector.Builder(
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
                            moveTo(4f, 18f)
                            verticalLineTo(16f)
                            horizontalLineToRelative(6.5f)
                            lineTo(2f, 7.5f)
                            lineTo(3.4f, 6.1f)
                            lineTo(12f, 14.68f)
                            lineToRelative(5.2f, -5.2f)
                            quadTo(17.1f, 9.25f, 17.05f, 9.01f)
                            reflectiveQuadTo(17f, 8.5f)
                            quadTo(17f, 7.45f, 17.73f, 6.72f)
                            reflectiveQuadTo(19.5f, 6f)
                            reflectiveQuadToRelative(1.78f, 0.72f)
                            reflectiveQuadTo(22f, 8.5f)
                            reflectiveQuadToRelative(-0.72f, 1.77f)
                            reflectiveQuadTo(19.5f, 11f)
                            quadToRelative(-0.22f, 0f, -0.44f, -0.04f)
                            quadTo(18.85f, 10.93f, 18.65f, 10.85f)
                            lineTo(13.5f, 16f)
                            horizontalLineTo(20f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(4f)
                            close()
                        }
                    }
                    .build()
            return _network_ping!!
        }

    private var _network_ping: ImageVector? = null


    val QrCode: ImageVector
        get() {
            if (_qr_code != null) {
                return _qr_code!!
            }
            _qr_code =
                ImageVector.Builder(
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
                            verticalLineTo(3f)
                            horizontalLineToRelative(8f)
                            verticalLineToRelative(8f)
                            horizontalLineTo(3f)
                            close()
                            moveTo(5f, 9f)
                            horizontalLineTo(9f)
                            verticalLineTo(5f)
                            horizontalLineTo(5f)
                            verticalLineTo(9f)
                            close()
                            moveTo(3f, 21f)
                            verticalLineTo(13f)
                            horizontalLineToRelative(8f)
                            verticalLineToRelative(8f)
                            horizontalLineTo(3f)
                            close()
                            moveTo(5f, 19f)
                            horizontalLineTo(9f)
                            verticalLineTo(15f)
                            horizontalLineTo(5f)
                            verticalLineToRelative(4f)
                            close()
                            moveToRelative(8f, -8f)
                            verticalLineTo(3f)
                            horizontalLineToRelative(8f)
                            verticalLineToRelative(8f)
                            horizontalLineTo(13f)
                            close()
                            moveTo(15f, 9f)
                            horizontalLineToRelative(4f)
                            verticalLineTo(5f)
                            horizontalLineTo(15f)
                            verticalLineTo(9f)
                            close()
                            moveToRelative(4f, 12f)
                            verticalLineTo(19f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(19f)
                            close()
                            moveTo(13f, 15f)
                            verticalLineTo(13f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(13f)
                            close()
                            moveToRelative(2f, 2f)
                            verticalLineTo(15f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(15f)
                            close()
                            moveToRelative(-2f, 2f)
                            verticalLineTo(17f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(13f)
                            close()
                            moveToRelative(2f, 2f)
                            verticalLineTo(19f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(15f)
                            close()
                            moveToRelative(2f, -2f)
                            verticalLineTo(17f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(17f)
                            close()
                            moveToRelative(0f, -4f)
                            verticalLineTo(13f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(17f)
                            close()
                            moveToRelative(2f, 2f)
                            verticalLineTo(15f)
                            horizontalLineToRelative(2f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(19f)
                            close()
                        }
                    }
                    .build()
            return _qr_code!!
        }

    private var _qr_code: ImageVector? = null


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
            if (_search != null) {
                return _search!!
            }
            _search =
                ImageVector.Builder(
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
                            moveTo(19.6f, 21f)
                            lineTo(13.3f, 14.7f)
                            quadToRelative(-0.75f, 0.6f, -1.72f, 0.95f)
                            reflectiveQuadTo(9.5f, 16f)
                            quadTo(6.78f, 16f, 4.89f, 14.11f)
                            quadTo(3f, 12.23f, 3f, 9.5f)
                            quadTo(3f, 6.77f, 4.89f, 4.89f)
                            reflectiveQuadTo(9.5f, 3f)
                            reflectiveQuadToRelative(4.61f, 1.89f)
                            reflectiveQuadTo(16f, 9.5f)
                            quadToRelative(0f, 1.1f, -0.35f, 2.07f)
                            reflectiveQuadTo(14.7f, 13.3f)
                            lineTo(21f, 19.6f)
                            lineTo(19.6f, 21f)
                            close()
                            moveTo(9.5f, 14f)
                            quadToRelative(1.88f, 0f, 3.19f, -1.31f)
                            reflectiveQuadTo(14f, 9.5f)
                            reflectiveQuadTo(12.69f, 6.31f)
                            reflectiveQuadTo(9.5f, 5f)
                            reflectiveQuadTo(6.31f, 6.31f)
                            reflectiveQuadTo(5f, 9.5f)
                            reflectiveQuadToRelative(1.31f, 3.19f)
                            reflectiveQuadTo(9.5f, 14f)
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
            if (_star != null) {
                return _star!!
            }
            _star =
                ImageVector.Builder(
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
                            moveTo(8.85f, 16.83f)
                            lineTo(12f, 14.93f)
                            lineToRelative(3.15f, 1.93f)
                            lineToRelative(-0.82f, -3.6f)
                            lineToRelative(2.78f, -2.4f)
                            lineTo(13.45f, 10.52f)
                            lineTo(12f, 7.13f)
                            lineTo(10.55f, 10.5f)
                            lineTo(6.9f, 10.83f)
                            lineToRelative(2.78f, 2.43f)
                            lineTo(8.85f, 16.83f)
                            close()
                            moveTo(5.83f, 21f)
                            lineTo(7.45f, 13.98f)
                            lineTo(2f, 9.25f)
                            lineTo(9.2f, 8.63f)
                            lineTo(12f, 2f)
                            lineToRelative(2.8f, 6.63f)
                            lineTo(22f, 9.25f)
                            lineToRelative(-5.45f, 4.72f)
                            lineTo(18.18f, 21f)
                            lineTo(12f, 17.27f)
                            lineTo(5.83f, 21f)
                            close()
                            moveTo(12f, 12.25f)
                            close()
                        }
                    }
                    .build()
            return _star!!
        }

    private var _star: ImageVector? = null


    val Storage: ImageVector
        get() {
            if (_storage != null) {
                return _storage!!
            }
            _storage =
                ImageVector.Builder(
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
                            moveTo(3f, 20f)
                            verticalLineTo(16f)
                            horizontalLineTo(21f)
                            verticalLineToRelative(4f)
                            horizontalLineTo(3f)
                            close()
                            moveTo(5f, 19f)
                            horizontalLineTo(7f)
                            verticalLineTo(17f)
                            horizontalLineTo(5f)
                            verticalLineToRelative(2f)
                            close()
                            moveTo(3f, 8f)
                            verticalLineTo(4f)
                            horizontalLineTo(21f)
                            verticalLineTo(8f)
                            horizontalLineTo(3f)
                            close()
                            moveTo(5f, 7f)
                            horizontalLineTo(7f)
                            verticalLineTo(5f)
                            horizontalLineTo(5f)
                            verticalLineTo(7f)
                            close()
                            moveTo(3f, 14f)
                            verticalLineTo(10f)
                            horizontalLineTo(21f)
                            verticalLineToRelative(4f)
                            horizontalLineTo(3f)
                            close()
                            moveTo(5f, 13f)
                            horizontalLineTo(7f)
                            verticalLineTo(11f)
                            horizontalLineTo(5f)
                            verticalLineToRelative(2f)
                            close()
                        }
                    }
                    .build()
            return _storage!!
        }

    private var _storage: ImageVector? = null


    val TextFormat: ImageVector
        get() {
            if (_text_format != null) {
                return _text_format!!
            }
            _text_format =
                ImageVector.Builder(
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
                            moveTo(5f, 19f)
                            verticalLineTo(17f)
                            horizontalLineTo(19f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(5f)
                            close()
                            moveTo(6.9f, 15f)
                            lineTo(11f, 4f)
                            horizontalLineToRelative(2f)
                            lineToRelative(4.1f, 11f)
                            horizontalLineTo(15.2f)
                            lineTo(14.25f, 12.2f)
                            horizontalLineTo(9.8f)
                            lineTo(8.8f, 15f)
                            horizontalLineTo(6.9f)
                            close()
                            moveToRelative(3.45f, -4.4f)
                            horizontalLineToRelative(3.3f)
                            lineTo(12.05f, 6.05f)
                            horizontalLineToRelative(-0.1f)
                            lineToRelative(-1.6f, 4.55f)
                            close()
                        }
                    }
                    .build()
            return _text_format!!
        }

    private var _text_format: ImageVector? = null


    val Timer: ImageVector
        get() {
            if (_timer != null) {
                return _timer!!
            }
            _timer =
                ImageVector.Builder(
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
                            moveTo(9f, 3f)
                            verticalLineTo(1f)
                            horizontalLineToRelative(6f)
                            verticalLineTo(3f)
                            horizontalLineTo(9f)
                            close()
                            moveToRelative(2f, 11f)
                            horizontalLineToRelative(2f)
                            verticalLineTo(8f)
                            horizontalLineTo(11f)
                            verticalLineToRelative(6f)
                            close()
                            moveTo(8.51f, 21.29f)
                            quadTo(6.88f, 20.58f, 5.65f, 19.35f)
                            reflectiveQuadTo(3.71f, 16.49f)
                            reflectiveQuadTo(3f, 13f)
                            reflectiveQuadTo(3.71f, 9.51f)
                            reflectiveQuadTo(5.65f, 6.65f)
                            quadTo(6.88f, 5.43f, 8.51f, 4.71f)
                            reflectiveQuadTo(12f, 4f)
                            quadToRelative(1.55f, 0f, 2.98f, 0.5f)
                            reflectiveQuadToRelative(2.68f, 1.45f)
                            lineToRelative(1.4f, -1.4f)
                            lineToRelative(1.4f, 1.4f)
                            lineToRelative(-1.4f, 1.4f)
                            quadTo(20f, 8.6f, 20.5f, 10.02f)
                            reflectiveQuadTo(21f, 13f)
                            quadToRelative(0f, 1.85f, -0.71f, 3.49f)
                            reflectiveQuadToRelative(-1.94f, 2.86f)
                            reflectiveQuadToRelative(-2.86f, 1.94f)
                            reflectiveQuadTo(12f, 22f)
                            reflectiveQuadTo(8.51f, 21.29f)
                            close()
                            moveToRelative(8.44f, -3.34f)
                            quadTo(19f, 15.9f, 19f, 13f)
                            reflectiveQuadTo(16.95f, 8.05f)
                            reflectiveQuadTo(12f, 6f)
                            reflectiveQuadTo(7.05f, 8.05f)
                            reflectiveQuadTo(5f, 13f)
                            reflectiveQuadToRelative(2.05f, 4.95f)
                            reflectiveQuadTo(12f, 20f)
                            reflectiveQuadToRelative(4.95f, -2.05f)
                            close()
                            moveTo(12f, 13f)
                            close()
                        }
                    }
                    .build()
            return _timer!!
        }

    private var _timer: ImageVector? = null


    val Transform: ImageVector
        get() {
            if (_transform != null) {
                return _transform!!
            }
            _transform =
                ImageVector.Builder(
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
                            moveTo(16f, 23f)
                            lineTo(12f, 19f)
                            lineToRelative(1.4f, -1.45f)
                            lineToRelative(1.6f, 1.6f)
                            verticalLineTo(17f)
                            horizontalLineTo(9f)
                            quadTo(8.18f, 17f, 7.59f, 16.41f)
                            reflectiveQuadTo(7f, 15f)
                            verticalLineTo(9f)
                            horizontalLineTo(2f)
                            verticalLineTo(7f)
                            horizontalLineTo(7f)
                            verticalLineTo(4.85f)
                            lineTo(5.4f, 6.45f)
                            lineTo(4f, 5f)
                            lineTo(8f, 1f)
                            lineToRelative(4f, 4f)
                            lineTo(10.6f, 6.45f)
                            lineTo(9f, 4.85f)
                            verticalLineTo(15f)
                            horizontalLineTo(22f)
                            verticalLineToRelative(2f)
                            horizontalLineTo(17f)
                            verticalLineToRelative(2.15f)
                            lineToRelative(1.6f, -1.6f)
                            lineTo(20f, 19f)
                            lineToRelative(-4f, 4f)
                            close()
                            moveTo(15f, 13f)
                            verticalLineTo(9f)
                            horizontalLineTo(11f)
                            verticalLineTo(7f)
                            horizontalLineToRelative(4f)
                            quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                            reflectiveQuadTo(17f, 9f)
                            verticalLineToRelative(4f)
                            horizontalLineTo(15f)
                            close()
                        }
                    }
                    .build()
            return _transform!!
        }

    private var _transform: ImageVector? = null


    val Verified: ImageVector
        get() {
            if (_verified != null) {
                return _verified!!
            }
            _verified =
                ImageVector.Builder(
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
                            moveTo(8.6f, 22.5f)
                            lineTo(6.7f, 19.3f)
                            lineTo(3.1f, 18.5f)
                            lineTo(3.45f, 14.8f)
                            lineTo(1f, 12f)
                            lineTo(3.45f, 9.2f)
                            lineTo(3.1f, 5.5f)
                            lineTo(6.7f, 4.7f)
                            lineTo(8.6f, 1.5f)
                            lineTo(12f, 2.95f)
                            lineTo(15.4f, 1.5f)
                            lineToRelative(1.9f, 3.2f)
                            lineToRelative(3.6f, 0.8f)
                            lineTo(20.55f, 9.2f)
                            lineTo(23f, 12f)
                            lineToRelative(-2.45f, 2.8f)
                            lineToRelative(0.35f, 3.7f)
                            lineToRelative(-3.6f, 0.8f)
                            lineToRelative(-1.9f, 3.2f)
                            lineTo(12f, 21.05f)
                            lineTo(8.6f, 22.5f)
                            close()
                            moveTo(9.45f, 19.95f)
                            lineTo(12f, 18.85f)
                            lineToRelative(2.6f, 1.1f)
                            lineTo(16f, 17.55f)
                            lineTo(18.75f, 16.9f)
                            lineTo(18.5f, 14.1f)
                            lineTo(20.35f, 12f)
                            lineTo(18.5f, 9.85f)
                            lineToRelative(0.25f, -2.8f)
                            lineTo(16f, 6.45f)
                            lineTo(14.55f, 4.05f)
                            lineTo(12f, 5.15f)
                            lineTo(9.4f, 4.05f)
                            lineTo(8f, 6.45f)
                            lineTo(5.25f, 7.05f)
                            lineTo(5.5f, 9.85f)
                            lineTo(3.65f, 12f)
                            lineTo(5.5f, 14.1f)
                            lineTo(5.25f, 16.95f)
                            lineTo(8f, 17.55f)
                            lineToRelative(1.45f, 2.4f)
                            close()
                            moveTo(12f, 12f)
                            close()
                            moveToRelative(-1.05f, 3.55f)
                            lineTo(16.6f, 9.9f)
                            lineTo(15.2f, 8.45f)
                            lineTo(10.95f, 12.7f)
                            lineTo(8.8f, 10.6f)
                            lineTo(7.4f, 12f)
                            lineToRelative(3.55f, 3.55f)
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

    val CodeXml: ImageVector
        get() {
            if (_code_xml != null) {
                return _code_xml!!
            }
            _code_xml =
                ImageVector.Builder(
                    name = "code_xml",
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
                            moveTo(6f, 17f)
                            lineTo(1f, 12f)
                            lineTo(6f, 7f)
                            lineTo(7.4f, 8.4f)
                            lineTo(3.83f, 12f)
                            lineTo(7.4f, 15.6f)
                            lineTo(6f, 17f)
                            close()
                            moveToRelative(4.45f, 3.3f)
                            lineTo(8.55f, 19.7f)
                            lineToRelative(5f, -16f)
                            lineToRelative(1.9f, 0.6f)
                            lineToRelative(-5f, 16f)
                            close()
                            moveTo(18f, 17f)
                            lineTo(16.6f, 15.6f)
                            lineTo(20.18f, 12f)
                            lineTo(16.6f, 8.4f)
                            lineTo(18f, 7f)
                            lineToRelative(5f, 5f)
                            lineToRelative(-5f, 5f)
                            close()
                        }
                    }
                    .build()
            return _code_xml!!
        }

    private var _code_xml: ImageVector? = null

    val Markdown: ImageVector
        get() {
            if (_markdown != null) {
                return _markdown!!
            }
            _markdown =
                ImageVector.Builder(
                    name = "markdown",
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
                            moveTo(16f, 15f)
                            lineToRelative(3f, -3f)
                            lineTo(17.95f, 10.93f)
                            lineToRelative(-1.2f, 1.2f)
                            verticalLineTo(9f)
                            horizontalLineToRelative(-1.5f)
                            verticalLineToRelative(3.13f)
                            lineToRelative(-1.2f, -1.2f)
                            lineTo(13f, 12f)
                            lineToRelative(3f, 3f)
                            close()
                            moveTo(4f, 20f)
                            quadTo(3.18f, 20f, 2.59f, 19.41f)
                            reflectiveQuadTo(2f, 18f)
                            verticalLineTo(6f)
                            quadTo(2f, 5.18f, 2.59f, 4.59f)
                            reflectiveQuadTo(4f, 4f)
                            horizontalLineTo(20f)
                            quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                            quadTo(22f, 5.18f, 22f, 6f)
                            verticalLineTo(18f)
                            quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                            reflectiveQuadTo(20f, 20f)
                            horizontalLineTo(4f)
                            close()
                            moveTo(4f, 18f)
                            horizontalLineTo(20f)
                            verticalLineTo(6f)
                            horizontalLineTo(4f)
                            verticalLineTo(18f)
                            close()
                            moveToRelative(0f, 0f)
                            verticalLineTo(6f)
                            verticalLineTo(18f)
                            close()
                            moveTo(5.5f, 15f)
                            horizontalLineTo(7f)
                            verticalLineTo(10.5f)
                            horizontalLineTo(8f)
                            verticalLineToRelative(3f)
                            horizontalLineTo(9.5f)
                            verticalLineToRelative(-3f)
                            horizontalLineToRelative(1f)
                            verticalLineTo(15f)
                            horizontalLineTo(12f)
                            verticalLineTo(10f)
                            quadTo(12f, 9.57f, 11.71f, 9.29f)
                            reflectiveQuadTo(11f, 9f)
                            horizontalLineTo(6.5f)
                            quadTo(6.08f, 9f, 5.79f, 9.29f)
                            reflectiveQuadTo(5.5f, 10f)
                            verticalLineToRelative(5f)
                            close()
                        }
                    }
                    .build()
            return _markdown!!
        }

    private var _markdown: ImageVector? = null

    val DragIndicator: ImageVector
        get() {
            if (_drag_indicator != null) {
                return _drag_indicator!!
            }
            _drag_indicator =
                ImageVector.Builder(
                    name = "drag_indicator",
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
                            moveTo(9f, 20f)
                            quadTo(8.18f, 20f, 7.59f, 19.41f)
                            reflectiveQuadTo(7f, 18f)
                            reflectiveQuadTo(7.59f, 16.59f)
                            reflectiveQuadTo(9f, 16f)
                            quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                            quadTo(11f, 17.18f, 11f, 18f)
                            reflectiveQuadToRelative(-0.59f, 1.41f)
                            reflectiveQuadTo(9f, 20f)
                            close()
                            moveToRelative(6f, 0f)
                            quadToRelative(-0.82f, 0f, -1.41f, -0.59f)
                            reflectiveQuadTo(13f, 18f)
                            reflectiveQuadToRelative(0.59f, -1.41f)
                            reflectiveQuadTo(15f, 16f)
                            reflectiveQuadToRelative(1.41f, 0.59f)
                            quadTo(17f, 17.18f, 17f, 18f)
                            reflectiveQuadToRelative(-0.59f, 1.41f)
                            reflectiveQuadTo(15f, 20f)
                            close()
                            moveTo(9f, 14f)
                            quadTo(8.18f, 14f, 7.59f, 13.41f)
                            reflectiveQuadTo(7f, 12f)
                            reflectiveQuadTo(7.59f, 10.59f)
                            reflectiveQuadTo(9f, 10f)
                            quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                            quadTo(11f, 11.18f, 11f, 12f)
                            reflectiveQuadToRelative(-0.59f, 1.41f)
                            reflectiveQuadTo(9f, 14f)
                            close()
                            moveToRelative(6f, 0f)
                            quadToRelative(-0.82f, 0f, -1.41f, -0.59f)
                            reflectiveQuadTo(13f, 12f)
                            reflectiveQuadToRelative(0.59f, -1.41f)
                            reflectiveQuadTo(15f, 10f)
                            reflectiveQuadToRelative(1.41f, 0.59f)
                            quadTo(17f, 11.18f, 17f, 12f)
                            reflectiveQuadToRelative(-0.59f, 1.41f)
                            reflectiveQuadTo(15f, 14f)
                            close()
                            moveTo(9f, 8f)
                            quadTo(8.18f, 8f, 7.59f, 7.41f)
                            reflectiveQuadTo(7f, 6f)
                            reflectiveQuadTo(7.59f, 4.59f)
                            reflectiveQuadTo(9f, 4f)
                            quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                            quadTo(11f, 5.18f, 11f, 6f)
                            reflectiveQuadTo(10.41f, 7.41f)
                            reflectiveQuadTo(9f, 8f)
                            close()
                            moveToRelative(6f, 0f)
                            quadTo(14.18f, 8f, 13.59f, 7.41f)
                            reflectiveQuadTo(13f, 6f)
                            reflectiveQuadTo(13.59f, 4.59f)
                            reflectiveQuadTo(15f, 4f)
                            reflectiveQuadToRelative(1.41f, 0.59f)
                            quadTo(17f, 5.18f, 17f, 6f)
                            reflectiveQuadTo(16.41f, 7.41f)
                            reflectiveQuadTo(15f, 8f)
                            close()
                        }
                    }
                    .build()
            return _drag_indicator!!
        }

    private var _drag_indicator: ImageVector? = null

    val TextFile: ImageVector by lazy { ImageVector.Builder(
        name = "TextFileIcon",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            fill = SolidColor(Color.Black),
            fillAlpha = 1f,
            stroke = null,
            strokeAlpha = 1f,
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 1f,
            pathFillType = PathFillType.NonZero
        ) {
            // 文件轮廓
            moveTo(4f, 2f)
            curveTo(3.45f, 2f, 3f, 2.45f, 3f, 3f)
            lineTo(3f, 21f)
            curveTo(3f, 21.55f, 3.45f, 22f, 4f, 22f)
            lineTo(20f, 22f)
            curveTo(20.55f, 22f, 21f, 21.55f, 21f, 21f)
            lineTo(21f, 7f)
            lineTo(14f, 7f)
            curveTo(13.45f, 7f, 13f, 6.55f, 13f, 6f)
            lineTo(13f, 2f)
            lineTo(4f, 2f)
            close()

            // 折角
            moveTo(14f, 2f)
            lineTo(21f, 9f)
            lineTo(21f, 6f)
            curveTo(21f, 4.9f, 20.1f, 4f, 19f, 4f)
            lineTo(14f, 4f)
            lineTo(14f, 2f)
            close()

            // 文字横线
            moveTo(8f, 11f)
            lineTo(16f, 11f)
            moveTo(8f, 14f)
            lineTo(16f, 14f)
            moveTo(8f, 17f)
            lineTo(14f, 17f)
        }
    }.build() }

    val convertToText: ImageVector
        get() {
            if (_convert_to_text != null) {
                return _convert_to_text!!
            }
            _convert_to_text =
                ImageVector.Builder(
                    name = "convert_to_text",
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
                            moveTo(10f, 17f)
                            horizontalLineToRelative(4f)
                            verticalLineTo(15f)
                            horizontalLineTo(10f)
                            verticalLineToRelative(2f)
                            close()
                            moveToRelative(0f, -4f)
                            horizontalLineToRelative(7f)
                            verticalLineTo(11f)
                            horizontalLineTo(10f)
                            verticalLineToRelative(2f)
                            close()
                            moveTo(7f, 9f)
                            horizontalLineTo(17f)
                            verticalLineTo(7f)
                            horizontalLineTo(7f)
                            verticalLineTo(9f)
                            close()
                            moveToRelative(5f, 3f)
                            close()
                            moveTo(2f, 22f)
                            verticalLineTo(20f)
                            horizontalLineTo(4.55f)
                            quadTo(3.35f, 19.43f, 2.61f, 18.3f)
                            reflectiveQuadTo(1.88f, 15.75f)
                            quadToRelative(0f, -1.98f, 1.39f, -3.36f)
                            reflectiveQuadTo(6.63f, 11f)
                            verticalLineToRelative(2f)
                            quadTo(5.5f, 13f, 4.69f, 13.8f)
                            reflectiveQuadTo(3.88f, 15.75f)
                            quadToRelative(0f, 0.98f, 0.6f, 1.73f)
                            reflectiveQuadTo(6f, 18.43f)
                            verticalLineTo(16f)
                            horizontalLineTo(8f)
                            verticalLineToRelative(6f)
                            horizontalLineTo(2f)
                            close()
                            moveToRelative(8f, -1f)
                            verticalLineTo(19f)
                            horizontalLineToRelative(9f)
                            verticalLineTo(5f)
                            horizontalLineTo(5f)
                            verticalLineTo(9f)
                            horizontalLineTo(3f)
                            verticalLineTo(5f)
                            quadTo(3f, 4.17f, 3.59f, 3.59f)
                            reflectiveQuadTo(5f, 3f)
                            horizontalLineTo(19f)
                            quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                            reflectiveQuadTo(21f, 5f)
                            verticalLineTo(19f)
                            quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                            reflectiveQuadTo(19f, 21f)
                            horizontalLineTo(10f)
                            close()
                        }
                    }
                    .build()
            return _convert_to_text!!
        }

    private var _convert_to_text: ImageVector? = null

    val SwitchIcon: ImageVector
        get() {
            val current = _switchIcon
            if (current != null) return current

            return ImageVector.Builder(
                name = "material.SwitchIcon",
                defaultWidth = 200.0.dp,
                defaultHeight = 200.0.dp,
                viewportWidth = 1024.0f,
                viewportHeight = 1024.0f,
            ).apply {
                // M128 522.67 c17.07 0 32 -14.94 32 -32 V320 c0 -6.4 4.27 -10.67 10.67 -10.67 h652.8 l-83.2 83.2 c-12.8 12.8 -12.8 34.14 0 46.94 6.4 6.4 14.93 10.66 23.46 10.66 s17.07 -4.26 23.47 -10.66 L932.27 294.4 c12.8 -12.8 12.8 -34.13 0 -46.93 L787.2 102.4 c-12.8 -12.8 -34.13 -12.8 -46.93 0 s-12.8 34.13 0 46.93 l93.86 93.87 H170.67 c-40.54 0 -74.67 34.13 -74.67 74.67 v170.66 c0 19.2 14.93 34.14 32 34.14 m778.67 -21.34 c-17.07 0 -32 14.94 -32 32 V704 c0 6.4 -4.27 10.67 -10.67 10.67 H211.2 l83.2 -83.2 c12.8 -12.8 12.8 -34.14 0 -46.94 s-34.13 -12.8 -46.93 0 L102.4 729.6 c-12.8 12.8 -12.8 34.13 0 46.93 L247.47 921.6 c6.4 6.4 14.93 10.67 23.46 10.67 S288 928 294.4 921.6 c12.8 -12.8 12.8 -34.13 0 -46.93 l-93.87 -93.87 H864 c40.53 0 74.67 -34.13 74.67 -74.67 V535.47 c0 -19.2 -12.8 -34.14 -32 -34.14
                path(
                    fill = SolidColor(Color.Black),
                ) {
                    // M 128 522.67
                    moveTo(x = 128.0f, y = 522.67f)
                    // c 17.07 0 32 -14.94 32 -32
                    curveToRelative(
                        dx1 = 17.07f,
                        dy1 = 0.0f,
                        dx2 = 32.0f,
                        dy2 = -14.94f,
                        dx3 = 32.0f,
                        dy3 = -32.0f,
                    )
                    // V 320
                    verticalLineTo(y = 320.0f)
                    // c 0 -6.4 4.27 -10.67 10.67 -10.67
                    curveToRelative(
                        dx1 = 0.0f,
                        dy1 = -6.4f,
                        dx2 = 4.27f,
                        dy2 = -10.67f,
                        dx3 = 10.67f,
                        dy3 = -10.67f,
                    )
                    // h 652.8
                    horizontalLineToRelative(dx = 652.8f)
                    // l -83.2 83.2
                    lineToRelative(dx = -83.2f, dy = 83.2f)
                    // c -12.8 12.8 -12.8 34.14 0 46.94
                    curveToRelative(
                        dx1 = -12.8f,
                        dy1 = 12.8f,
                        dx2 = -12.8f,
                        dy2 = 34.14f,
                        dx3 = 0.0f,
                        dy3 = 46.94f,
                    )
                    // c 6.4 6.4 14.93 10.66 23.46 10.66
                    curveToRelative(
                        dx1 = 6.4f,
                        dy1 = 6.4f,
                        dx2 = 14.93f,
                        dy2 = 10.66f,
                        dx3 = 23.46f,
                        dy3 = 10.66f,
                    )
                    // s 17.07 -4.26 23.47 -10.66
                    reflectiveCurveToRelative(
                        dx1 = 17.07f,
                        dy1 = -4.26f,
                        dx2 = 23.47f,
                        dy2 = -10.66f,
                    )
                    // L 932.27 294.4
                    lineTo(x = 932.27f, y = 294.4f)
                    // c 12.8 -12.8 12.8 -34.13 0 -46.93
                    curveToRelative(
                        dx1 = 12.8f,
                        dy1 = -12.8f,
                        dx2 = 12.8f,
                        dy2 = -34.13f,
                        dx3 = 0.0f,
                        dy3 = -46.93f,
                    )
                    // L 787.2 102.4
                    lineTo(x = 787.2f, y = 102.4f)
                    // c -12.8 -12.8 -34.13 -12.8 -46.93 0
                    curveToRelative(
                        dx1 = -12.8f,
                        dy1 = -12.8f,
                        dx2 = -34.13f,
                        dy2 = -12.8f,
                        dx3 = -46.93f,
                        dy3 = 0.0f,
                    )
                    // s -12.8 34.13 0 46.93
                    reflectiveCurveToRelative(
                        dx1 = -12.8f,
                        dy1 = 34.13f,
                        dx2 = 0.0f,
                        dy2 = 46.93f,
                    )
                    // l 93.86 93.87
                    lineToRelative(dx = 93.86f, dy = 93.87f)
                    // H 170.67
                    horizontalLineTo(x = 170.67f)
                    // c -40.54 0 -74.67 34.13 -74.67 74.67
                    curveToRelative(
                        dx1 = -40.54f,
                        dy1 = 0.0f,
                        dx2 = -74.67f,
                        dy2 = 34.13f,
                        dx3 = -74.67f,
                        dy3 = 74.67f,
                    )
                    // v 170.66
                    verticalLineToRelative(dy = 170.66f)
                    // c 0 19.2 14.93 34.14 32 34.14
                    curveToRelative(
                        dx1 = 0.0f,
                        dy1 = 19.2f,
                        dx2 = 14.93f,
                        dy2 = 34.14f,
                        dx3 = 32.0f,
                        dy3 = 34.14f,
                    )
                    // m 778.67 -21.34
                    moveToRelative(dx = 778.67f, dy = -21.34f)
                    // c -17.07 0 -32 14.94 -32 32
                    curveToRelative(
                        dx1 = -17.07f,
                        dy1 = 0.0f,
                        dx2 = -32.0f,
                        dy2 = 14.94f,
                        dx3 = -32.0f,
                        dy3 = 32.0f,
                    )
                    // V 704
                    verticalLineTo(y = 704.0f)
                    // c 0 6.4 -4.27 10.67 -10.67 10.67
                    curveToRelative(
                        dx1 = 0.0f,
                        dy1 = 6.4f,
                        dx2 = -4.27f,
                        dy2 = 10.67f,
                        dx3 = -10.67f,
                        dy3 = 10.67f,
                    )
                    // H 211.2
                    horizontalLineTo(x = 211.2f)
                    // l 83.2 -83.2
                    lineToRelative(dx = 83.2f, dy = -83.2f)
                    // c 12.8 -12.8 12.8 -34.14 0 -46.94
                    curveToRelative(
                        dx1 = 12.8f,
                        dy1 = -12.8f,
                        dx2 = 12.8f,
                        dy2 = -34.14f,
                        dx3 = 0.0f,
                        dy3 = -46.94f,
                    )
                    // s -34.13 -12.8 -46.93 0
                    reflectiveCurveToRelative(
                        dx1 = -34.13f,
                        dy1 = -12.8f,
                        dx2 = -46.93f,
                        dy2 = 0.0f,
                    )
                    // L 102.4 729.6
                    lineTo(x = 102.4f, y = 729.6f)
                    // c -12.8 12.8 -12.8 34.13 0 46.93
                    curveToRelative(
                        dx1 = -12.8f,
                        dy1 = 12.8f,
                        dx2 = -12.8f,
                        dy2 = 34.13f,
                        dx3 = 0.0f,
                        dy3 = 46.93f,
                    )
                    // L 247.47 921.6
                    lineTo(x = 247.47f, y = 921.6f)
                    // c 6.4 6.4 14.93 10.67 23.46 10.67
                    curveToRelative(
                        dx1 = 6.4f,
                        dy1 = 6.4f,
                        dx2 = 14.93f,
                        dy2 = 10.67f,
                        dx3 = 23.46f,
                        dy3 = 10.67f,
                    )
                    // S 288 928 294.4 921.6
                    reflectiveCurveTo(
                        x1 = 288.0f,
                        y1 = 928.0f,
                        x2 = 294.4f,
                        y2 = 921.6f,
                    )
                    // c 12.8 -12.8 12.8 -34.13 0 -46.93
                    curveToRelative(
                        dx1 = 12.8f,
                        dy1 = -12.8f,
                        dx2 = 12.8f,
                        dy2 = -34.13f,
                        dx3 = 0.0f,
                        dy3 = -46.93f,
                    )
                    // l -93.87 -93.87
                    lineToRelative(dx = -93.87f, dy = -93.87f)
                    // H 864
                    horizontalLineTo(x = 864.0f)
                    // c 40.53 0 74.67 -34.13 74.67 -74.67
                    curveToRelative(
                        dx1 = 40.53f,
                        dy1 = 0.0f,
                        dx2 = 74.67f,
                        dy2 = -34.13f,
                        dx3 = 74.67f,
                        dy3 = -74.67f,
                    )
                    // V 535.47
                    verticalLineTo(y = 535.47f)
                    // c 0 -19.2 -12.8 -34.14 -32 -34.14
                    curveToRelative(
                        dx1 = 0.0f,
                        dy1 = -19.2f,
                        dx2 = -12.8f,
                        dy2 = -34.14f,
                        dx3 = -32.0f,
                        dy3 = -34.14f,
                    )
                }
            }.build().also { _switchIcon = it }
        }

    @Suppress("ObjectPropertyName")
    private var _switchIcon: ImageVector? = null
}
