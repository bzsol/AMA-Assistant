package hu.unideb.assistant

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import hu.unideb.assistant.data.Message
import hu.unideb.assistant.databinding.ActivityMainBinding
import hu.unideb.assistant.utils.Constants.OPEN_GOOGLE
import hu.unideb.assistant.utils.Constants.OPEN_SEARCH
import hu.unideb.assistant.utils.Constants.RECEIVE_ID
import hu.unideb.assistant.utils.Constants.SEND_ID
import hu.unideb.assistant.utils.Response
import hu.unideb.assistant.utils.Time
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MessengerAdapter
    private lateinit var binding: ActivityMainBinding
    private var namelist = listOf("Gergely")

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recView()

        customMessage("Hi!\nMy name is ${namelist[(namelist.indices).random()]}\nHow can I help you?")
        customMessage("Please read the README.md in the github project!!")
        customMessage("I hope this will be a good app to demonstrate the Kotlin is very good for programming!! \uD83E\uDD73 \uD83E\uDD73")
        clickEvent()
    }

    @DelicateCoroutinesApi
    private fun clickEvent() {
        binding.btnSend.setOnClickListener{
            sendMessage()
        }
        binding.etMessage.setOnClickListener{
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main){
                    binding.rvMessages.scrollToPosition(adapter.itemCount-1)
                }
            }
        }
    }

    private fun recView() {
        adapter = MessengerAdapter()
        binding.rvMessages.adapter = adapter
        binding.rvMessages.layoutManager = LinearLayoutManager(applicationContext)
    }

    @DelicateCoroutinesApi
    private fun sendMessage(){
        val message = binding.etMessage.text.toString()
        val timeStamp = Time.TimeStamp()
        if(message.isNotEmpty()){
            binding.etMessage.setText("")
            adapter.insert(Message(message, SEND_ID,timeStamp))
            binding.rvMessages.scrollToPosition(adapter.itemCount-1)
            botResponse(message)
        }
    }

    @DelicateCoroutinesApi
    private fun botResponse(message: String) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                val resp = Response.basicResponse(message)
                adapter.insert(Message(resp, RECEIVE_ID,Time.TimeStamp()))
                binding.rvMessages.scrollToPosition(adapter.itemCount-1)
                when(resp){
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchData: String = message.substringAfter("search")
                        site.data = Uri.parse("https://google.com/search?&q=$searchData")
                        startActivity(site)
                    }
                }
            }
        }

    }

    @DelicateCoroutinesApi
    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                binding.rvMessages.scrollToPosition(adapter.itemCount-1)
            }
        }
    }

    @DelicateCoroutinesApi
    private fun customMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                val timeStamp = Time.TimeStamp()
                adapter.insert(Message(message,RECEIVE_ID,timeStamp))

                binding.rvMessages.scrollToPosition(adapter.itemCount-1)
            }
        }
    }
}