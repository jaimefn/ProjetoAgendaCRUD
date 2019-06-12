package filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import connection.SingleConnection;

@WebFilter("/*")
public class Filtro implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {


		try {
			request.setAttribute("connection", SingleConnection.getConnection());
			chain.doFilter(request, response);
			
			SingleConnection.getConnection().commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				SingleConnection.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
