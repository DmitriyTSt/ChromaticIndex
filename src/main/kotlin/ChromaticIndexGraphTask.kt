import ru.dmitriyt.dcs.core.GraphInvariant

/**
 * Хроматический индекс
 */
class ChromaticIndexGraphTask : GraphInvariant {
    private var color: ThreadLocal<List<MutableList<Int>>> = ThreadLocal.withInitial { emptyList() }
    private val nonUsed = ThreadLocal.withInitial { mutableListOf<Int>() }
    private var noIdx = ThreadLocal.withInitial { 0 }

    override fun solve(graph6: String): Int {
        val graph = Graph.fromGraph6(graph6)
        val m = maxVertexPower(graph)
        return if (graphColor(graph, m)) {
            m
        } else {
            m + 1
        }
    }

    private fun maxVertexPower(graph: Graph): Int {
        var maxColor = 0
        repeat(graph.n) { i ->
            val temp = graph.a[i].count { it }
            if (temp > maxColor) {
                maxColor = temp
            }
        }
        return maxColor
    }

    private fun graphColor(graph: Graph, m: Int): Boolean {
        color.set(List(graph.n) { MutableList(graph.n) { 0 } })
        nonUsed.get().clear()
        val t = MutableList(graph.n) { i -> graph.a[i].count { it } }
        for (i in ((graph.n - 1) downTo 1)) {
            repeat(graph.n) { j ->
                if (t[j] == i) {
                    nonUsed.get().add(j)
                }
            }
        }
        noIdx.set(0)
        val v = nonUsed.get()[noIdx.get()]
        val u = findU(graph, v, -2)
        return mainColor(graph, v, u, m, color.get(), false)
    }

    private fun isSafe(v: Int, u: Int, graph: Graph, color: List<MutableList<Int>>, c: Int): Boolean {
        repeat(graph.n) { i ->
            if ((graph.a[v][i] && c == color[v][i]) || (graph.a[u][i] && c == color[u][i])) {
                return false
            }
        }
        return true
    }

    private fun findU(graph: Graph, v: Int, u: Int): Int {
        repeat(graph.n) {
            if (graph.a[v][it] && it > u) {
                return it
            }
        }
        return -1
    }

    private fun mainColor(graph: Graph, v: Int, _u: Int, m: Int, color: List<MutableList<Int>>, _flag: Boolean): Boolean {
        var flag = _flag
        var u = _u
        if (flag && v == nonUsed.get().first()) {
            return false
        }

        if (color[v][u] != 0) {
            u = findU(graph, v, u)
            var newU = u
            var newV = v
            if (newU == -1) {
                noIdx.set(noIdx.get() + 1)
                if (noIdx.get() == graph.n) {
                    return true
                }

                newV = nonUsed.get()[noIdx.get()]
                newU = findU(graph, newV, newU)
            }
            if (mainColor(graph, newV, newU, m, color, flag)) {
                return true
            }
            if (u == -1) {
                noIdx.set(noIdx.get() - 1)
            }
        } else {
            for (c in 1..m) {
                if (isSafe(v, u, graph, color, c)) {
                    color[v][u] = c
                    color[u][v] = c
                    var newU = findU(graph, v, u)
                    val badU = newU
                    if (newU == -1) {
                        flag = true
                        noIdx.set(noIdx.get() + 1)
                        if (noIdx.get() == graph.n) {
                            return true
                        }
                        val newV = nonUsed.get()[noIdx.get()]
                        newU = findU(graph, newV, newU)
                        if (mainColor(graph, newV, newU, m, color, flag)) {
                            return true
                        }
                        if (badU == -1) {
                            noIdx.set(noIdx.get() - 1)
                        }
                        color[v][u] = 0
                        color[u][v] = 0
                    } else {
                        if (mainColor(graph, v, newU, m, color, flag)) {
                            return true
                        }
                        color[v][u] = 0
                        color[u][v] = 0
                    }
                }
            }
        }
        return false
    }
}