# curso_mastertech
Projeto do Sistema de Ponto

APIs disponíveis:

GET /usuarios : lista todos os usuários cadastrados na base
  Saída: lista de usuários, contendo id, nome completo, cpf e e-mail

GET /usuario/{id} : exibe os dados de um usuário de acordo com id informado
  Parâmetro de entrada: id do usuário
  Saída: detalhes do usuário, contendo id, nome completo, cpf, e-mail e data do cadastro

POST /usuario : cria um usuario com os dados informados, com exceção do id, o qual é gerado automaticamente
  Entrada: usuario contendo nome, sobrenome, cpf, email e data do cadastro
  Saída: usuario cadastrado contendo id, nome completo, cpf e e-mail
 
PUT /usuario/{id} : altera as informações do usuario de id informado, exceto id e data do cadastro
  Entrada: id do usuario que deseja alterar e os dados nome, sobrenome, cpf e e-mail
  Saída: usuario alterado contendo id, nome completo, cpf e e-mail

GET /usuario/{id}/pontos : listagem de todas as batidas de ponto do usuário de id informado, juntamente com o total de horas trabalhadas por esse mesmo usuário
  Parâmetro de entrada: id do usuário
  Saída: Lista dos pontos batidos e o total de horas trabalhadas

POST /ponto : registra uma batida de ponto para o usuário informado
  Entrada: id do usuário que deseja realizar a batida
  Saída: ponto contendo os dados do id do usuario, data/hora da batida e o tipo da batida (ENTRADA ou SAIDA) 
  
