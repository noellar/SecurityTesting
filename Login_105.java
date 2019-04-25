package unitn.sectest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.InputElementFactory;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.WebTester;

public class Login_105 {
	
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
            previousValue = tester.getElementByXPath("html//textarea [@name='sitemessage']").getTextContent();
            tester.setTextField("sitemessage", "First Day <a href =http://unitn.it>malicious link</a>");
            tester.clickButtonWithText(" Update ");
            tester.clickLinkWithText("Log Out");
            tester.assertMatch("Today's Message");
            tester.assertLinkNotPresentWithText ("<a href =http://unitn.it>malicious link</a>");
    }
             
        @Test
        public void page(){
            tester.setTextField("page", "0'> <a href =http://unitn.it>malicious link</a> <br'");
            tester.clickLinkWithText("Log Out");
            tester.assertLinkNotPresentWithText("malicious link");
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
            tester.setTextField("sitetext", "First Day");
            tester.clickButtonWithText(" Update ");
              
}	
            }

}
