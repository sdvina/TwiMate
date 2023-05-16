package org.jayhsu.twimate.repository

interface AppContainer {
    val tmRepository: TMRepository
}

class AppContainerImpl : AppContainer {

    override val tmRepository: TMRepository by lazy { TMRepository() }
}