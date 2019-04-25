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

	public class GradeReport_241 {
		
	private WebTester tester;
	
		
		@Before
		   public void prepare(){
	        tester = new WebTester();  
	        tester.setBaseUrl("http://localhost/schoolmate");
	        tester.beginAt("/index.php");
	        tester.setTextField("username", "schoolmate");
	        tester.setTextField("password", "schoolmate");
	        tester.submit();
	        tester.assertTitleEquals("SchoolMate - School Name");
	        tester.clickLinkWithText("Students");
	        tester.assertMatch("Manage Students");
}
		
		
    	@Test
 		public void page(){
 			tester.setWorkingForm("students");
 	        tester.setTextField("page", "1'> <a href =http://unitn.it>malicious link</a> <br'");
 	        tester.selectOption("report", "Grade Report");
 	        tester.assertMatch("Grade Report");
 	        tester.assertLinkNotPresentWithText("malicious link");
 		}
   	 @Test
		 public void page2(){
			 tester.setWorkingForm("students");
			 tester.setTextField("page2", "28'> <a href =http://unitn.it>malicious link</a> <br'");
			 addSubmitButton("//form[@name='students']");
			 tester.submit();
		     tester.assertMatch("Grade Report");
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

