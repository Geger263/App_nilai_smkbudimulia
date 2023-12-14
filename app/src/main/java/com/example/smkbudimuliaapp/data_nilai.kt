package com.example.smkbudimuliaapp


import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_data_nilai.*

//import android.support.v7.app.AlertDialog


class data_nilai : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_nilai)
    }
    fun saveRecord_nilai(view: View){
        val id = nis.text.toString()
        val harian = harian_id.text.toString()
        val uts = uts_id.text.toString()
        val uas = uas_id.text.toString()
        val databaseHandler_nilai: DatabaseHandler_nilai = DatabaseHandler_nilai(this)
        if(id.trim()!="" && harian.trim()!="" && uts.trim()!="" && uas.trim()!=""){
            val status = databaseHandler_nilai.add_nilai(EmpModelClass_nilai(Integer.parseInt(id),harian, uts, uas))
            if(status > -1){
                Toast.makeText(applicationContext,"data di simpan",Toast.LENGTH_LONG).show()
                nis.text.clear()
                harian_id.text.clear()
                uts_id.text.clear()
                uas_id.text.clear()
            }
        }else{
            Toast.makeText(applicationContext,"Data Nilai harus di isi",Toast.LENGTH_LONG).show()
        }

    }
    //method for read records from database in ListView
    fun viewRecord_nilai(view: View){
        //creating the instance of DatabaseHandler class
        val databaseHandler_nilai: DatabaseHandler_nilai= DatabaseHandler_nilai(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<EmpModelClass_nilai> = databaseHandler_nilai.view_nilai()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayharian = Array<String>(emp.size){"null"}
        val empArrayuts = Array<String>(emp.size){"null"}
        val empArrayuas = Array<String>(emp.size){"null"}
        var index = 0
        for(e in emp){
            empArrayId[index] = e.userId.toString()
            empArrayharian[index] = e.harian
            empArrayuts[index] = e.uts
            empArrayuas[index] = e.uas
            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter_nilai = MyListAdapter_nilai(this,empArrayId,empArrayharian,empArrayuts,empArrayuas)
        listView_nilai.adapter = myListAdapter_nilai
    }
    //method for updating records based on user id
    fun updateRecord_nilai(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog_nilai, null)
        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById(R.id.updateId_nilai) as EditText
        val edtharian = dialogView.findViewById(R.id.update_harian) as EditText
        val edtuts = dialogView.findViewById(R.id.update_uts) as EditText
        val edtuas = dialogView.findViewById(R.id.update_uas) as EditText

        dialogBuilder.setTitle("Edit_Nilai")
        dialogBuilder.setMessage("Enter Nilai below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

            val id_edt = edtId.text.toString()
            val harian_edt = edtharian.text.toString()
            val uts_edt = edtuts.text.toString()
            val uas_edt = edtuas.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler_nilai: DatabaseHandler_nilai= DatabaseHandler_nilai(this)
            if(id_edt.trim()!="" && harian_edt.trim()!="" && uts_edt.trim()!="" && uas_edt.trim()!=""){
                //calling the updateEmployee method of DatabaseHandler class to update record
                val status = databaseHandler_nilai.update_nilai(EmpModelClass_nilai(Integer.parseInt(id_edt),harian_edt, uts_edt, uas_edt))
                if(status > -1){
                    Toast.makeText(applicationContext,"record update",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"data harus di isi dengan benar",Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }
    //method for deleting records based on id
    fun deleteRecord_nilai(view: View){
        //creating AlertDialog for taking user id
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog_nilai, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId_nilai) as EditText
        dialogBuilder.setTitle("Delete Nilai")
        dialogBuilder.setMessage("Enter Data Nilai Below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

            val deleteId = dltId.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler_nilai: DatabaseHandler_nilai= DatabaseHandler_nilai(this)
            if(deleteId.trim()!=""){
                //calling the deleteEmployee method of DatabaseHandler class to delete record
                val status = databaseHandler_nilai.delete_nilai(EmpModelClass_nilai(Integer.parseInt(deleteId),"","",""))
                if(status > -1){
                    Toast.makeText(applicationContext,"Data Nilai telah di hapus",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"Data harus di isi",Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }

}