package model

import kotlinx.serialization.Serializable

@Serializable
data class TodosResponse(
	val id: Int? = null,
	val title: String? = null,
	val body: String? = null,
	val userId: Int? = null
)

