package org.kotlin.exchange_domain.callback

/**
 * Callbacks for handling Api success/failure
 */
interface ResultCallback {
    fun success()
    fun failure()
}