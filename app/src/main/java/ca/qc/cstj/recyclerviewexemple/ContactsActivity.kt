package ca.qc.cstj.recyclerviewexemple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.LinearLayoutManager
import ca.qc.cstj.recyclerviewexemple.adapters.ContactRecyclerViewAdapter
import ca.qc.cstj.recyclerviewexemple.models.Contact
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_main.*

class ContactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        val contacts = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,null, null)

        val contactDuTelephone: MutableList<Contact> = ArrayList()

        while(contacts.moveToNext()) {
            val nomContact = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val telephoneContact = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val imageContact = contacts.getString(contacts.getColumnIndex((ContactsContract.CommonDataKinds.Phone.PHOTO_URI)))

            contactDuTelephone.add(Contact(nomContact, telephoneContact, imageContact))
        }

        rcvContacts.layoutManager = LinearLayoutManager(this)
        rcvContacts.adapter = ContactRecyclerViewAdapter(contactDuTelephone)
    }
}
