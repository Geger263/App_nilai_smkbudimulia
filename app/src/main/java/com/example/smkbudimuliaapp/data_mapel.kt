package com.example.smkbudimuliaapp

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_data_mapel.*



class data_mapel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_mapel)
    }
    fun saveRecord_mapel(view: View){
        val id = mapel_id.text.toString()
        val name = mapel_name.text.toString()
        val email = nama_guru.text.toString()
        val databaseHandler_mapel: DatabaseHandler_mapel = DatabaseHandler_mapel(this)
        if(id.trim()!="" && name.trim()!="" && email.trim()!=""){
            val status = databaseHandler_mapel.addEmployee_mapel(EmpModelClass_mapel(Integer.parseInt(id),name, email))
            if(status > -1){
                Toast.makeText(applicationContext,"data di simpan",Toast.LENGTH_LONG).show()
                mapel_id.text.clear()
                mapel_id.text.clear()
                nama_guru.text.clear()
            }
        }else{
            Toast.makeText(applicationContext,"Data Mapel harus di isi",Toast.LENGTH_LONG).show()
        }

    }
    //method for read records from database in ListView
    fun viewRecord_mapel(view: View){
        //creating the instance of DatabaseHandler class
        val databaseHandler_mapel: DatabaseHandler_mapel= DatabaseHandler_mapel(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<EmpModelClass_mapel> = databaseHandler_mapel.viewEmployee_mapel()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayName = Array<String>(emp.size){"null"}
        val empArrayEmail = Array<String>(emp.size){"null"}
        var index = 0
        for(e in emp){
            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.userName
            empArrayEmail[index] = e.userEmail
            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter_mapel = MyListAdapter_mapel(this,empArrayId,empArrayName,empArrayEmail)
        listView_mapel.adapter = myListAdapter_mapel
    }
    //method for updating records based on user id
    fun updateRecord_mapel(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog_mapel, null)
        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById(R.id.updateId_mapel) as EditText
        val edtName = dialogView.findViewById(R.id.updateName_mapel) as EditText
        val edtEmail = dialogView.findViewById(R.id.updateguru_mapel) as EditText

        dialogBuilder.setTitle("Edit_Mapel")
        dialogBuilder.setMessage("Enter Nip below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

            val updateId = edtId.text.toString()
            val updateName = edtName.text.toString()
            val updateEmail = edtEmail.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler_mapel: DatabaseHandler_mapel= DatabaseHandler_mapel(this)
            if(updateId.trim()!="" && updateName.trim()!="" && updateEmail.trim()!=""){
                //calling the updateEmployee method of DatabaseHandler class to update record
                val status = databaseHandler_mapel.updateEmployee_mapel(EmpModelClass_mapel(Integer.parseInt(updateId),updateName, updateEmail))
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
    fun deleteRecord_mapel(view: View){
        //creating AlertDialog for taking user id
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog_mapel, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId_mapel) as EditText
        dialogBuilder.setTitle("Delete mapel")
        dialogBuilder.setMessage("Enter Kode Mapel Below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

            val deleteId = dltId.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler_mapel: DatabaseHandler_mapel= DatabaseHandler_mapel(this)
            if(deleteId.trim()!=""){
                //calling the deleteEmployee method of DatabaseHandler class to delete record
                val status = databaseHandler_mapel.deleteEmployee_mapel(EmpModelClass_mapel(Integer.parseInt(deleteId),"",""))
                if(status > -1){
                    Toast.makeText(applicationContext,"Data Mapel telah di hapus",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"Kode Mapel harus di isi",Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }

}