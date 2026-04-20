package testng.Demo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependsOnGroup {

    @Test(groups = "groupA")
    public void testMethod1ForGroupA() {
        System.out.println("Running test method1 of groupA");
    }

    @Test(groups = "groupA")
    public void testMethod2ForGroupA() {
        System.out.println("Running test method2 of groupA");
        //Assert.fail();
    }

    @Test(groups = "groupB")
    public void testMethod1ForGroupB() {
        System.out.println("Running test method1 of groupB");
    }

    @Test(groups = "groupB")
    public void testMethod2ForGroupB() {
        System.out.println("Running test method2 of groupB");
    }

    @Test(dependsOnGroups = "groupA")
    public void dependsOnGroupA() {
        System.out.println("Running the dependent test");
    }
}