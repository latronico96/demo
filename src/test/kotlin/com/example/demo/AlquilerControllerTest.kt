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
import java.util.*

@SpringBootTest(classes = [DemoApplication::class])
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AlquilerControllerTest {
    var contacto: String = ""
    var contactoId: String = ""
    var contactoName: String = ""
    var contactoDescription: String = ""

    var inflable: String = ""
    var inflableId: String = ""
    var inflableName: String = ""

    var alquiler = ""
    var alquilerId: String = ""


    @Autowired
    lateinit var mockMvc: MockMvc


    @Test
    fun `should create a new contact`() {
        this.inflableName = "Pelotero inflable"
        val inflableJson = """{"name": "Pelotero inflable"}"""
        mockMvc.perform(
            post("/inflables")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inflableJson)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value(this.inflableName))
            .andDo { mvcResult ->
                this.inflable = mvcResult.response.getContentAsString()
                this.inflableId = JsonPath.parse(this.inflable).read("id")
            }

        this.contactoName = "John Doe"
        this.contactoDescription = "Test contact"

        val contactJson = """{"name": "${this.contactoName}", "description": "${this.contactoDescription}"}"""
        mockMvc.perform(
            post("/contactos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactJson)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value(this.contactoName))
            .andExpect(jsonPath("$.description").value(this.contactoDescription))
            .andDo {mvcResult ->
                this.contacto = mvcResult.response.getContentAsString()
                this.contactoId = JsonPath.parse(this.contacto).read("id")
            }


        val alquilerJson = """{"contacto": ${this.contacto}, "inflable": ${this.inflable}}"""

        mockMvc.perform(post("/alquileres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(alquilerJson))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.contacto.id").value(this.contactoId))
            .andExpect(jsonPath("$.contacto.name").value(this.contactoName))
            .andExpect(jsonPath("$.contacto.description").value(this.contactoDescription))
            .andExpect(jsonPath("$.inflable.id").value(this.inflableId))
            .andExpect(jsonPath("$.inflable.name").value(this.inflableName))
            .andDo {mvcResult ->
                this.alquiler = mvcResult.response.getContentAsString()
                this.alquilerId = JsonPath.parse(this.alquiler).read("id")
            }


        mockMvc.perform(get("/alquileres"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.[0].id").exists())
            .andExpect(jsonPath("$.[0].contacto.id").value(this.contactoId))
            .andExpect(jsonPath("$.[0].inflable.id").value(this.inflableId))


        val alquilerJson2 = """{"id": "${this.alquilerId}", "contacto": ${this.contacto}, "inflable": ${this.inflable}"}"""
        mockMvc.perform(put("/alquileres/{id}", this.alquilerId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(alquilerJson2))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(alquilerId))

        mockMvc.perform(delete("/inflables/{id}", this.alquilerId))
            .andExpect(status().isOk)
    }
}
