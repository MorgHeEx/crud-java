package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Produto{

    private int id;
    private int categoria_id;
    private String nome;
    private double preco;
    private int quantidade;

    /**
     * @param id
    */
    public Produto(){}
    public Produto(int id){
        if(id > 0){
            String sql = "SELECT * FROM produtos WHERE id = ?";
    
            try{
                Connection con = DB.conexao(); 
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet registro = stmt.executeQuery();
                
                while(registro.next()){
                    this.setId(registro.getInt("id"));
                    this.setNome(registro.getString("nome"));
                    this.setPreco(registro.getDouble("preco"));
                    this.setQuantidade(registro.getInt("quantidade"));      
                    
                }

            }catch(SQLException e){      	
                System.out.println("Erro no Consulta Produto: " + e.toString()); 
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoriaId() {
        return categoria_id;
    }

    public void setCategoriaId(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public ArrayList<Produto> listar(){
        String sql = "SELECT * FROM produtos";
        ArrayList<Produto> produtos = new ArrayList<>();

        try{
            Connection con = DB.conexao(); 
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet registros = stmt.executeQuery();

            while(registros.next()){
                Produto temp = new Produto();
                temp.setId(registros.getInt("id"));
                temp.setNome(registros.getString("nome"));
                temp.setPreco(registros.getDouble("preco"));
                temp.setQuantidade(registros.getInt("quantidade"));
                    
                produtos.add(temp);	 
            }
        }catch(SQLException e){      	
            System.out.print("Erro no Listar Produto: " + e.toString()); 
        }

        return produtos;
    }


    public void adicionar(){
        String sql = "INSERT INTO produtos (categoria_id, nome, preco, quantidade) VALUES (?, ?, ?, ?)";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, this.getCategoriaId());   
            stmt.setString(2, this.getNome());   
            stmt.setDouble(3, this.getPreco());     
            stmt.setInt(4, this.getQuantidade());
            stmt.execute();
        
        }catch(SQLException e){      	
            System.out.print("Erro no adicionar Produto: " + e.toString()); 
        }
    }

    public void atualizar (){
        String sql = "UPDATE produtos SET categoria_id = ?, nome = ?, preco = ?, quantidade = ?  WHERE id = ? ";
        try{
            Connection con = DB.conexao();     
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.getCategoriaId());
            stmt.setString(2, this.getNome());   
            stmt.setDouble(3, this.getPreco());     
            stmt.setInt(4, this.getQuantidade());
            stmt.setInt(5, this.getId());
            stmt.executeUpdate();
    
        }catch(SQLException e){      	
            System.out.print("Erro no Atualizar Produto: " + e.toString()); 
        }   
    }
    public void excluir(){
      String sql = "DELETE FROM produtos WHERE id =?";
      try{
      Connection con = DB.conexao();
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, this.getId());
      stmt.executeUpdate();
    }catch(SQLException e){        
      System.out.print("Erro no excluir produto: " + e.toString()); 
    }
  }
}