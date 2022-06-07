package com.example.coroutineexmp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class Second : AppCompatActivity() {
    val scope = CoroutineScope(Dispatchers.IO + CoroutineName("myname"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        coroutincancelcheck()
        //for global scope
//        GlobalScope.launch {
//            while(true){
//                delay(1000)
//                Log.d("Mycoroutine","running")
//            }
//
//        }

        // for lifecycle
//            lifecycleScope.launch {
//            while(true){
//                delay(1000)
//                Log.d("Mycoroutine","running")
//            }
//
//        }

        // for scope
        scope.launch {
            Log.d("Mycoroutine", this.coroutineContext.toString())
        }

        //multiple coroutine
//        scope.launch {
//            Log.d("Mycoroutine", this.coroutineContext.toString())
//            launch {
//            }
//        }

        //val job =   scope.launch {
        //            Log.d("Mycoroutine",this.coroutineContext.toString())
        //        }

    }

    private fun coroutincancelcheck() {

       val mainjob = scope.launch {
            val job1 = launch {
                while (isActive) {
                    Log.d("cancelis", "running")
                }
            }
            delay(1000)
            Log.d("cancelis", "cancelling")
            job1.cancel()
            job1.join() // it will exceute only if the job 1 is canceeled
            Log.d("cancelis", "cancelled")

        }
//        mainjob.cancelAndJoin() // we can call only inside suspend
 runBlocking {
     mainjob.cancelAndJoin()
     Log.d("cancelis", "Mainjobcancelled")
 }
    }

    override fun onStop() {
        Log.d("Mycoroutine", "stop")
        super.onStop()
    }

    override fun onPause() {
        Log.d("Mycoroutine", "pause")
        super.onPause()
    }

    override fun onResume() {
        Log.d("Mycoroutine", "resume")
        super.onResume()
    }
}