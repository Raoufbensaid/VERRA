package fr.raouf.verra.repositories

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.raouf.verra.models.ArticleChildModel
import fr.raouf.verra.repositories.ArticleChildRepository.Singleton.articleChildList
import fr.raouf.verra.repositories.ArticleChildRepository.Singleton.databaseRef

class ArticleChildRepository {
    object Singleton {
        val databaseRef = FirebaseDatabase.getInstance().getReference("articleChild")
        val articleChildList = arrayListOf<ArticleChildModel>()
    }

    fun updateDataDetails(callback: (Boolean) -> Unit) {
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                articleChildList.clear()

                for (dsc in snapshot.children){
                    val articleChild = dsc.getValue(ArticleChildModel::class.java)

                    if (articleChild != null) {
                        articleChildList.add(articleChild)
                    }
                }
                callback(true)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(false)
                Log.e("ArticleChildRepository", "Erreur lors de la lecture depuis la base de données : ${error.message}")
            }

        })
    }

    fun updateArticleChild(articleChild: ArticleChildModel) = databaseRef.child(articleChild.id).setValue(articleChild)
}