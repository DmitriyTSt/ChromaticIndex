/**
 * Граф с
 * @param n вершинами, где
 * @param a - матрица смежности
 */
class Graph(val n: Int, val a: Array<BooleanArray>) {
    companion object {

        const val MAX_N = 20

        fun fromGraph6(code: String): Graph {
            if (code.isNotEmpty()) {
                var el: Int
                val n: Int = (code[0] - 63).toInt()
                val a = Array(MAX_N) { BooleanArray(MAX_N) }
                var i = 0
                var j = 1
                for (k in 1 until code.length) {
                    el = (code[k] - 63).toInt()
                    for (p in 5 downTo 0) {
                        a[i][j] = (el shr p and 1) != 0
                        a[j][i] = a[i][j]
                        i++
                        if (i >= j) {
                            i = 0
                            j++
                        }
                    }
                }
                return Graph(n, a)
            } else {
                return Graph(0, emptyArray())
            }
        }
    }

    fun vte(): Graph {
        val edges = hashSetOf<Pair<Int, Int>>()
        repeat(n) { i ->
            repeat(n) { j ->
                if (a[i][j]) {
                    edges.add(getEdge(i, j))
                }
            }
        }
        val newN = edges.size
        val newA = MutableList(newN) { MutableList(newN) { false } }
        val edgesList = edges.toList()
        repeat(newN) { i ->
            repeat(newN) { j ->
                if (isAdjacentEdge(edgesList[i], edgesList[j])) {
                    newA[i][j] = true
                }
            }
        }
        return Graph(newN, newA.map { it.toBooleanArray() }.toTypedArray())
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