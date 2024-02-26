package com.harsh.chattask

import java.util.Calendar

data class MessageData(
    var id: Int = 0,
    var message: String? = null,
    var dateTime: Calendar = Calendar.getInstance()
)
