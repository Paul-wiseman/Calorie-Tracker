package com.plcoding.core.domain.use_case

import javax.inject.Inject

class FilterOutDigits @Inject constructor() {
    operator fun invoke(input: String) = input.filter { it.isDigit() }
}