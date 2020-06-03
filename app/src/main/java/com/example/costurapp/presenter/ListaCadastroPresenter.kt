package com.example.costurapp.presenter

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.Menu
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.cursoradapter.widget.SimpleCursorAdapter
import com.example.costurapp.R
import com.example.costurapp.model.classes.Material
import kotlinx.coroutines.*
import kotlin.reflect.KClass


class ListaCadastroPresenter(
    val viewLista: viewListaCadastro,
    val model: modelListaCadastro,
    val detailClass: KClass<*>
) {

    val uiScope = CoroutineScope(Dispatchers.Default)

    var listaLinhasSelecionadas = mutableListOf<View>()
    var listaObjetosSelecionados = mutableListOf<Int>()
    var selecionando = false

    fun configuraAdapter() {
        uiScope.launch {
            //Log.i("teste", "")
            val contexto = viewLista.contexto()
            val cursor = CoroutineScope(Dispatchers.Default).async { model.cursorLista(contexto)}

            withContext(Dispatchers.Main) {
                viewLista.lista().adapter = SimpleCursorAdapter(
                    contexto,
                    R.layout.database_list,
                    cursor.await(),
                    arrayOf(model.campoDescritivo),
                    intArrayOf(R.id.texto_unico_um),
                    0
                )
            }
        }
    }

    fun onListItemClick(view: View, id:Long){
        if (selecionando){
            processarSelecao(view, id)
        }else{
            (viewLista.contexto() as AppCompatActivity).startActivityForResult(
                Intent(viewLista.contexto(), detailClass.java)
                    .putExtra(CADASTRO_ID, id),
                REQUEST_ABRE_DETALHE
            )
        }
    }

    fun onClickDelete(){
        uiScope.launch {
            val concluido = CoroutineScope(Dispatchers.Default).async { model.updateDeleteItems(viewLista.contexto(), listaObjetosSelecionados)}
            if (concluido.await()){
                refreshData()
            }
        }
    }

    fun refreshData(){
        uiScope.launch {
            val contexto = viewLista.contexto()
            val newCursor = CoroutineScope(Dispatchers.Default).async { model.cursorLista(contexto)}
            val oldCursor = (viewLista.lista().adapter as SimpleCursorAdapter).cursor

            withContext(Dispatchers.Main) {
                (viewLista.lista().adapter as SimpleCursorAdapter).changeCursor(newCursor.await())
                oldCursor.close()
            }
        }
    }

    fun processarSelecao(view: View, id: Long) {
        if (listaLinhasSelecionadas.contains(view)) {
            listaLinhasSelecionadas.remove(view)
            listaObjetosSelecionados.remove(id.toInt())
            view.setBackgroundResource(R.color.design_default_color_background)
        } else {
            listaLinhasSelecionadas.add(view)
            listaObjetosSelecionados.add(id.toInt())
            view.setBackgroundResource(R.color.colorSelectedItem  )
        }
        selecionando = listaLinhasSelecionadas.count() > 0
        viewLista.menu()?.findItem(R.id.action_delete_selected_items)?.setVisible( selecionando)
    }

    interface modelListaCadastro {
        val campoDescritivo:String
        suspend fun cursorLista(context:Context):Cursor
        suspend fun updateDeleteItems(context: Context, idsToUpdate:List<Int>):Boolean
    }

    interface viewListaCadastro {
        fun contexto():Context
        fun menu():Menu?
        fun lista():ListView
    }

    companion object{
        val CADASTRO_ID = "CADASTRO_ID"
        val REQUEST_ABRE_DETALHE = 1
    }
}