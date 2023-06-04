import com.example.demo.DemoApplication
import com.jayway.jsonpath.JsonPath
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(classes = [DemoApplication::class])
@AutoConfigureMockMvc
class ContactControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc
    var id:String = ""

    @Test
    fun `should create a new contact`() {
        val contactJson = """{"name": "John Doe", "description": "Test contact"}"""
        mockMvc.perform(post("/contactos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(contactJson))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value("John Doe"))
            .andExpect(jsonPath("$.description").value("Test contact"))
            .andDo {mvcResult -> this.id = JsonPath.parse(mvcResult.response.getContentAsString()).read("id")}

        mockMvc.perform(get("/contactos"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.[0].id").exists())
            .andExpect(jsonPath("$.[0].name").value("John Doe"))
            .andExpect(jsonPath("$.[0].description").value("Test contact"))

        val contactJsonToUpdate = """{"id": "${this.id}", "name": "Jane Doe", "description": "Updated contact"}"""
        mockMvc.perform(put("/contactos/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(contactJsonToUpdate))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value("Jane Doe"))
            .andExpect(jsonPath("$.description").value("Updated contact"))

        mockMvc.perform(delete("/contactos/{id}", 1))
            .andExpect(status().isOk)
    }
}
