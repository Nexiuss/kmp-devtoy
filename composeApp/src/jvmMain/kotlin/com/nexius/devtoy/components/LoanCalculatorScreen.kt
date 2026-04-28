package com.nexius.devtoy.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * 贷款计算器 - 等额本息 / 等额本金
 */
@Composable
fun LoanCalculatorScreen() {
    // 输入数据
    var loanAmount by remember { mutableStateOf("100000") }
    var loanYear by remember { mutableStateOf("20") }
    var loanRate by remember { mutableStateOf("3.45") }

    // 还款方式
    var repaymentType by remember { mutableStateOf(RepaymentType.AVERAGE_CAPITAL_PLUS_INTEREST) }

    // 计算结果
    var resultState by remember { mutableStateOf(LoanResult()) }

    // 实时计算
    LaunchedEffect(loanAmount, loanYear, loanRate, repaymentType) {
        resultState = calculateLoan(
            amount = loanAmount.toDoubleOrNull() ?: 0.0,
            year = loanYear.toIntOrNull() ?: 0,
            yearRate = loanRate.toDoubleOrNull() ?: 0.0,
            type = repaymentType
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                    )
                )
            )
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            // 标题
            Text(
                text = "贷款利息计算器",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // 输入卡片
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .shadow(8.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // 贷款金额
                    OutlinedTextField(
                        value = loanAmount,
                        onValueChange = { loanAmount = it },
                        label = { Text("贷款金额 (元)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    // 贷款年限
                    OutlinedTextField(
                        value = loanYear,
                        onValueChange = { loanYear = it },
                        label = { Text("贷款年限 (年)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    // 年利率
                    OutlinedTextField(
                        value = loanRate,
                        onValueChange = { loanRate = it },
                        label = { Text("年利率 (%)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    // 还款方式选择
                    Text(
                        text = "还款方式",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        RepaymentType.entries.forEach { type ->
                            val selected = repaymentType == type
                            FilterChip(
                                selected = selected,
                                onClick = { repaymentType = type },
                                label = { Text(type.desc) },
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 结果展示卡片
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .shadow(8.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("每月月供", fontSize = 15.sp)
                        Text(
                            resultState.monthlyPayment,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("还款总额", fontSize = 15.sp)
                        Text(
                            resultState.totalPayment,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("总利息", fontSize = 15.sp)
                        Text(
                            resultState.totalInterest,
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    }

                    if (repaymentType == RepaymentType.AVERAGE_PRINCIPAL) {
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("首月月供", fontSize = 15.sp)
                            Text(
                                resultState.firstMonthPayment,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("每月递减", fontSize = 15.sp)
                            Text(
                                resultState.monthlyReduce,
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 底部说明
            Text(
                text = "数据仅供参考，实际以银行核算为准",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// 还款方式枚举
enum class RepaymentType(val desc: String) {
    AVERAGE_CAPITAL_PLUS_INTEREST("等额本息"),
    AVERAGE_PRINCIPAL("等额本金")
}

// 计算结果数据类
data class LoanResult(
    val monthlyPayment: String = "0.00 元",
    val totalPayment: String = "0.00 元",
    val totalInterest: String = "0.00 元",
    val firstMonthPayment: String = "0.00 元",
    val monthlyReduce: String = "0.00 元"
)

/**
 * 核心贷款计算逻辑
 */
fun calculateLoan(
    amount: Double,
    year: Int,
    yearRate: Double,
    type: RepaymentType
): LoanResult {
    if (amount <= 0 || year <= 0 || yearRate <= 0) return LoanResult()

    val month = year * 12
    val monthRate = yearRate / 100 / 12

    return when (type) {
        // 等额本息
        RepaymentType.AVERAGE_CAPITAL_PLUS_INTEREST -> {
            val monthly = amount * monthRate * Math.pow(1 + monthRate, month.toDouble()) /
                    (Math.pow(1 + monthRate, month.toDouble()) - 1)
            val total = monthly * month
            val interest = total - amount

            LoanResult(
                monthlyPayment = monthly.formatMoney(),
                totalPayment = total.formatMoney(),
                totalInterest = interest.formatMoney()
            )
        }
        // 等额本金
        RepaymentType.AVERAGE_PRINCIPAL -> {
            val monthCapital = amount / month
            var firstMonth = amount * monthRate + monthCapital
            val reduce = monthCapital * monthRate
            val totalInterest = (month + 1) * amount * monthRate / 2
            val total = amount + totalInterest

            LoanResult(
                monthlyPayment = "逐月递减",
                totalPayment = total.formatMoney(),
                totalInterest = totalInterest.formatMoney(),
                firstMonthPayment = firstMonth.formatMoney(),
                monthlyReduce = reduce.formatMoney()
            )
        }
    }
}

// 金额格式化 保留2位小数
fun Double.formatMoney(): String {
    return BigDecimal(this)
        .setScale(2, RoundingMode.HALF_UP)
        .toPlainString() + " 元"
}