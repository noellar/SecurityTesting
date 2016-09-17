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

		public class ViewClassSettings_87 {
			
		private WebTester tester;

			
			@Before
		    public void setup(){
		        tester = new WebTester();  
		        tester.setBaseUrl("http://localhost/schoolmate");
				tester.beginAt("index.php");
				tester.setTextField("username", "noellar");
		        tester.setTextField("password", "noellar");
		        tester.submit();  
		        tester.assertMatch("Noellar Kappa's Classes");
		        tester.clickLinkWithText("Security Testing");
		        tester.assertMatch("Class Settings");
	}

			 @Test
				public void page(){
					tester.setWorkingForm("student");
			        tester.setTextField("page", "4'> <a href =http://unitn.it>malicious link</a> <br'");
			        tester.clickLinkWithExactText("Settings");
			        tester.assertLinkNotPresentWithText("malicious link");
				}
			
			
			 @Test
			 public void page2(){
				 tester.setWorkingForm("student");
				 tester.setTextField("page2", "1'> <a href =http://unitn.it>malicious link</a> <br'");
				 addSubmitButton("//form[@name='student']");
				 tester.submit();
				 tester.assertLinkNotPresentWithText("malicious"); 
			     }
			 
			 
			 @Test
			 public void selectclass(){
				 tester.setWorkingForm("student");
				 tester.setTextField("selectclass", "1 --'> <a href =http://unitn.it>malicious link</a> <br'");
				 tester.clickLinkWithExactText("Settings");
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
