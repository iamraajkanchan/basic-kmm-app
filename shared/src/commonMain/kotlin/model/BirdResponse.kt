package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BirdResponse(
	@SerialName("path")
	val path: String? = null,
	@SerialName("author")
	val author: String? = null,
	@SerialName("category")
	val category: String? = null
)

