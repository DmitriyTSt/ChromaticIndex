import ru.dmitriyt.dcs.core.GraphInvariant

/**
 * Хроматический индекс
 */
class ChromaticIndexGraphTask : GraphInvariant {

    override val version = 2

    override fun solve(graph6: String): Int {
        val graph = Graph.fromGraph6(graph6)
        val (delta, maxDegVertex) = graph.maxDeg()
        prepareGraphColoring(graph, maxDegVertex)
        if (isDeltaColored(delta, 0, graph.edges.filter { !it.hasColor() }, graph)) {
            return delta
        }
        return delta + 1
    }

    private fun prepareGraphColoring(graph: Graph, maxDegVertex: Int) {
        graph.edges.filter { it.hasVertex(maxDegVertex) }.forEachIndexed { index, edge ->
            graph.edgesByColor[index + 1] = mutableSetOf(edge)
            edge.color = index + 1
        }
    }

    /**
     * @param delta - максимальная степень вершины
     * @param edgeIndex - текущий индекс ребра (из списка оставшихся после подготовки)
     * @param edges - список неокрашенных ребер после подготовки
     * @param graph - граф
     */
    private fun isDeltaColored(delta: Int, edgeIndex: Int, edges: List<Edge>, graph: Graph): Boolean {
        if (edgeIndex == edges.size) {
            return true
        }
        for (color in 1..delta) {
            if (hasNoNeighboursWithSimilarColor(edges, edgeIndex, color, graph)) {
                setColor(graph, edges, edgeIndex, color)
                if (isDeltaColored(delta, edgeIndex + 1, edges, graph)) {
                    return true
                }
            }
            removeColor(graph, edges, edgeIndex, color)
        }
        return false
    }

    private fun hasNoNeighboursWithSimilarColor(edges: List<Edge>, edgeIndex: Int, color: Int, graph: Graph): Boolean {
        val currentEdge = edges[edgeIndex]
        graph.edgesByColor[color]?.forEach { edge ->
            if (currentEdge.isAdjacent(edge)) {
                return false
            }
        }
        return true
    }

    private fun setColor(graph: Graph, edges: List<Edge>, edgeIndex: Int, color: Int) {
        val edge = edges[edgeIndex]
        edge.color = color
        graph.edgesByColor[color]?.add(edge) ?: run {
            graph.edgesByColor[color] = mutableSetOf(edges[edgeIndex])
        }
    }

    private fun removeColor(graph: Graph, edges: List<Edge>, edgeIndex: Int, color: Int) {
        val edge = edges[edgeIndex]
        graph.edgesByColor[color]?.remove(edge)
        edge.color = Edge.EMPTY_COLOR
    }
}