

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
 * Servlet implementation class ServletExcluirProdutos
 */
@WebServlet("/excluirProduto")
public class ServletExcluirProdutos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletExcluirProdutos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recuperar parametros
				String id = request.getParameter("id");
				String nome = request.getParameter("nome");
				//obter objeto de resposta
				PrintWriter out = response.getWriter();
				
				//conectar ao banco de dados
				
				//come�ar a montar HTML
						out.println("<html><head><title>Listar Produtos</title></head>");
						out.println("<body>");
				
						//ArrayList<String> produtos = new ArrayList<String>();
						
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
					//String sql = "select * from produto";
					String sql = "delete from produto where idproduto=" + id;
					
					//preparar o SQL para envio ao DB
					PreparedStatement ps = conexao.prepareStatement(sql);
					//passar o valor de usuario
					//ps.setString(1, id);
					//passar o valor de senha
					
					
					//Executando SQL
					ps.executeUpdate(sql);
					
					out.print("<h1>Produto " + nome + " foi excluido com sucesso </h1>");
					//verificar se usuario e senha estao corretos
					
						
						//produtos.add(nomeProduto + "#" + descProduto + "#" + fabriProduto + "#" + precoProduto);
						
					
					//for (String produto : produtos) {
						//String parameters[] = produto.split("#");
						
						//out.println("Nome: " + parameters[0] + "Descri��o: " + parameters[1] + "Fabricante: " + parameters[2] + "Pre�o: " + parameters[3] + "<br>");
					//}
					
					//fechar ResultSet
					
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
