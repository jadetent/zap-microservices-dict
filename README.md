# Zap Microservices Comprovantes


O `Zap Microservices Comprovantes` é um serviço utilizado para requisições *HTTP* de *API RESTful*. As requisições são determinadas por meio de certos parâmetros e feitas pelo *bot* ao barramento.

O objetivo é criar o comprovante de uma transação da conta dos usuários. Com base na necessidade de acessar as informações da conta de forma **segura** e **ágil**, a experiência do usuário ao realizar essa consulta é melhor quando usado o aplicativo *WhatsApp* em relação ao aplicativo da Conta Zap.

### Fluxo


#### Segurança

Efetuar chamadas em dados bancários exige **confiabilidade**. Para isso, o serviço utilizado de criptografia é `JWT` (*JSON Web Token*).

#### Documentação

O *framework* `Swagger` permite a geração da documentação de forma automática a medida que o projeto avança. Com isso facilita a visualização, descrição e consumo da *API RESTful*.