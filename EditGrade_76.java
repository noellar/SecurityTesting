package unitn.sectest;

	import net.sourceforge.jwebunit.api.IElement;
	import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
	import net.sourceforge.jwebunit.junit.WebTester;

	import org.junit.Before;
	import org.junit.Test;
	import org.xml.sax.helpers.AttributesImpl;

	import com.gargoylesoftware.htmlunit.html.DomElement;
	import com.gargoylesoftware.htmlunit.html.HtmlElement;
	import com.gargoylesoftware.htmlunit.html.InputElementFactory;

public class EditGrade_76 {
		
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
	        tester.clickLinkWithText("Grades");
	        tester.assertMatch("Grades");
		}
		
		// XSS 76
			
		@Test
		public void page(){
			tester.setWorkingForm("grades");
	        tester.setTextField("page", "2'> <a href =\"http://unitn.it\">malicious link</a> <br'");
	        tester.checkCheckbox("delete[]", "1");
	        tester.clickButtonWithText ("Edit");
	        tester.assertLinkNotPresentWithText("malicious link");
		}
				   
		 @Test
		 public void page2(){
			 tester.setWorkingForm("grades");
			 tester.setTextField("page2", "7'> <a href =\"http://unitn.it\">malicious link</a> <br'");
		     addSubmitButton("//form[@name='grades']");
		     tester.checkCheckbox("delete[]", "1");
			 tester.submit();
		     tester.assertLinkNotPresentWithText("malicious link"); 
		     }
			
			@Test
		  public void selectclass(){
				tester.setWorkingForm("grades");
		        tester.setTextField("selectclass", "1'> <a href =\"http://unitn.it\">malicious link</a> <br'");
		        tester.checkCheckbox("delete[]", "1");
		        tester.clickButtonWithText ("Edit");
		        tester.assertMatch("Edit Grade");
		        tester.assertLinkNotPresentWithText("malicious link");
			}
			
			@Test
		   public void assignment(){
				 tester.setWorkingForm("grades");
				 tester.getElementByXPath("//input[@type='checkbox' and @value='1']").setAttribute("value", "2 -- '> <a href =\"http://unitn.it\">malicious link</a> <br' ");
			     tester.checkCheckbox("delete[]");
			     tester.clickButtonWithText ("Edit");
			     tester.assertLinkNotPresentWithText("malicious link");
}
			
		 @Test
		 public void delete(){
			 tester.setWorkingForm("grades");
			 tester.getElementByXPath("//input[@type='checkbox' and @value='1']").setAttribute("value", "1 -- '> <a href =\"http://unitn.it\">malicious link</a> <br' ");
		     tester.checkCheckbox("delete[]");
		     tester.clickButtonWithText ("Edit");
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

