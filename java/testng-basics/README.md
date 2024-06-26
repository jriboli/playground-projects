# TestNG Basics

In this module to play around with a few different TestNG basics. 

### Related Blogs/Posts
- [Something Something](http://google.com) 

### Valuable Notes

**Q:** Can you arrange the below testng.xml tags from parent to child?

**A:** suite > test > classes > class > methods

**Q:** Difference between Soft and Hard assertions?

**A:** assertAll()

**Q:** What is an exception test in TestNG?

**A:** public void t1(expectedExceptions = ElementNotFoundException.class)

**Q:** How to set test case priority in TestNG?

**A:**

**Q:** How can you parameterize testing in TestNG?

**A:**  using testng.xml or a Data Provider

**Q:** How to run a group of test cases using TestNG?

**A:** @Test(groups = {"group1", "group2"})

**Q:** How to execute parallel?

**A:** <suite parallel="true" thread-count="5" >

**Q:** How to exclude?

**A:** <methods><exclude name="excludedTest"></methods>

**Q:** How to disable a test in TestNG?

**A:** @Test(enabled=false)

**Q:** How to setup dependencies in TestNG?

**A:** @Test(dependsOnMethods="dependableMethod")

**Q:** What is the use of @Listener annotation in TestNG?

**A:** onTestStart, onTestSuccess, onTestFailure, etc.

**Q:** How to add regex in testng.xml?

**A:** <include name=".*smoke.*" />

**Q:** What are @Factory and @DataProvider annotation?

**A:** Factory - a factory will execute all the test methods present inside a test class using a separate
instance of the respective class with different set of data.
DataProvider - a test method uses DataProvider will be executed the specific method multiple number of
times based on the data provided.
