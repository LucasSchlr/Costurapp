package com.example.costurapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.costurapp.R
import com.example.costurapp.model.DatabaseListaMaterial
import com.example.costurapp.presenter.ListaCadastroPresenter
import kotlinx.android.synthetic.main.activity_material_lista.*


class MaterialListaActivity : AppCompatActivity(), ListaCadastroPresenter.viewListaCadastro {
    var presenter:ListaCadastroPresenter? = null
    private var activityMenu:Menu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_lista)
        setSupportActionBar(toolbar as Toolbar?)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = ListaCadastroPresenter(this, DatabaseListaMaterial(), MaterialActivity::class)
        presenter?.configuraAdapter()

        lista_materiais.setOnItemClickListener {_, view, _, id -> presenter?.onListItemClick(view, id)}

        lista_materiais.setOnItemLongClickListener(OnItemLongClickListener { _, view, pos, id ->
            presenter!!.processarSelecao(view, id)
            true
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista, menu)
        activityMenu = menu
        menu?.findItem(R.id.action_delete_selected_items)?.setVisible(false)
        return super.onCreateOptionsMenu(menu)
    }

    fun novoMaterialOnClick(view: View){
        val intent =
            Intent(this@MaterialListaActivity, MaterialActivity::class.java)
                .putExtra("CADASTRO_ID", -1.toLong())
        startActivityForResult(intent, ListaCadastroPresenter.REQUEST_ABRE_DETALHE)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_delete_selected_items->{
                presenter!!.onClickDelete()
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ListaCadastroPresenter.REQUEST_ABRE_DETALHE){
            presenter!!.refreshData()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun contexto(): Context {
        return this
    }

    override fun menu(): Menu? {
        return activityMenu
    }

    override fun lista(): ListView{
        return lista_materiais
    }
}