import ru.dmitriyt.dcs.core.GraphInvariant

/**
 * Хроматический индекс
 */
class PaletteIndexGraphTask : GraphInvariant {
    private var minPaletteIndex = Int.MAX_VALUE

    override fun solve(graph6: String): Int {
        val graph = Graph.fromGraph6(graph6)
        val (delta, maxDegVertex) = graph.maxDeg()
        prepareGraphColoring(graph, maxDegVertex)
        isDeltaColored(delta, 0, graph.edges.filter { !it.hasColor() }, graph)
        return minPaletteIndex
    }

    private fun prepareGraphColoring(graph: Graph, maxDegVertex: Int) {
        graph.edges.filter { it.hasVertex(maxDegVertex) }.forEachIndexed { index, edge ->
            edge.color = index + 1
        }
    }

    /**
     * @param delta - максимальная степень вершины
     * @param edgeIndex - текущий индекс ребра (из списка оставшихся после подготовки)
     * @param edges - список неокрашенных ребер после подготовки
     * @param graph - граф
     */
    private fun isDeltaColored(delta: Int, edgeIndex: Int, edges: List<Edge>, graph: Graph): DeltaColoredResult {
        if (edgeIndex == edges.size) {
            // правильная расскраска, посчитаем индекс палитры
            val currentPaletteIndex = paletteIndex(graph)
            if (currentPaletteIndex < minPaletteIndex) {
                minPaletteIndex = currentPaletteIndex
            }
            return DeltaColoredResult(isDeltaColored = true, runAlgo = true)
        }
        var isDeltaColored = false
        for (color in 1..delta) {
            if (hasNoNeighboursWithSimilarColor(edges, edgeIndex, color, graph)) {
                setColor(edges, edgeIndex, color)
                val result = isDeltaColored(delta, edgeIndex + 1, edges, graph)
                if (result.isDeltaColored) {
                    isDeltaColored = true
                }
                if (!result.runAlgo) {
                    return result
                }
            }
            removeColor(edges, edgeIndex)
        }
        return DeltaColoredResult(
            isDeltaColored = isDeltaColored,
            runAlgo = edgeIndex != 0,
        )
    }

    private fun hasNoNeighboursWithSimilarColor(edges: List<Edge>, edgeIndex: Int, color: Int, graph: Graph): Boolean {
        val currentEdge = edges[edgeIndex]
        for (edge in graph.edges.filter { it.color == color }) {
            if (currentEdge.isAdjacent(edge)) {
                return false
            }
        }
        return true
    }

    class DeltaColoredResult(
        val isDeltaColored: Boolean,
        val runAlgo: Boolean,
    )

    private fun setColor(edges: List<Edge>, edgeIndex: Int, color: Int) {
        edges[edgeIndex].color = color
    }

    private fun removeColor(edges: List<Edge>, edgeIndex: Int) {
        edges[edgeIndex].color = Edge.EMPTY_COLOR
    }

    private fun palette(graph: Graph, vertex: Int): Set<Int> {
        return graph.edges.filter { it.hasVertex(vertex) }.map { it.color }.toSet()
    }

    private fun paletteIndex(graph: Graph): Int {
        return IntRange(0, graph.n - 1).map { palette(graph, it) }.toSet().count()
    }
}