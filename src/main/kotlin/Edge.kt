data class Edge(var v1: Int, var v2: Int, var color: Int = EMPTY_COLOR) {

    companion object {
        const val EMPTY_COLOR = -1
    }

    fun isAdjacent(edge: Edge?): Boolean {
        return if (edge == null) {
            false
        } else v1 == edge.v1 || v1 == edge.v2 || v2 == edge.v1 || v2 == edge.v2
    }

    fun hasVertex(vertex: Int): Boolean {
        return v1 == vertex || v2 == vertex
    }

    fun hasColor(): Boolean {
        return color > EMPTY_COLOR
    }
}