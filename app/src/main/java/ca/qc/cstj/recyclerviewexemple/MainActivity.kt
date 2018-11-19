package ca.qc.cstj.recyclerviewexemple

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import ca.qc.cstj.recyclerviewexemple.adapters.PlaneteRecyclerViewAdapter
import ca.qc.cstj.recyclerviewexemple.models.Planete
import kotlinx.android.synthetic.main.activity_main.*
import ca.qc.cstj.recyclerviewexemple.helpers.PermissionHelper.checkPermissions

private const val PERMISSION_REQUEST = 10

class MainActivity : AppCompatActivity() {

    private val permissions = arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.READ_SMS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val planetes = listOf<Planete>(
            Planete( "Mercure", 2439),
            Planete("Venus", 6051),
            Planete( "Terre",	6378),
            Planete("Mars",3396),
            Planete("Jupiter",71492),
            Planete("Saturne",60268),
            Planete("Uranus",	25559),
            Planete("Neptune",24764 ),
            Planete("Pluton",1185))

        rcvExemple.layoutManager = LinearLayoutManager(this)
        rcvExemple.adapter = PlaneteRecyclerViewAdapter(planetes)

        btnContacts.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                   if (checkPermissions(permissions)) {
                       //Nous avons les permissions
                       val intent = Intent(this, ContactsActivity::class.java)
                       startActivity(intent)
                   } else {
                       requestPermissions(permissions, PERMISSION_REQUEST)
                   }
            } else {
                //Version avant M, permission dans le manifest
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSION_REQUEST) {
            var allSuccess = true

            for (i in permissions.indices) {
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    allSuccess = false
                    var requestAgain = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                            && shouldShowRequestPermissionRationale(permissions[i])
                    if(requestAgain) {
                        Toast.makeText(this,"Permission non accordée", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this,"Changer les permissions manuellement", Toast.LENGTH_LONG).show()
                    }
                }
            }
            if(allSuccess) {
                val intent = Intent(this, ContactsActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"Nous avons les permissions", Toast.LENGTH_LONG).show()
            }
        }
    }

}
