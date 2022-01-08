package com.example.shroomapp.domain

enum class NotificationCondition(val value: String) {
    IS_GREATER("="),
    IS_LESS("<"),
    IS_EQUAL(">")
}