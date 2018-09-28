package org.mary.pro.test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mary.pro.config.ApplicationContextConfig;
import org.mary.pro.cotroller.UserController;
import org.mary.pro.model.User;
import org.mary.pro.service.UserDAO;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class,
        classes = {ApplicationContextConfig.class})

public class UserControllerTest {


    private MockMvc mock;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserDAO userDAO;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mock = MockMvcBuilders.standaloneSetup(userController).build();
    }


    User user = new User("Ivan", "Ivanov", "manager");

    String exampleJson = "{\"name\":\"Ivan\",\"lastName\":\"Ivanov\",\"position\":\"manager\"}";


    @Test
    public void getAllUsersTest() throws Exception{

        mock.perform(get("/users").accept(
                MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType
                        (MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void getUserTest() throws Exception {
        when(
                userDAO.getUser(Mockito.anyString())).thenReturn(user);
        RequestBuilder requestBuilder = get(
                "/user/lastName").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mock.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        String expected = "{name:Ivan,lastName:Ivanov,position:manager}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);

    }

    @Test
    public void deleteUserTest() throws Exception {
        this.mock.perform(MockMvcRequestBuilders
                .delete("/users/lastName")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addUserTest() throws Exception {
        when(userDAO.addUser(Mockito.any(User.class))).thenReturn(user);
        mock.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(user))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType
                        (MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void updateUserTest() throws Exception{
        User user1 = new User("1", "2", "3");
        when(userDAO.updateUser(Mockito.any(User.class))).thenReturn(user1);
        mock.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(user1))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType
                        (MediaType.APPLICATION_JSON_UTF8_VALUE));

    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}












