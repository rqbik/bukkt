package com.github.rqbik.bukkt.extensions.time

class Time(val time: Double) {
    constructor(time: Int) : this(time.toDouble())
    constructor(time: Long) : this(time.toDouble())
    constructor(time: Short) : this(time.toDouble())
    constructor(time: Float) : this(time.toDouble())

    var type = TimeType.MILLISECONDS

    infix fun of(type: TimeType): Time {
        this.type = type
        return this
    }

    infix fun get(type: TimeType): Double = when (type) {
        TimeType.MILLISECONDS -> asMilliseconds()
        TimeType.TICKS -> asTicks()
        TimeType.SECONDS -> asSeconds()
        TimeType.MINUTES -> asMinutes()
        TimeType.HOURS -> asHours()
    }

    fun asMilliseconds() = when (type) {
        TimeType.MILLISECONDS -> time
        TimeType.TICKS -> time * 50.0
        TimeType.SECONDS -> time * 50.0 * 20.0
        TimeType.MINUTES -> time * 50.0 * 20.0 * 60.0
        TimeType.HOURS -> time * 50.0 * 20.0 * 60.0 * 60.0
    }

    fun asTicks() = when (type) {
        TimeType.MILLISECONDS -> time / 50.0
        TimeType.TICKS -> time
        TimeType.SECONDS -> time * 20.0
        TimeType.MINUTES -> time * 20.0 * 60.0
        TimeType.HOURS -> time * 20.0 * 60.0 * 60.0
    }

    fun asSeconds() = when (type) {
        TimeType.MILLISECONDS -> time / (50.0 * 20.0)
        TimeType.TICKS -> time / 20.0
        TimeType.SECONDS -> time
        TimeType.MINUTES -> time * 60.0
        TimeType.HOURS -> time * 60.0 * 60.0
    }

    fun asMinutes() = when (type) {
        TimeType.MILLISECONDS -> time / (50.0 * 20.0 * 60.0)
        TimeType.TICKS -> time / (20.0 * 60.0)
        TimeType.SECONDS -> time / 60.0
        TimeType.MINUTES -> time
        TimeType.HOURS -> time * 60.0
    }

    fun asHours() = when (type) {
        TimeType.MILLISECONDS -> time / (50.0 * 20.0 * 60.0 * 60.0)
        TimeType.TICKS -> time / (20.0 * 60.0 * 60.0)
        TimeType.SECONDS -> time / (60.0 * 60.0)
        TimeType.MINUTES -> time / 60.0
        TimeType.HOURS -> time
    }
}
