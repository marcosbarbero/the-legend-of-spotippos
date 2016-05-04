package org.github.barbero.spotippos.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.github.barbero.spotippos.Application;
import org.github.barbero.spotippos.helper.Given;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Marcos Barbero
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PropertyControllerTest {

    @Rule
    public RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper mapper;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void testGetById() throws Exception {
        this.mockMvc.perform(get("/properties/{id}", 3L).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        pathParameters(
                                parameterWithName("id").description("Long with Property Id representation")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The Id, e.g: 3786").type(NUMBER),
                                fieldWithPath("x").description("The axis x position, e.g: 1137").type(NUMBER),
                                fieldWithPath("y").description("The axis y position, e.g: 875").type(NUMBER),
                                fieldWithPath("beds").description("Number of beds, e.g: 3").type(NUMBER),
                                fieldWithPath("baths").description("Number of baths, e.g: 2").type(NUMBER),
                                fieldWithPath("provinces[]").description("String array of provinces").type(ARRAY),
                                fieldWithPath("squareMeters").description("The square meters, e.g: 187").type(NUMBER)),
                        responseHeaders(headerWithName("Content-Type").description("Content type of the result entity (application/json by default)"))
                ));
    }

    @Test
    public void testGetByNotFoundId() throws Exception {
        this.mockMvc.perform(get("/properties/{id}", -1L).accept(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(document("{method-name}",
                        pathParameters(
                                parameterWithName("id").description("Long with Property Id representation")
                        ),
                        responseFields(
                                fieldWithPath("code").description("The error code").type(STRING),
                                fieldWithPath("message").description("The error message").type(STRING)),
                        responseHeaders(headerWithName("Content-Type").description("Content type of the result entity (application/json by default)"))
                ));
    }

    @Test
    public void testGetByRegion() throws Exception {
        this.mockMvc.perform(get("/properties").param("ax", "0").param("ay", "1000").param("bx", "1400").param("by", "0").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("{method-name}",
                        requestParameters(
                                parameterWithName("ax").description("Upper left position on axis x"),
                                parameterWithName("ay").description("Upper left position on axis y"),
                                parameterWithName("bx").description("Bottom right position on axis x"),
                                parameterWithName("by").description("Bottom right position on axis y")
                        ),
                        responseFields(
                                fieldWithPath("foundProperties").description("The number of properties found").type(NUMBER),
                                fieldWithPath("properties[]").description("Array of properties").type(ARRAY),
                                fieldWithPath("properties[].id").description("The Id, e.g: 3786").type(NUMBER),
                                fieldWithPath("properties[].x").description("The axis x position, e.g: 1137").type(NUMBER),
                                fieldWithPath("properties[].y").description("The axis y position, e.g: 875").type(NUMBER),
                                fieldWithPath("properties[].beds").description("Number of beds, e.g: 3").type(NUMBER),
                                fieldWithPath("properties[].baths").description("Number of baths, e.g: 2").type(NUMBER),
                                fieldWithPath("properties[].squareMeters").description("The square meters, e.g: 187").type(NUMBER)),
                        responseHeaders(headerWithName("Content-Type").description("Content type of the result entity (application/json by default)"))
                ));
    }

    @Test
    public void testGetByRegionNotFound() throws Exception {
        this.mockMvc.perform(get("/properties").param("ax", "-10").param("ay", "10").param("bx", "-400").param("by", "0").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(document("{method-name}",
                        requestParameters(
                                parameterWithName("ax").description("Upper left position on axis x"),
                                parameterWithName("ay").description("Upper left position on axis y"),
                                parameterWithName("bx").description("Bottom right position on axis x"),
                                parameterWithName("by").description("Bottom right position on axis y")
                        ),
                        responseFields(
                                fieldWithPath("code").description("The error code").type(STRING),
                                fieldWithPath("message").description("The error message").type(STRING)),
                        responseHeaders(headerWithName("Content-Type").description("Content type of the result entity (application/json by default)"))
                ));
    }

    @Test
    public void testGetByRegionBadRequest() throws Exception {
        this.mockMvc.perform(get("/properties").param("ax", "-10").param("ay", "10").param("bx", "-400").accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(document("{method-name}",
                        requestParameters(
                                parameterWithName("ax").description("Upper left position on axis x"),
                                parameterWithName("ay").description("Upper left position on axis y"),
                                parameterWithName("bx").description("Bottom right position on axis x")
                        ),
                        responseFields(
                                fieldWithPath("code").description("The error code").type(STRING),
                                fieldWithPath("message").description("The error message").type(STRING)),
                        responseHeaders(headerWithName("Content-Type").description("Content type of the result entity (application/json by default)"))
                ));
    }

    @Test
    public void testPost() throws Exception {
        this.mockMvc.perform(post("/properties")
                .content(mapper.writeValueAsString(Given.validPropertyDTO()))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(document("{method-name}",
                        requestFields(
                                fieldWithPath("x").description("Axis X position"),
                                fieldWithPath("y").description("Axis Y position"),
                                fieldWithPath("beds").description("The number of beds"),
                                fieldWithPath("baths").description("The number of baths"),
                                fieldWithPath("squareMeters").description("The square meters")
                        ),
                        responseHeaders(
                                headerWithName("Location").description("The created resource location")
                        )
                ));
    }

    @Test
    public void testPostUnprocessableEntity() throws Exception {
        this.mockMvc.perform(post("/properties")
                .content("{}")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andDo(document("{method-name}",
                        responseHeaders(
                                headerWithName("Content-Type").description("Content type of the result entity (application/json by default)")
                        )
                ));
    }

    @Test
    public void testPostBadRequest() throws Exception {
        this.mockMvc.perform(post("/properties")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(document("{method-name}",
                        responseHeaders(
                                headerWithName("Content-Type").description("Content type of the result entity (application/json by default)")
                        )
                ));
    }

}


