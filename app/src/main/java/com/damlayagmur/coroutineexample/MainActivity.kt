package com.damlayagmur.coroutineexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    lateinit var tvDummy: TextView
    lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvDummy = findViewById(R.id.tvDummy)
        button = findViewById(R.id.btnStartActivity)

        button.setOnClickListener {
            lifecycleScope.launch {
                while (true){
                    delay(1000L)
                    Log.d(TAG,"Still running...")
                }
            }
            GlobalScope.launch {
                delay(5000L)
                Intent(this@MainActivity,SecondActivity::class.java).also{
                    startActivity(it)
                    finish()
                }
            }
        }

        /*GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                var networkCallAnswer = async { doNetworkCall() }
                var networkCallAnswer2 = async { doNetworkCall2() }

                Log.d(TAG, "Answer1 is ${networkCallAnswer.await()}")
                Log.d(TAG, "Answer2 is ${networkCallAnswer2.await()}")
            }
            Log.d(TAG, "Requests took $time ms")
        }*/


        /*val job = GlobalScope.launch(Dispatchers.Default) {
            repeat(5){
                Log.d(TAG, "Coroutines is still working...")
                delay(1000L)
            }
        }

        runBlocking {
            job.join()
            Log.d(TAG, "Main Thread is continuing...")
        }


        GlobalScope.launch {
            val networkCallAnswer = doNetworkCall()
            val networkCallAnswer2 = doNetworkCall2()
            Log.d(TAG, networkCallAnswer)
            Log.d(TAG, networkCallAnswer2)

            //delay(5000L)
            //Log.d(TAG, "Coroutines says hello from thread ${Thread.currentThread().name}")
        }

        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Starting coroutine in thread ${Thread.currentThread().name}")
            val answer = doNetworkCall()
            withContext(Dispatchers.Main) {
                Log.d(TAG, "Setting text in thread ${Thread.currentThread().name}")
                tvDummy.text = answer
            }
        }
        Log.d(TAG, "Hello from thread ${Thread.currentThread().name}")

        Log.d(TAG, "Before runBlocking")
        runBlocking {
            launch(Dispatchers.IO) {
                delay(3000L)
                Log.d(TAG, "Finished IO Coroutine 1")
            }
            launch(Dispatchers.IO) {
                delay(6000L)
                Log.d(TAG, "Finished IO Coroutine 2")
            }
            Log.d(TAG, "Start runBlocking")
            delay(100L)
            Log.d(TAG, "End runBlocking")
        }
        Log.d(TAG, "After runBlocking")*/
    }

    private suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the answer"
    }

    private suspend fun doNetworkCall2(): String {
        delay(3000L)
        return "This is the answer2"
    }
}