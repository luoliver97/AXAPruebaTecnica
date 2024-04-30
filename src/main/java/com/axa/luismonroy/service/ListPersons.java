package com.axa.luismonroy.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.axa.luismonroy.model.Persona;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConsumerCustomer
 */
@WebServlet("/")
public class ListPersons extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPersons() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("https://8e7c6b8a-fc46-4674-a529-4ebec57295d3.mock.pstmn.io/customers");

        HttpResponse httpResponse = client.execute(get);
        
        if (httpResponse.getStatusLine().getStatusCode() != 200) {
        	request.setAttribute("errorMessage", "No se encontraron datos");
			request.getRequestDispatcher("showData.jsp").forward(request, response);
			return;
		}
        String responseData = EntityUtils.toString(httpResponse.getEntity());
        
	    responseData = responseData.replace('“', '"').replace('”', '"').replace('–', '-');
	    
	    Gson gson = new Gson();
	    Type listType = new TypeToken<List<Persona>>(){}.getType();
	    List<Persona> listaDatos = gson.fromJson(responseData, listType);
	    
	    if (listaDatos.isEmpty()) {
        	request.setAttribute("errorMessage", "No se encontraron datos");
			request.getRequestDispatcher("showData.jsp").forward(request, response);
			return;
		}
	    
	    request.setAttribute("data", listaDatos);
	    request.getRequestDispatcher("showData.jsp").forward(request, response);
	}

}
