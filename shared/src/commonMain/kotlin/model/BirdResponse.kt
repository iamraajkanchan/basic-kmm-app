package model

import kotlinx.serialization.Serializable

@Serializable
data class BirdResponse(
	val path: String? = null,
	val author: String? = null,
	val category: String? = null
)

