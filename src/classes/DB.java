package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB{
	public static final String servidor = "127.0.0.1:3306";
	public static final String usuario = "root";
	public static final String senha = "";
	public static final String nome_banco = "crud";

	public static Connection conexao(){
        Connection conexao = null; 
        try{         	
 	        Class.forName("com.mysql.cj.jdbc.Driver");         
            conexao = DriverManager.getConnection("jdbc:mysql://"+servidor+"/"+nome_banco
            +"?useSSL=false&useTimezone=true&serverTimezone=UTC",  usuario, senha);   
        }catch(SQLException e){         
            System.out.println("Erro de Conexao:" + e.toString()); 
        }catch(ClassNotFoundException e){         
            System.out.println("Classe nao Encontrada:" + e.toString()); 
        }           	
      
        return conexao;
    }       
}
