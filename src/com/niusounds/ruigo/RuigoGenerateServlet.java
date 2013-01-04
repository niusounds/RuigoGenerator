package com.niusounds.ruigo;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RuigoGenerateServlet extends HttpServlet {
	private RuigoGenerate gen = new RuigoGenerate();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("q");
		String filter = req.getParameter("f");
		String[] result = gen.generate(input);

		if (filter != null && !filter.isEmpty()) {
			result = gen.filter(result, filter);
		}

		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		BufferedWriter bw = new BufferedWriter(resp.getWriter());
		for (String s : result) {
			bw.write(s);
			bw.write(" ");
		}
		bw.flush();
	}
}
