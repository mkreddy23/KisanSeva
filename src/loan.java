

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servelt
 */
@WebServlet("/servelt")
public class servelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();  
        response.setContentType("text/html");  
        out.println("<html><body>");  
        try
        {  
            Class.forName("oracle.jdbc.driver.OracleDriver");  
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");  
            Statement stmt = con.createStatement();  
            String n=request.getParameter("loan amount");  
            String p=request.getParameter("interest");
            String q=request.getParameter("Tenure");
            int loan=Integer.valueOf(n);
            int interest=Integer.valueOf(p);
            int Tenure=Integer.valueOf(q);
            ResultSet rs = stmt.executeQuery("select * from bank");  
            out.println("<table class=\"table table-sm\" border=1 width=50% height=50%>");  
            out.println("<tr class=\"table-dark\"><th>BANK</th><th>LOAN AMOUNT</th><th>INTEREST</th><th>TENURE</th><th>EMI</th></tr>");  
            while (rs.next())
            {  
               String b= rs.getString("Bank");
                int l = rs.getInt("Loan");  
                double i = (double)rs.getInt("Interest");  
                int t = rs.getInt("Tenure");  
                if(loan>=l&&Tenure>=t&&interest>=i){
		double emi=0;
		double pp=i/1200;
		double k=t;
		t=t*24;
		emi=l*i*Math.pow((1+i),t);
	    	emi=emi/(Math.pow((1+i),t)-1);
                out.println("<tr class=\"table-primary\"><td>" + b + "</td><td>" + l +"</td><td>" + i + "</td><td>" + k + "</td><td>" + emi + "</td></tr>");}
            }  
            out.println("</table>");  
            out.println("</html></body>");  
            con.close();  
           }  
            catch (Exception e)
           {  
            e.printStackTrace();
        }  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
