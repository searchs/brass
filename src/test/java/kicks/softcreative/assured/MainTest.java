package kicks.softcreative.assured;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertNotNull;
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
    public void aUserCanNotAccessIfNoBasicAuthHeaderUsingRestAssured(){

        given().
                contentType("text/xml").
                expect().
                statusCode(404).
                when().
                get("http://localhost:80/todos.xml");

    }
}


