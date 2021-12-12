import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ChromaticIndexGraphTaskTest {
    @Test
    fun `Chromatic index of FC~~w should be 6`() {
        val graph6 = "D?{"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(4, chromaticIndex)
    }

    @Test
    fun `Chromatic index of FCZNw should be 6`() {
        val graph6 = "D?{"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(4, chromaticIndex)
    }

    @Test
    fun `Chromatic index of EEhw should be 4`() {
        val graph6 = "EEhw"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(4, chromaticIndex)
    }

    @Test
    fun `Chromatic index of EUZo should be 4`() {
        val graph6 = "EEhw"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(4, chromaticIndex)
    }

    @Test
    fun `Chromatic index of FCxv? should be 4`() {
        val graph6 = "FCxv?"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(4, chromaticIndex)
    }
}