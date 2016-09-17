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

	public class EditAnnouncement_41 {
		
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
        tester.clickLinkWithText("Announcements");
        tester.assertMatch("Manage Announcements");
    }
		
		@Test
		public void page(){
			tester.setWorkingForm("announcements");
	        tester.setTextField("page", "1'> <a href =\"http://unitn.it\">malicious link</a> <br'");
	        tester.checkCheckbox("delete[]", "1");
	        tester.clickButtonWithText ("Edit");
	        //tester.assertMatch("Edit Announcement");
	        tester.assertLinkNotPresentWithText("malicious link");
		}
		
		   
		 @Test
		 public void page2(){
			 tester.setWorkingForm("announcements");
			 tester.setTextField("page2", "19'> <a href =\"http://unitn.it\">malicious link</a> <br'");
		     addSubmitButton("//form[@name='announcements']");
		     tester.checkCheckbox("delete[]", "1");
			 tester.submit();
			 //tester.clickButtonWithText ("Edit");
		     //tester.assertMatch("Edit Announcement");
		     tester.assertLinkNotPresentWithText("malicious link"); 
		     }
		 
		 @Test
		 public void delete(){
			 tester.setWorkingForm("announcements");
			 tester.getElementByXPath("//input[@type='checkbox' and @value='1']").setAttribute("value", "1 -- '> <a href =\"http://unitn.it\">malicious link</a> <br");
		     tester.checkCheckbox("delete[]");
		     tester.clickButtonWithText ("Edit");
		     //tester.assertMatch("Edit Announcement");
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


