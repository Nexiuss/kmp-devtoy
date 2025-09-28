package com.nexius.log

import java.time.LocalDate

class Log {
    companion object {
        fun d(tag: String, msg: String) {
           print("[${LocalDate.now()}]-[$tag]: $msg")
        }
    }
}