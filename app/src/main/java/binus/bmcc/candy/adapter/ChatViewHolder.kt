package binus.bmcc.candy.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import binus.bmcc.candy.R

class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var userText : TextView = itemView.findViewById(R.id.rightText) as TextView
    var botText  : TextView = itemView.findViewById(R.id.leftText) as TextView

}