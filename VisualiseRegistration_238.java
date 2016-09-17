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

	public class VisualiseRegistration_238 {

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
		        tester.clickLinkWithText("Registration");
		        tester.assertMatch("Registration");

		        
			}
			 @Test
				public void page(){
					tester.setWorkingForm("registration");
			        tester.setTextField("page", "1'> <a href =http://unitn.it>malicious link</a> <br'");
			        tester.clickButtonWithText("Show in Grid");
			        tester.assertMatch("Noellar Kappa's Schedule");
			        tester.assertLinkNotPresentWithText("malicious");
				}
			
			
			 @Test
			 public void page2(){
				 tester.setWorkingForm("registration");
				 tester.setTextField("page2", "29'> <a href =http://unitn.it>malicious link</a> <br'");
				 addSubmitButton("//form[@name='registration']");
				 tester.submit();
			        tester.assertMatch("Noellar Kappa's Schedule");
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
