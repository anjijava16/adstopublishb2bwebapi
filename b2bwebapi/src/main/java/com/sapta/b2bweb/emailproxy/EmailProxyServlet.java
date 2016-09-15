package com.sapta.b2bweb.emailproxy;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmailProxyServlet
 */
@WebServlet("/EmailProxy")
public class EmailProxyServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private String host = "";
	private String fromEmail = "";
	boolean htmlContent;
	String jsonResponse = "";
	
    public EmailProxyServlet() {
        super();
    }
	

	public void init() {
		// reads SMTP server details from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter(CommonConstants.HOST);
		fromEmail = context.getInitParameter(CommonConstants.FROMEMAIL);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("Email proxy post methodeS");
			response.setHeader(CommonConstants.ACCESS_CONTROL_ALLOW_ORIGIN, CommonConstants.STAR);
	        response.setHeader(CommonConstants.CACHE_CONTROL, CommonConstants.NO_CACHE);
			// Reading Input parameters
			//String requestType = request.getHeader("X-Requested-With");
			String toEmails = request.getParameter(CommonConstants.TOEMAIL);
			String ccEmails = request.getParameter(CommonConstants.CCEMAIL);
			String bccEmails = request.getParameter(CommonConstants.BCCMAIL);
			String emailBody = request.getParameter(CommonConstants.EMAILBODY);
			String returnURL = request.getParameter(CommonConstants.RETURNURL);
			String requestType = request.getParameter(CommonConstants.REQUESTTYPE);
			
			
			String tempFromEmail = request.getParameter(CommonConstants.FROMEMAIL);
			if(tempFromEmail != null && tempFromEmail.length() > 0){
				fromEmail = tempFromEmail;
			}
			
			
			String tempHtmlContent = request.getParameter(CommonConstants.HTMLCONTENT);
			
			if(tempHtmlContent != null && tempHtmlContent.length() > 0 && tempHtmlContent.equalsIgnoreCase(CommonConstants.TRUE)){
				htmlContent = true;
			} else {
				htmlContent = false;
			}
			
			host = (host != null || host != CommonConstants.EMPTY) ? host : CommonConstants.EMPTY;
			fromEmail = (fromEmail != null || fromEmail != CommonConstants.EMPTY) ? fromEmail : CommonConstants.EMPTY;
			
			toEmails = (toEmails != null || toEmails != CommonConstants.EMPTY) ? toEmails : CommonConstants.EMPTY;
			ccEmails = (ccEmails != null || ccEmails != CommonConstants.EMPTY) ? ccEmails : CommonConstants.EMPTY;
			bccEmails = (bccEmails != null || bccEmails != CommonConstants.EMPTY) ? bccEmails : CommonConstants.EMPTY;
			emailBody = (emailBody != null || emailBody != CommonConstants.EMPTY) ? emailBody : CommonConstants.EMPTY;
			returnURL = (returnURL != null || returnURL != CommonConstants.EMPTY) ? returnURL : CommonConstants.EMPTY;
	
			// Handling Bad Request
			if(toEmails != null && toEmails.length() > 0 && emailBody != null && emailBody.length() > 0){
				System.out.println("came inside");
				boolean mailStatus = EmailProxyUtil.sendEmail( ccEmails, bccEmails, emailBody, htmlContent,toEmails); 
				
				if(mailStatus){
					// Redirecting to Return URL on success
					if(returnURL != null && returnURL.length() > 0){
						response.sendRedirect(returnURL);
					}
					
					// Handling 'AJAX' Request
					if(requestType != null && requestType.equalsIgnoreCase(CommonConstants.AJAX)){
						jsonResponse = ResponseUtil.buildSuccessResponse();
					}
				}else{
					jsonResponse = ResponseUtil.buildErrorResponse(CommonConstants.FAILURE_MSG);
				}
			}else{
				jsonResponse = ResponseUtil.buildErrorResponse(CommonConstants.INPUT_ERROR);
			}
			
			response.getWriter().print((jsonResponse != null) ? jsonResponse.toString() : CommonConstants.EMPTY);
			
		} catch (Exception exception) {
			jsonResponse = ResponseUtil.buildErrorResponse(CommonConstants.FAILURE_MSG, exception);
			response.getWriter().print((jsonResponse != null) ? jsonResponse.toString() : CommonConstants.EMPTY);
		}
	}

}
