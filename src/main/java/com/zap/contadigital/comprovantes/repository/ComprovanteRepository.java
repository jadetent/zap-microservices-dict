package com.zap.contadigital.comprovantes.repository;

import com.zap.contadigital.comprovantes.model.Comprovante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComprovanteRepository extends JpaRepository<Comprovante, Long> {

    @Query(value = "select * from tb_comprovantes where telefone = ?1 order by data_transacao desc limit 10", nativeQuery = true)
    List<Comprovante> buscaUltimosDezComprovantes(@Param("telefone") String telefone);
}