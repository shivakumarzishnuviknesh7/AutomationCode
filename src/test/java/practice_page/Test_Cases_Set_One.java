package practice_page;

import custom_utilities.base.Browser_Launch;
import custom_utilities.pages.Source_Page_Opening;
import org.testng.annotations.*;

public class Test_Cases_Set_One extends Browser_Launch {


    @BeforeClass
    public void before_All_Class() {
        System.out.println("executed before all class  - BeforeClass");
    }

    @BeforeMethod
    public void entry_Run_for_all_method() {
        System.out.println("its entry_Run_for_all_method  - BeforeMethod");
    }


    @AfterMethod
    public void exit_Run_for_all_method() {
        System.out.println("its exit_Run_for_all_method  - AfterMethod");
    }


    @BeforeTest
    public void starting_Point() {
        System.out.println("its Started  - BeforeTest");
    }

    @BeforeSuite
    public void suite_Start() {
        System.out.println("its run before suite  - BeforeSuite");
    }

    @Test(priority = 1)
    public void case1(){
        System.out.println();

        Source_Page_Opening Source_Page_Opening_calls = new Source_Page_Opening(driver);

        //web page opening
        Source_Page_Opening_calls.page_Opening();
        System.out.println("test 1");
    }

    @Test(priority = 2)
    public void case2(){
        Source_Page_Opening Source_Page_Opening_calls = new Source_Page_Opening(driver);

        Source_Page_Opening_calls.click();
        Source_Page_Opening_calls.mouse_click();
        System.out.println("test 2");
    }

    @AfterSuite
    public void after_Suite_Ends(){
        System.out.println("suite execution over  - AfterSuite");
    }

    @AfterTest
    public void endMessage(){
        System.out.println("its over  - AfterTest");
    }

    @AfterClass
    public void after_All_Class() {
        System.out.println("executed after all class  - AfterClass");
    }

}
