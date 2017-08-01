package kicks.softcreative.assured;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertNotNull;

import org.junit.Assert;
import org.junit.Test;


public class MainTest {


    /**
     * Test for the construction of Main and the 
     * main method being called
     *
     */
    @Test
    public void shouldCreateMainObject(){
        Main main = new Main();
        assertNotNull("Main method called.", main);
    }

      @Test
    public void aUserGets404UsingRestAssured(){

        given().
                contentType("text/xml").
                when().
                get("http://localhost:80/todos.xml").
                then().
                statusCode(404);

    }      @Test
    public void a_User_Gets_404_Using_Rest_Assured(){

        given().
                contentType("text/xml").
                expect().
                statusCode(404).
                when().
                get("http://localhost:80/todos.xml");

    }


//    More structured test
@Test
    public void aUserCanDeleteAProject(){

        TracksApi api = new TracksApi(TestEnvDefaults.getTestEnv());

        api.createProject("A New Project" +
                new RandomDataGenerator().randomWord());
        Assert.assertEquals(201,api.getLastResponse().getStatusCode());

    String projectId = new TracksResponseProcessor(
            api.getLastResponse())
            .getIdFromLocation();

    // check we can get it
    api.getProject(projectId);
    Assert.assertEquals(200,            api.getLastResponse().getStatusCode());

    // check we can delete it
    api.deleteProject(projectId);
    Assert.assertEquals(200,
            api.getLastResponse().getStatusCode());

    // check it has been deleted
    api.getProject(projectId);
    Assert.assertEquals(404,
            api.getLastResponse().getStatusCode());
}





}


