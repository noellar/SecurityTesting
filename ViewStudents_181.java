package unitn.sectest;

	import org.junit.*;
import org.xml.sax.helpers.AttributesImpl;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.InputElementFactory;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.*;


	public class ViewStudents_181 {
		private WebTester tester;
	   
	    
	    @Before
	    public void setup(){
	        tester = new WebTester();  
	        tester.setBaseUrl("http://localhost/schoolmate");
			tester.beginAt("index.php");
	        tester.setTextField("username", "Jack");
	        tester.setTextField("password", "password@1");
	        tester.submit();
	        tester.assertMatch("Jack Porter's Classes");
	        tester.clickLinkWithText("Security Testing");
	        tester.assertMatch("Class Settings");
	        tester.clickLinkWithText("Students");
	        tester.assertMatch("Students");


}
	    
	    
		 @Test
			public void page(){
				tester.setWorkingForm("teacher");
		        tester.setTextField("page", "2'> <a href =http://unitn.it>malicious link</a> <br'");
		        tester.clickLinkWithExactText("Students");
		        tester.assertLinkNotPresentWithText("malicious");
			}
		
		
		 @Test
		 public void page2(){
			 tester.setWorkingForm("teacher");
			 tester.setTextField("page2", "8'> <a href =http://unitn.it>malicious link</a> <br'");
			 addSubmitButton("//form[@name='teacher']");
			 tester.submit();
			 tester.assertLinkNotPresentWithText("malicious"); 
		     }
		 
		 
		 @Test
		 public void selectclass(){
			 tester.setWorkingForm("teacher");
			 tester.setTextField("selectclass", "1 --'> <a href =http://unitn.it>malicious link</a> <br'");
			 tester.clickLinkWithExactText("Students");
			 tester.assertLinkNotPresentWithText("malicious"); 
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


