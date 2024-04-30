package practice_page;

import custom_utilities.base.Browser_Launch;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test_Cases_Set_Three extends Browser_Launch {

    @Test(groups = {"group1"})
    public void case1() {
        System.out.println("Group execution");

    }

/*
    @Parameters({"Value"})
    @Test
    public void case2( String val){
        System.out.println(val);
    }

 */
    @Test(dataProvider ="dpMethod" )
    public void values(String value1, String value2){
        System.out.println(value1);
        System.out.println(value2);
    }

    @DataProvider
    public Object[][] dpMethod() {
        Object [][]values = new Object[2][2];
        values[0][0] = "val1";
        values[0][1] = "val2";

        values[1][0] = "val3";
        values[1][1] = "val4";
        return values;
    }

}
