package www.revengerfitness.blogspot.com.coroutine3kotlin

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Coroutine Builders : functions that help in creating coroutines.
 * we have already seen launch function.
 *
 * use launch : when you do not care about the result(fire and forget) .it return job instance.
 * use async : when you expect result/output from your coroutine. it returns deferred object.which resolve in future.
 * although both can be used to achieve the same functionality but it is better to use things that are meant for it.*/

class MainActivity : AppCompatActivity() {

    private val TAG: String = "prashant chauhan"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            printFollowers()
        }

    }

    private suspend fun printFollowers() {
        /**   var fbFollowers=0
        val job=  CoroutineScope(Dispatchers.IO).launch {// launch return job object.its a handle which manage coroutines.
        fbFollowers=getFbFollower()
        }
        job.join() // waiting until coroutine complete.
        Log.d(TAG,fbFollowers.toString())*/
// async return deferred<T> type object which is generic and T is the  data type of last statement of block.
        // behind the scenes Deferred interface inherit Job interface.
        val job =
            CoroutineScope(Dispatchers.IO).async {// those coroutines from which we expect some output or result.there we use async coroutine builder.
                getFbFollower()
                "hello"
            }
        Log.d(TAG, job.await().toString()) // waiting


    }

    private suspend fun getFbFollower(): Int {
        delay(1000)
        return 54
    }
}