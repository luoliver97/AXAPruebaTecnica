package com.axa.luismonroy.service;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.axa.luismonroy.model.Persona;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConsumerCustomer
 */
@WebServlet("/insertPerson")
public class InsertPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertPerson() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("insertPerson.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String ageStr = request.getParameter("age");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		int age = 0;

		boolean valid = true;
		StringBuilder errorMsg = new StringBuilder();

		if (!Pattern.matches("[A-Z][a-z]+(?: [A-Z][a-z]+)*", name) || name.length() > 50) {
			errorMsg.append("Nombre en formato incorrecto o demasiado largo.");
			valid = false;
		}

		try {
			age = Integer.parseInt(ageStr);
			if (age < 0 || age > 150) {
				errorMsg.append("Edad fuera de rango permitido.");
				valid = false;
			}
		} catch (NumberFormatException e) {
			errorMsg.append("Edad inválida.");
			valid = false;
		}

		if (!Pattern.matches("[0-9]{10}", phoneNumber)) {
			errorMsg.append("Número de teléfono en formato incorrecto. ");
			valid = false;
		}

		if (!Pattern.matches("[\\w\\s]+ \\d+ # \\d+\\w* - \\d+\\w*", address)) {
			errorMsg.append("Dirección en formato incorrecto. ");
			valid = false;
		}

		if (!valid) {
			request.setAttribute("errorMessage", errorMsg.toString());
			request.getRequestDispatcher("insertPerson.jsp").forward(request, response);
			return;
		} else {
			request.removeAttribute("errorMessage");
		}
		Persona person = new Persona(name, age, phoneNumber, address);

		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://8e7c6b8a-fc46-4674-a529-4ebec57295d3.mock.pstmn.io/customers");

		StringEntity entity = new StringEntity(person.toString());
		post.setEntity(entity);
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-type", "application/json");

		HttpResponse httpResponse = client.execute(post);

		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			String responseString = EntityUtils.toString(httpResponse.getEntity());

			response.setContentType("text/html");
			response.getWriter().write("Respuesta del servicio: " + responseString);

			response.sendRedirect("ListPersons");
		} else {

			request.getRequestDispatcher("insertPerson.jsp").forward(request, response);
		}

	}

}
