/**
 * Граф с
 * @param n вершинами,
 * @param m ребрами, где
 * @param a - матрица смежности
 */
class Graph(val n: Int, val m: Int, val a: Array<BooleanArray>, val edges: List<Edge>) {
    companion object {

        const val MAX_N = 20

        fun fromGraph6(code: String): Graph {
            if (code.isNotEmpty()) {
                var el: Int
                var m = 0
                val n: Int = (code[0] - 63).toInt()
                val a = Array(MAX_N) { BooleanArray(MAX_N) }
                val edges = mutableListOf<Edge>()
                var i = 0
                var j = 1
                for (k in 1 until code.length) {
                    el = (code[k] - 63).toInt()
                    for (p in 5 downTo 0) {
                        a[i][j] = (el shr p and 1) != 0
                        a[j][i] = a[i][j]
                        if (a[i][j]) {
                            edges.add(Edge(i, j))
                            m++
                        }
                        i++
                        if (i >= j) {
                            i = 0
                            j++
                        }
                    }
                }
                return Graph(n, m, a, edges)
            } else {
                return Graph(0, 0, emptyArray(), emptyList())
            }
        }
    }

    fun maxDeg(): MaxDeg {
        val maxDeg = a.maxOf { row -> row.count { it } }
        val vertex = a.indexOfFirst { row -> row.count { it } == maxDeg }
        return MaxDeg(maxDeg, vertex)
    }

    /**
     * Получить ребро
     */
    private fun getEdge(i: Int, j: Int): Pair<Int, Int> {
        return if (i > j) j to i else i to j
    }

    /**
     * Смежные ли ребра
     */
    private fun isAdjacentEdge(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        return a.first == b.first || a.first == b.second || a.second == b.first || a.second == b.second
    }
}