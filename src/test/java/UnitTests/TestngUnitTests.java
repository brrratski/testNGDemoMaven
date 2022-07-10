package UnitTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;


public class TestngUnitTests {
    Random rm = new Random();

    /* Create the following tests:
       A+B
       A-B
       A*B
       A/B
       A%B*/
    @DataProvider(name = "dp")
    public Object[][] dataProviderMethod() {


        return new Object[][]{{15, 10}, {30, 8}, {12, -6}, {1, 4}, {89, 212}};
    }

    @Test(dataProvider = "dp", groups = "mathTestSum")
    public void sum(int a, int b) {
        int sum = a + b;
        Assert.assertEquals(a + b, sum);
    }

    @Test(dataProvider = "dp", groups = "mathTestMinus", dependsOnGroups = "mathTestSum")
    public void subtraction(int a, int b) {
        int subtractionResult = a - b;
        Assert.assertEquals(a - b, subtractionResult);
    }

    @Test(dataProvider = "dp", groups = "mathTestMulti")
    public void multiplication(int a, int b) {
        int multiplicationResult = a * b;
        Assert.assertEquals(a * b, multiplicationResult);
    }

    @Test(dataProvider = "dp", groups = "mathTestDivision")
    public void division(int a, int b) {
        int divisionResult = a / b;
        Assert.assertEquals(a / b, divisionResult);
    }

    @Test(dataProvider = "dp"/*, groups = "mathTestModuleDivision"*/)
    public void moduleDivision(int a, int b) {
        int moduleDivisionResult = a % b;
        Assert.assertEquals(a % b, moduleDivisionResult);
    }
}
