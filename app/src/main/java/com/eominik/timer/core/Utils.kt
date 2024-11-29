package com.eominik.timer.core

val <T> T.exhaustive: T
    get() = this

//The exhaustive extension property in Kotlin is a common pattern used to
// ensure that all cases of a sealed class or enum are handled in a when
// expression. By adding this property, you can enforce exhaustiveness
// checking at compile time, which helps prevent runtime errors due to
// unhandled cases.
