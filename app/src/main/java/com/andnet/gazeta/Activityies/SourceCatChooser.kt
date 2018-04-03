package com.andnet.gazeta.Activityies

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.andnet.gazeta.Databases.GazetaDatabase
import com.andnet.gazeta.Helper.GridItemDecoration
import com.andnet.gazeta.Helper.MaterialColorPalette
import com.andnet.gazeta.Models.Category
import com.andnet.gazeta.Models.Source
import com.andnet.gazeta.PreferenceUtility
import com.andnet.gazeta.R
import com.andnet.gazeta.ui.Componenet.SmoothCheckBox
import com.andnet.gazeta.ui.RadialProgressView
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_cat_chooser.*
import java.util.*


class SourceCatChooser:AppCompatActivity() {

    var adapter: CatSourceAdapter? = null
    var database: FirebaseDatabase? = null
    var last=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_chooser)

        database = FirebaseDatabase.getInstance()
        adapter =CatSourceAdapter(this)
        rv?.adapter = adapter
        rv?.addItemDecoration(GridItemDecoration(8))
        rv?.layoutManager = GridLayoutManager(this@SourceCatChooser,3)

        addSoruce()
        next?.setOnClickListener {
                 if(adapter?.getSelected()?.size==0){
                     Toast.makeText(this,"Please select at list one source",Toast.LENGTH_SHORT).show()
                     return@setOnClickListener
                  }
                    next.text = "DONE"
                    startDec.text = "Choose categories"
                  if(!last){
                      addCategories()
                  }
            if(last){
                val selectedList=adapter?.getSelected()
                if (selectedList != null) {
                    for(selectedValue in selectedList){
                        if(selectedValue is Category){
                            processSelectedValue(selectedList)
                            return@setOnClickListener
                        }
                    }
                    Toast.makeText(this,"Please select at list one category",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
             last=true

        }

    }

    private fun processSelectedValue(selected: MutableList<Any>) {
        val dao= GazetaDatabase.getDatabase(this).dao()
        val list=adapter?.getAllList()
        for(k in list!!){
            if(k is Source){
                k.isVisibility = selected.contains(k)
                val id=dao.isSoureExist(k.link)
                if(id!=null){
                    k.rowid=id
                    dao.updateSource(k)
                }else{
                    dao.insertSource(k)
                }
            }else if(k is Category){
                k.isVisibility = selected.contains(k)
                val id=dao.isCatExist(k.name)
                if(id!=null){
                    k.rowid=id
                    dao.updateCategory(k)
                }else{
                    dao.insertCategory(k)
                }
        }

        }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
    }


    fun addSoruce(){
        database?.getReference("source")?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataList: DataSnapshot) {
                if(!dataList.exists()) return
                val datas= dataList.children
                val sourceList= ArrayList<Source>()
                for(data in datas){
                    sourceList.add(data.getValue(Source::class.java)!!)
                }
                Collections.sort<Source>(sourceList, Comparator { o1, o2 ->
                    if (o1.pri > o2.pri)
                        -1
                    else
                        1
                })
                adapter?.clearAnyList()
                adapter?.setAnyList(sourceList)
                adapter?.notifyDataSetChanged()
                crossFade()
            }
            override fun onCancelled(error: DatabaseError?) {

            }
        })
    }

    fun addCategories(){
        val tabItems = PreferenceUtility.getTabIconList(this@SourceCatChooser)
        val catagories = ArrayList<Category>()

        for (i in tabItems.indices) {
            catagories.add(Category(tabItems[i].title, tabItems[i].icon, tabItems[i].enabled, i))
        }
        adapter?.clearAnyList()
        adapter?.setAnyList(catagories)
        adapter?.notifyDataSetChanged()
    }

    fun showProgressDialgo() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.progress_alert_dialog, null)
        dialogBuilder.setView(dialogView)
        val text = dialogView.findViewById<View>(R.id.statusText) as TextView
        val progress=dialogView.findViewById<View>(R.id.radialProgress) as RadialProgressView
        text.text = getString(R.string.loading)
        dialogBuilder.setCancelable(false)
        val b = dialogBuilder.create()
        b.show()
    }


    fun crossFade(){
        if(rv?.visibility!= View.VISIBLE){
            rv.alpha=0f
            rv.visibility=View.VISIBLE
            rv.animate().alpha(1f).setDuration(300).setListener(null)
            progressFrame.animate().alpha(0f).setDuration(300).setListener(object:AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                   progressFrame.visibility=View.GONE
                }
            })
        }
    }
    }

    class CatSourceAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var objectList: MutableList<Any>? = ArrayList()
        var selectedObjectList: MutableList<Any> = ArrayList()
        var mList: MutableList<Any>? = ArrayList()

        val sourcetype = 1
        val cattype = 2

        var context: Context? = context

        fun setAnyList(models:List<Any>){
            mList?.addAll(models)
            objectList?.addAll(models)

        }

        fun getAllList(): MutableList<Any>? {
            return mList
        }

        fun clearAnyList(){
            objectList?.clear()
            notifyDataSetChanged()
        }

        fun getSelected(): MutableList<Any> {
            return selectedObjectList
        }

        override fun getItemViewType(position: Int): Int {
            val o = objectList?.get(position)
            if(o is Source)return sourcetype
            if(o is Category)return  cattype
            return -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when(viewType){
                sourcetype-> SourceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.source_list_item,parent,false))
                cattype-> CatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cat_list_item, parent, false))
                else-> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.header_item, parent, false))
            }
        }

        override fun getItemCount(): Int {
            return if (objectList != null) objectList!!.size else 0
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val o = objectList?.get(position)
            if (getItemViewType(position) == sourcetype && o is Source) {
                if (o.isEnabled) {
                    o.isEnabled = false
                    selectedObjectList.add(o)
                }
                val sourceviewholder = holder as SourceViewHolder
                sourceviewholder.image.setImageURI(o.image)
                sourceviewholder.name.text=o.name

                val roundingParams = RoundingParams.fromCornersRadius(5f)
                roundingParams.setBorder(ContextCompat.getColor(this.context!!,R.color.colorPrimary), 3.0f)
                roundingParams.roundAsCircle = true
                sourceviewholder.image.hierarchy.roundingParams = roundingParams

                if (selectedObjectList.contains(o)) {
                    sourceviewholder.check.setChecked(true, false)
                } else {
                    sourceviewholder.check.setChecked(false, false)

                }
                sourceviewholder.view.setOnClickListener({ v ->
                    if (selectedObjectList.contains(o)) {
                        selectedObjectList.remove(o)
                        sourceviewholder.check.setChecked(false, true)
                    } else {
                        selectedObjectList.add(o)
                        sourceviewholder.check.setChecked(true, true)
                    }
                })
            } else if (getItemViewType(position) == cattype && o is Category) {
                if (o.isEnabled) {
                    o.isEnabled = false
                    selectedObjectList.add(o)
                }
                val catviewholder = holder as CatViewHolder
                catviewholder.image.setImageResource(o.res)
                catviewholder.name.text = o.name
                if (selectedObjectList.contains(o)) {
                    catviewholder.check.setChecked(true, false)
                } else {
                    catviewholder.check.setChecked(false, false)

                }
                catviewholder.view.setOnClickListener({ v ->
                    if (selectedObjectList.contains(o)) {
                        selectedObjectList.remove(o)
                        catviewholder.check.setChecked(false, true)
                    } else {
                        selectedObjectList.add(o)
                        catviewholder.check.setChecked(true, true)
                    }
                })

                val drawable = catviewholder.image.background
                drawable?.setColorFilter(MaterialColorPalette.getRandomColor("500"), PorterDuff.Mode.SRC_IN)

            }
        }
        inner   class SourceViewHolder(internal var view: View) : RecyclerView.ViewHolder(view) {
            internal var image: SimpleDraweeView
            internal var name: TextView
            internal var check: SmoothCheckBox
            init {
                image = view.findViewById(R.id.image)
                name = view.findViewById(R.id.name)
                check = view.findViewById(R.id.check)
            }
        }
        inner class CatViewHolder(internal var view:View):RecyclerView.ViewHolder(view){
            internal var image:ImageView
            internal var name:TextView
            internal var check:SmoothCheckBox
            init {
                image=view.findViewById(R.id.image)
                name=view.findViewById(R.id.name)
                check=view.findViewById(R.id.check)
            }
        }
        inner class HeaderViewHolder(internal var view:View):RecyclerView.ViewHolder(view){
            internal var name:TextView
            init {
                name=view.findViewById(R.id.header)
            }
        }

    }






