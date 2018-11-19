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
import ca.qc.cstj.recyclerviewexemple.models.Contact
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_contact.view.*

class ContactRecyclerViewAdapter(private val values: List<Contact>):RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_contact, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = values[position]
        holder.bind(contact)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgContact = view.imgContact!!
        val lblNom = view.txtNom!!
        val lblTelephone = view.txtTelephone!!

        fun bind(contact: Contact) {
            //imgContact.setImageURI(contact.photo.)
            lblNom.text = contact.nom
            lblTelephone.text = contact.telephone
            Picasso.with(imgContact.context).load(contact.image).into(imgContact)
        }
    }
}