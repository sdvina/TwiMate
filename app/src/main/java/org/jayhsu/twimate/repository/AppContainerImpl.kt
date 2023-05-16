package org.jayhsu.twimate.repository

interface AppContainer {
    val tmRepository: TMRepository
    val fakeRepository: FakeRepository
}

class AppContainerImpl : AppContainer {

    override val tmRepository: TMRepository by lazy { TMRepository() }

    override val fakeRepository: FakeRepository by lazy { FakeRepository() }
}