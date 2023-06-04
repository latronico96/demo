import com.example.demo.DemoApplication
import com.jayway.jsonpath.JsonPath
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(classes = [DemoApplication::class])
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InflableControllerTest {
    var id: String = ""

    @Autowired
    lateinit var mockMvc: MockMvc


    @Test
    fun `should create a new contact`() {

        val contactJson = """{"name": "Pelotero inflable"}"""
        mockMvc.perform(post("/inflables")
            .contentType(MediaType.APPLICATION_JSON)
            .content(contactJson))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value( "Pelotero inflable"))
            .andDo {mvcResult -> this.id = JsonPath.parse(mvcResult.response.getContentAsString()).read("id")}

        mockMvc.perform(get("/inflables"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.[0].id").exists())
            .andExpect(jsonPath("$.[0].name").value("Pelotero inflable"))

        val contactJson2 = """{"id": "${this.id}", "name": "Pelotero inflable2"}"""
        mockMvc.perform(put("/inflables/{id}", this.id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(contactJson2))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value("Pelotero inflable2"))

        mockMvc.perform(delete("/inflables/{id}", this.id))
            .andExpect(status().isOk)
    }
}
