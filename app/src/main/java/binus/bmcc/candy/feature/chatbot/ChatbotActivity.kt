package binus.bmcc.candy.feature.chatbot

import ai.api.AIConfiguration.*
import ai.api.AIDataService
import ai.api.android.AIConfiguration
import ai.api.android.AIConfiguration.*
import ai.api.android.AIService
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import binus.bmcc.candy.R
import binus.bmcc.candy.adapter.ChatAdapter
import binus.bmcc.candy.entity.ChatMessage
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chatbot.*

class ChatbotActivity : AppCompatActivity(), ChatContract.View {

    lateinit var adapter: ChatAdapter
    lateinit var ref: DatabaseReference
    lateinit var aiService: AIService
    lateinit var aiDataAIService: AIDataService
    var user: String? = null

    lateinit var mPresenter : ChatContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbot)
        initPresenter()
        val actionBar = supportActionBar
        actionBar!!.title = "Marbot"
        actionBar.setDisplayHomeAsUpEnabled(true)

        message_list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        message_list.layoutManager = layoutManager
        ref.keepSynced(true)

        btn_send.setOnClickListener {
            val message = edt_message.text.toString()
            if (message != "") {
                mPresenter.sendMessage(message)
            } else {
                aiService.startListening()
                Toast.makeText(applicationContext, "Enter message first", Toast.LENGTH_SHORT).show()
            }
            edt_message.setText("")
        }
        val options = FirebaseRecyclerOptions.Builder<ChatMessage>()
            .setQuery(ref.child("chat"), ChatMessage::class.java)
            .build()

        adapter = ChatAdapter(options)
        message_list.adapter = adapter
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)

                val msgCount = adapter.itemCount
                val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()

                if (lastVisiblePosition == -1 || positionStart >= msgCount - 1 && lastVisiblePosition == positionStart - 1) {
                    message_list.scrollToPosition(positionStart)
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
        mPresenter.onDestroy()
    }

    fun initPresenter(){
        val aiConfiguration = AIConfiguration("76f19d1283564fc2a13589bb645eb6d8",
            SupportedLanguages.English,
            RecognitionEngine.System)

        aiService = AIService.getService(this, aiConfiguration)
        aiDataAIService = AIDataService(aiConfiguration)
        ref = FirebaseDatabase.getInstance().reference

        mPresenter = ChatbotPresenter(aiDataAIService, ref)

    }
}
