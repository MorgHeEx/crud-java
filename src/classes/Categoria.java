package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Categoria {
    private int id;
    private String nome;

    public Categoria(){}
    public Categoria(int id){
        if(id > 0){
            String sql = "SELECT * FROM categorias WHERE id = ?";
            try{
                Connection con = DB.conexao(); 
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet registro = stmt.executeQuery();
                
                while(registro.next()){
                    this.setId(registro.getInt("id"));
                    this.setNome(registro.getString("nome"));    
                }
            }catch(SQLException e){      	
                System.out.println("Erro no Consulta Categoria: " + e.toString()); 
            }
        }
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Categoria> listar(){
        String sql = "SELECT * FROM categorias";
        ArrayList<Categoria> categorias = new ArrayList<>();

        try{
            Connection con = DB.conexao(); 
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet registros = stmt.executeQuery();

            while(registros.next()){
                Categoria temp = new Categoria();
                temp.setId(registros.getInt("id"));
                temp.setNome(registros.getString("nome"));
                categorias.add(temp);	 
            }
        }catch(SQLException e){      	
            System.out.print("Erro no Listar Categorias: " + e.toString()); 
        }
        return categorias;
    }


    public void adicionar(){
        String sql = "INSERT INTO categorias (nome) VALUES (?)";
   
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
   
            stmt.setString(1, this.getNome());
            stmt.execute();
        }catch(SQLException e){      	
            System.out.print("Erro no adicionar Categoria: " + e.toString()); 
        }
   }
   
    public void excluir(){
      String sql = "DELETE FROM categorias WHERE id =?";
      try{
      Connection con = DB.conexao();
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, this.getId());
      stmt.executeUpdate();
    }catch(SQLException e){        
      System.out.print("Erro no excluir categoria: " + e.toString()); 
    }
}