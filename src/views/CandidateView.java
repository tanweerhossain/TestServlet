package views;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import utils.CandidateData;

@WebServlet("/api/*")
public class CandidateView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<CandidateData> candidates;
	
	private void insertManyCandidates() {
		String name[] = { "Bhuvan", "Tanweer", "Heena" };
		Long phone[] = { 9876543210L, 1234567890L, 7654321098L };
		String id[] = { "1", "2", "3" };
		
		for( int i = 0 ; i < name.length ; ++i ) {
			CandidateData candidateData = new CandidateData();
			candidateData.setName(name[i]);
			candidateData.setId(id[i]);
			candidateData.setPhone(phone[i]);
			this.candidates.add(candidateData);
		}
	}
	
    public void init() {
    	try {
		    this.candidates = new ArrayList<CandidateData>();
		    this.insertManyCandidates();
    	} catch (Exception e) {
    		 e.printStackTrace();
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out  = response.getWriter();
		response.setContentType("text/html");
		
		String[] URL = request.getRequestURI().split("/");
		try {
			int candidateId = (URL.length == 4 )? Integer.parseInt(URL[3]) : 0;
			
			if( (candidateId > 0) && (candidateId <= this.candidates.size()) ) {
				for(CandidateData data : this.candidates)
					if( Integer.parseInt(data.getId()) == candidateId ) {
						out.println("<h1>"+ data.getName() +" Details</h1>");
						out.println("<><><><><><><><><><><><><><><><br />");
						out.println("Phone : " + data.getPhone() );
					}
			} else 
				out.println("<h1>ID DOESN'T MATCH<h2>");
		} catch (Exception e) {
			out.println("<h1>ID DOESN'T MATCH<h2>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CandidateData candidateData = new CandidateData();
		
		candidateData.setId( String.valueOf( this.candidates.size()+1) );
		candidateData.setName( request.getParameter("name") );
		candidateData.setPhone( Long.parseLong( request.getParameter("phone") ) );
		this.candidates.add(candidateData);
		
		PrintWriter out  = response.getWriter();
		response.setContentType("text/html");
		
		for(CandidateData data : this.candidates) {
			out.println("<><><><><><><><><><><><><><><><br />");
			out.println("<h1>"+ data.getId() + ". " + data.getName() +" Details</h1>");
			out.println("Phone : " + data.getPhone() + "<br />" );
			out.println("<><><><><><><><><><><><><><><><br />");
		}
	}

}
