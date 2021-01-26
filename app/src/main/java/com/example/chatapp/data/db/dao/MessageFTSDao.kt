package com.example.chatapp.data.db.dao

import androidx.room.Dao
import androidx.room.Query
@Dao
interface MessageFTSDao {

    @Query(
        """
        SELECT * FROM messages_fts
        WHERE messages_fts MATCH :query
                """
    )//Todo("""if this shows Type mismatch: inferred type is List<MessageFTS> but List<Message>! was expected  the you have 3 option one to change the Datatype in List or other is Use join or use the no. of columns you want instead of * (SELECT * FROM) to what u want""")
    suspend fun search(query: String): List<MessageDao>
/*
    @Query("""
  SELECT *, matchinfo(messages_fts) as matchInfo
  FROM messages
  JOIN messages_fts ON messages.data = messages_fts.data
  WHERE messages_fts MATCH :query
""")
    suspend fun searchWithMatchInfo(query: String): List<LaunchWithMatchInfo>*/
}

/*
fun search(query: Editable?) {
  viewModelScope.launch {
    if (query.isNullOrBlank()) {
      launchDao.all().let {
        _searchResults.postValue(it)
      }
    } else {
      val sanitizedQuery = sanitizeSearchQuery(query)
      launchDao.search(sanitizedQuery).let {
        _searchResults.postValue(it)
      }
    }
  }
}



private fun sanitizeSearchQuery(query: Editable?): String {
  if (query == null) {
    return "";
  }
  val queryWithEscapedQuotes = query.replace(Regex.fromLiteral("\""), "\"\"")
  return "*\"$queryWithEscapedQuotes\"*"
}
*/