import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PaletteIndexGraphTaskTest {
    @Test
    fun `Palette index of D?? should be 1`() {
        val graph6 = "D??"
        val chromaticIndex = PaletteIndexGraphTask().solve(graph6)
        Assertions.assertEquals(1, chromaticIndex)
    }
    @Test
    fun `Palette index of D^{ should be 4`() {
        val graph6 = "D^{"
        val chromaticIndex = PaletteIndexGraphTask().solve(graph6)
        Assertions.assertEquals(4, chromaticIndex)
    }
    @Test
    fun `Palette index of D?_ should be 2`() {
        val graph6 = "D?_"
        val chromaticIndex = PaletteIndexGraphTask().solve(graph6)
        Assertions.assertEquals(2, chromaticIndex)
    }
    @Test
    fun `Palette index of DCO should be 2`() {
        val graph6 = "DCO"
        val chromaticIndex = PaletteIndexGraphTask().solve(graph6)
        Assertions.assertEquals(2, chromaticIndex)
    }
    @Test
    fun `Palette index of DCo should be 3`() {
        val graph6 = "DCo"
        val chromaticIndex = PaletteIndexGraphTask().solve(graph6)
        Assertions.assertEquals(3, chromaticIndex)
    }
    @Test
    fun `Palette index of DCW should be 3`() {
        val graph6 = "DCW"
        val chromaticIndex = PaletteIndexGraphTask().solve(graph6)
        Assertions.assertEquals(3, chromaticIndex)
    }
}