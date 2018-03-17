

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperar parametros
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		
		//obter objeto de resposta
		PrintWriter out = response.getWriter();
		
		//conectar ao banco de dados
		
		//começar a montar HTML
				out.println("<html><head><title>Login</title></head>");
				out.println("<body>");
		
		try {
			//referenciar o driver JDBC
			Class.forName("com.mysql.jdbc.Driver");
			
			//Criar String de conexao
			String url = "jdbc:mysql://localhost/supermercado";
			String username = "root";
			String password = "root";
			
			//Realizar conexao com o BD
			Connection conexao = DriverManager.getConnection(url, username, password);
			
			//criando o SQL - Jeito HORIIVEL
			//String sql = "Select nomeusuario, senhausuario from usuario " +
			//"where nomeusuario = '" + usuario + "' and senhausuario = '" + senha + "'";
			
			//Criando SQL - Jeito MELHOR
			String sql = "Select nomeusuario, senhausuario from usuario " +
					"where nomeusuario = ? and senhausuario = ?";
			
			//preparar o SQL para envio ao DB
			PreparedStatement ps = conexao.prepareStatement(sql);
			//passar o valor de usuario
			ps.setString(1, usuario);
			//passar o valor de senha
			ps.setString(2, senha);
			
			//Executando SQL
			ResultSet rs = ps.executeQuery();
			
			//verificar se usuario e senha estao corretos
			if (rs.first()) {
				out.println("<h1>Bem Vindo! </h1>");
				out.println("<h2><a href=\"produtos\"> Listar Produtos</a> </h2>");
			} else {
				out.println("<h1>Login sem sucesso</h1>");
			}
			
			//fechar ResultSet
			rs.close();
			//fechar o PrepareStatment
			ps.close();
			//fechar o Connection
			conexao.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			//mostrar o erro
			e.printStackTrace();
		}
		
		//fechar o HTML
		out.println("</body></html>");
	}

}
