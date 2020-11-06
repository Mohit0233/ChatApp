package com.example.chatapp.ui.home

import android.util.Log
import androidx.annotation.RestrictTo
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * A fake lifecycle owner which obeys the lifecycle transition rules.
 *
 * @hide
 * @see [lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
class CustomLifecycle : LifecycleOwner {
    private val mLifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    /**
     * Starts and resumes the lifecycle.
     *
     *
     * The lifecycle is put into the STARTED and RESUMED states. The lifecycle must already be in
     * the CREATED state or an exception is thrown.
     */
    fun startAndResume() {
        //check(mLifecycleRegistry.currentState == Lifecycle.State.CREATED) { "Invalid state transition." }
        Log.e("must init here", mLifecycleRegistry.currentState.toString())
        mLifecycleRegistry.currentState = Lifecycle.State.STARTED
        mLifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    /**
     * Starts the lifecycle.
     *
     *
     * The lifecycle is put into the START state. The lifecycle must already be in the CREATED
     * state or an exception is thrown.
     */
    fun start() {
        check(mLifecycleRegistry.currentState == Lifecycle.State.CREATED) { "Invalid state transition." }
        mLifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    /**
     * Pauses and stops the lifecycle.
     *
     *
     * The lifecycle is put into the STARTED and CREATED states. The lifecycle must already be in
     * the RESUMED state or an exception is thrown.
     */
    fun pauseAndStop() {
        check(mLifecycleRegistry.currentState == Lifecycle.State.RESUMED) { "Invalid state transition." }
        mLifecycleRegistry.currentState = Lifecycle.State.STARTED
        mLifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    /**
     * Stops the lifecycle.
     *
     *
     * The lifecycle is put into the CREATED state. The lifecycle must already be in the STARTED
     * state or an exception is thrown.
     */
    fun stop() {
        check(mLifecycleRegistry.currentState == Lifecycle.State.STARTED) { "Invalid state transition." }
        mLifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    /**
     * Destroys the lifecycle.
     *
     *
     * The lifecycle is put into the DESTROYED state. The lifecycle must already be in the
     * CREATED state or an exception is thrown.
     */
    fun destroy() {
        Log.e("must init here", mLifecycleRegistry.currentState.toString())
        //check(mLifecycleRegistry.currentState == Lifecycle.State.RESUMED) { "Invalid state transition." }
        mLifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    /** Returns the number of observers of this lifecycle.  */
    val observerCount: Int
        get() = mLifecycleRegistry.observerCount

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }

    /**
     * Creates a new lifecycle owner.
     *
     *
     * The lifecycle is initial put into the INITIALIZED and CREATED states.
     */
    init {
        Log.e("going to init","wow that should be initialized")
        mLifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
        mLifecycleRegistry.currentState = Lifecycle.State.CREATED
        Log.e("must init here", mLifecycleRegistry.currentState.toString())
    }
}
