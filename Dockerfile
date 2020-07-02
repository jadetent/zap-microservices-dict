# Por esse parametro você seta a tag que vai ser baixada do core.
ARG CORE_TAG=latest

# Imagem base.
FROM czap/core:$CORE_TAG

# Adiciona pom.xml.
ADD ./pom.xml /app/pom.xml

# Adiciona arquivos do projéto.
ADD ./src/ /app/src/

# Usa .m2 do caching do github actions. O arquivo Dockerfile é copiado apenas como um contorno para a possibilidade do .m2 não existir.
# Comand copy precisa de pelo menos um arquivo válido, assim não precisa mexer nesse Dockerfile para rodar em outra máquina.
COPY ./Dockerfile ./.m2/* /root/.m2/repository/

# Pasta para executar o mvn install.
WORKDIR /app/

# Compilação do projeto.
RUN mvn clean install

# Mostra arquivos da pasta compilada no console de build para facilitar implementação e testes.
RUN ls -a ./target/

# Declaração para persistir a pasta após o fim da compilação da imagem.
VOLUME /root/.m2

# Adicionando arquivo usado para iniciar sistema.
COPY ./start.sh /app/scripts/start.sh

# Adicionando possibilidade de executar os scripts.
RUN chmod -R +x /app/scripts/

# Chamada do script que torna possível execução dinâmica do jar.
ENTRYPOINT /app/scripts/start.sh
