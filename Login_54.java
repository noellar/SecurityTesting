package unitn.sectest;

import org.junit.*;
import net.sourceforge.jwebunit.junit.*;


	public class Login_54 {
		private WebTester tester;
		private String previousValue;

	    
	    @Before
	    public void prepare(){
	        tester = new WebTester();  
	        tester.setBaseUrl("http://localhost/schoolmate");
	            tester.beginAt("/index.php");
	            tester.setTextField("username", "schoolmate");
	            tester.setTextField("password", "schoolmate");
	            tester.submit();
	            tester.assertTitleEquals("SchoolMate - School Name");
	            tester.clickLinkWithText("School");
	            tester.assertMatch("Manage School Information");
	    }	            
	            
	       @Test
	       public void test(){
	            previousValue = tester.getElementByXPath("html//textarea [@name='sitetext']").getTextContent();
	            tester.setTextField("sitetext", "welcome <a href =http://unitn.it>malicious link</a>");
	            tester.clickButtonWithText(" Update ");
                tester.clickLinkWithText("Log Out");
	            tester.assertMatch("Today's Message");
	            tester.assertLinkNotPresentWithText ("<a href =http://unitn.it>malicious link</a>");
	    }
	             
	        @After
	        public void cleanUp(){
	            if (previousValue!=null) {
	            tester.beginAt("/index.php");
	            tester.setTextField("username", "schoolmate");
	            tester.setTextField("password", "schoolmate");
	            tester.submit();
	            tester.assertTitleEquals("SchoolMate - School Name");
	            tester.clickLinkWithText("School");
	            tester.assertMatch("Manage School Information");
	            tester.setTextField("sitetext", "welcome");
	            tester.clickButtonWithText(" Update ");
	              
}	
	            }
	}
		
	            
