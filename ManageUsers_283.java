package unitn.sectest;

	import org.junit.Before;
	import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.InputElementFactory;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.WebTester;

	public class ManageUsers_283 {

			private WebTester tester;

			
			
			@Before
		    public void setup(){
		        tester = new WebTester();  
		        tester.setBaseUrl("http://localhost/schoolmate");
				tester.beginAt("index.php");
				tester.setTextField("username", "schoolmate");
		        tester.setTextField("password", "schoolmate");
		        tester.submit();	        
		        tester.assertTitleEquals("SchoolMate - School Name");
		        tester.clickLinkWithText("Users");   
		        tester.assertMatch("Manage Users");
			}
			
			
		    
			@Test
			public void page(){
				tester.setWorkingForm("admin");
		        tester.setTextField("page", "1'> <a href =http://unitn.it>malicious link</a>'");
		        tester.clickLinkWithExactText("Users");
		        tester.assertLinkNotPresentWithText("malicious link");
			}
		    
			
	@Test
	public void onpage(){
		tester.setWorkingForm("users");
	    tester.setTextField("onpage", "1'> <a href =\"http://unitn.it\">malicious link</a> <br'");
	    addSubmitButton("//form[@name='users']");
		tester.submit();
	    tester.assertMatch("Manage Users");
	    tester.assertLinkNotPresentWithText("malicious link");
	}

	 @Test
	 public void page2(){
		 tester.setWorkingForm("admin");
		 tester.setTextField("page2", "10'> <a href =\"http://unitn.it\">malicious link</a> <br' ");
		 addSubmitButton("//form[@name='admin']");
		 tester.submit();
		 tester.assertMatch("Manage Users");
		 tester.assertLinkNotPresentWithText("malicious link"); 
	     }
	 
		private void addSubmitButton(String fromXpath){
			   IElement element = tester.getElementByXPath(fromXpath);
			   DomElement form= ((HtmlUnitElementImpl)element).getHtmlElement();
			   InputElementFactory factory = InputElementFactory.instance;
			   AttributesImpl attributes =  new AttributesImpl();
			   attributes.addAttribute("", "", "type", "", "submit");
			   HtmlElement submit = factory.createElement(form.getPage(), "input", attributes);
			   form.appendChild(submit);
		}
}
