package unitn.sectest;

	import org.junit.*;
	import net.sourceforge.jwebunit.junit.*;


	public class ManageSemesters_234 {
		private WebTester tester;
	    private String previousValue;
	    
	    @Before
	    public void prepare(){
	        tester = new WebTester();  
	        tester.setBaseUrl("http://localhost/schoolmate");
	        
	 }
	    
	    @Test   
	    public void test(){
	        tester.beginAt("/index.php");
	        tester.setTextField("username", "schoolmate");
	        tester.setTextField("password", "schoolmate");
	        tester.submit();
	        
	        tester.assertTitleEquals("SchoolMate - School Name");
	        tester.clickLinkWithText("Semesters");
	        
	        tester.assertMatch("Manage Semesters");

}
}

