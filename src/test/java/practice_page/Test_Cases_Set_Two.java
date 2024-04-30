package practice_page;

import custom_utilities.base.Browser_Launch;
import custom_utilities.pages.Source_Page_Opening;
import org.testng.annotations.Test;

public class Test_Cases_Set_Two extends Browser_Launch {

    @Test
    public void case1(){
        System.out.println();

        Source_Page_Opening Source_Page_Opening_calls = new Source_Page_Opening(driver);

        //web page opening
        Source_Page_Opening_calls.page_Opening();

    }

    @Test(dependsOnMethods = {"case1"})
    public void case2(){
        Source_Page_Opening Source_Page_Opening_calls = new Source_Page_Opening(driver);

        Source_Page_Opening_calls.click();
        Source_Page_Opening_calls.mouse_click();
    }

    @Test(enabled = false)
    public void case3(){
        System.out.println("this is not executed");
    }

    @Test(timeOut = 5000)
    public void case4(){
        System.out.println("wait till 40 second untils gets executed");
    }

}
