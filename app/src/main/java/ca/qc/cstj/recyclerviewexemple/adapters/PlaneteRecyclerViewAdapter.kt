package ca.qc.cstj.recyclerviewexemple.adapters

import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import ca.qc.cstj.recyclerviewexemple.R
import ca.qc.cstj.recyclerviewexemple.models.Planete
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_planete.view.*

class PlaneteRecyclerViewAdapter(private val values: List<Planete>):RecyclerView.Adapter<PlaneteRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener : View.OnClickListener

    init{
        onClickListener = View.OnClickListener {v ->
            val item = v.tag as Planete
            Toast.makeText(v.context, item.nom, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_planete, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        with(holder) {
            view.tag = item
            bind(item)
            view.setOnClickListener(onClickListener)
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        //val lblText : TextView = view.findViewById(android.R.id.text1)
        val imgPlanete : ImageView = view.imgPlanete!!
        val lblPlanete : TextView = view.txtPlanete!!
        val lblRayon : TextView = view.txtRayon!!

        fun bind(planete: Planete) {
            val imageURL = "http://assets.andromia.science/img/planetes/${planete.nom.toLowerCase()}.jpg"
            lblPlanete.text = planete.nom
            lblRayon.text = planete.rayon.toString()
            Picasso.with(imgPlanete.context).load(imageURL).placeholder(R.mipmap.ic_launcher).centerCrop().fit().into(imgPlanete)
        }
    }
}