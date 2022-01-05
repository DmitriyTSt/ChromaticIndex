import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ChromaticIndexGraphTaskTest {
    @Test
    fun `Chromatic index of DFw should be 3`() {
        val graph6 = "DFw"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(3, chromaticIndex)
    }

    @Test
    fun `Chromatic index of FC~~w should be 6`() {
        val graph6 = "FC~~w"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(6, chromaticIndex)
    }

    @Test
    fun `Chromatic index of FCZNw should be 6`() {
        val graph6 = "FCZNw"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(6, chromaticIndex)
    }

    @Test
    fun `Chromatic index of EEhw should be 4`() {
        val graph6 = "EEhw"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(4, chromaticIndex)
    }

    @Test
    fun `Chromatic index of EUZo should be 4`() {
        val graph6 = "EUZo"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(4, chromaticIndex)
    }

    @Test
    fun `Chromatic index of FCxv? should be 4`() {
        val graph6 = "FCxv?"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(4, chromaticIndex)
    }

    @Test
    fun `Chromatic index of DC{ should be 4`() {
        val graph6 = "DC{"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(4, chromaticIndex)
    }

    @Test
    fun `Chromatic index of D^{ should be 5`() {
        val graph6 = "D^{"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(5, chromaticIndex)
    }

    @Test
    fun `Chromatic index of ETzg should be 4`() {
        val graph6 = "ETzg"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(4, chromaticIndex)
    }

    @Test
    fun `Chromatic index of EEnw should be 5`() {
        val graph6 = "EEnw"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(5, chromaticIndex)
    }

//    @Test
//    fun `Chromatic index of FErvw should be 5`() {
//        val graph6 = "FErvw"
//        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
//        Assertions.assertEquals(5, chromaticIndex)
//    }

    @Test
    fun `Chromatic index of FV~~w should be 7`() {
        val graph6 = "FV~~w"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(7, chromaticIndex)
    }

    @Test
    fun `Chromatic index of GEnvQ{ should be 5`() {
        val graph6 = "GEnvQ{"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(5, chromaticIndex)
    }

//    @Test
//    fun `Chromatic index of GFzvvW should be 6`() {
//        val graph6 = "GFzvvW"
//        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
//        Assertions.assertEquals(6, chromaticIndex)
//    }

    @Test
    fun `Chromatic index of HTm||}~ should be 7`() {
        val graph6 = "HTm||}~"
        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
        Assertions.assertEquals(7, chromaticIndex)
    }

//    @Test
//    fun `Chromatic index of HF~~~~~ should be 9`() {
//        val graph6 = "HF~~~~~"
//        val chromaticIndex = ChromaticIndexGraphTask().solve(graph6)
//        Assertions.assertEquals(9, chromaticIndex)
//    }
}