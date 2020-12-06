# Desafio Java Grupo WL

<h4 align="center">
  Projeto desenvolvido com Java, MySql, Glassfish, Primefaces
</h4>

<br>

<h4 align="center">
  http://3.17.4.232/desafio-java-grupo-wl/
</h4>

<br>

## 📜 Documentação API

     Listar todos os usuário
     
     - GET
     - /desafio-java-grupo-wl/api/usuario/listar   
    
     
     Adicionar um usuário (nome, documento(cpf), dataNascimento )
     
     - POST
     - /desafio-java-grupo-wl/api/usuario/novo 
     - Body: {        
	        "nome": "NOME",
	        "documento": "000.000.000-00",
	        "dataNascimento": "00/00/0000"
        }
     
            
     Remover usuário
     - GET
     - /desafio-java-grupo-wl/api/usuario/remover
     - Headers: [
                  {
                      "id": "001"
                  }
                ]


<br>
