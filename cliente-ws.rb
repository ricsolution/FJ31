require "savon"

# cria um cliente para o SOAP service
client = Savon.client(:wsdl => "http://localhost:8080/fj31-loja-ejb3/ConsultaDeLivrosBean?wsdl")

response = client.call(:busca_livro) do
  message ({:nome => "pais"})
end

#buscando o resultado chamado "listaDeLivros"

livros = response.body[:busca_livro_reponse][:lista_de_livros]

if(livros.nil?)
  puts "Nenhum livro encontrado!"
else
  puts "Encontrados: #{livros}"
end
