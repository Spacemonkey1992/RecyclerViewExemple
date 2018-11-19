package ca.qc.cstj.recyclerviewexemple.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class StringRecyclerViewAdapter(private val values: List<String>):RecyclerView.Adapter<StringRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener : View.OnClickListener

    init{
        onClickListener = View.OnClickListener {v ->
            val item = v.tag as String
            Toast.makeText(v.context, item, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        with(holder) {
            view.tag = item
            lblText.text = item
            view.setOnClickListener(onClickListener)
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val lblText : TextView = view.findViewById(android.R.id.text1)
    }
}